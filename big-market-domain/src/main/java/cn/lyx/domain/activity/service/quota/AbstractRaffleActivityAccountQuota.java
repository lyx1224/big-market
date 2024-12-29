package cn.lyx.domain.activity.service.quota;

import cn.lyx.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.lyx.domain.activity.model.entity.*;
import cn.lyx.domain.activity.repository.IActivityRepository;
import cn.lyx.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.lyx.domain.activity.service.quota.policy.ITradePolicy;
import cn.lyx.domain.activity.service.quota.rule.IActionChain;
import cn.lyx.domain.activity.service.quota.rule.factory.DefaultActivityChainFactory;
import cn.lyx.types.enums.ResponseCode;
import cn.lyx.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 抽奖活动抽象类，定义标准的流程
 * @create 2024-03-16 08:42
 */
@Slf4j
public abstract class AbstractRaffleActivityAccountQuota extends RaffleActivityAccountQuotaSupport implements IRaffleActivityAccountQuotaService {

    private final Map<String, ITradePolicy> tradePolicyGroup;

    public AbstractRaffleActivityAccountQuota(DefaultActivityChainFactory defaultActivityChainFactory, IActivityRepository activityRepository, Map<String, ITradePolicy> tradePolicyGroup) {
        super(defaultActivityChainFactory, activityRepository);
        this.tradePolicyGroup = tradePolicyGroup;
    }

    @Override
    public String createOrder(SkuRechargeEntity skuRechargeEntity) {
        // 1. 参数校验
        String userId = skuRechargeEntity.getUserId();
        Long sku = skuRechargeEntity.getSku();
        String outBusinessNo = skuRechargeEntity.getOutBusinessNo();
        if(null == sku || StringUtils.isBlank(userId)||StringUtils.isBlank(outBusinessNo)){
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(),ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 2. 查询基础信息
        // 2.1 通过sku查询活动信息
        ActivitySkuEntity activitySkuEntity = queryActivitySku(sku);
        // 2.2 查询活动信息
        ActivityEntity activityEntity = queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());
        // 2.3 查询次数信息（用户在活动上可参与的次数）
        ActivityCountEntity activityCountEntity = queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());

        // 3. 活动动作规则校验
        IActionChain actionChain = defaultActivityChainFactory.openActionChain();
        actionChain.action(activitySkuEntity, activityEntity, activityCountEntity);

        // 4. 构建订单聚合对象
        CreateQuotaOrderAggregate createOrderAggregate = buildOrderAggregate(skuRechargeEntity,activitySkuEntity,activityEntity,activityCountEntity);

        // 5. 保存订单
        ITradePolicy tradePolicy = tradePolicyGroup.get(skuRechargeEntity.getOrderTradeType().getCode());
        tradePolicy.trade(createOrderAggregate);

        // 6. 返回单号
        return createOrderAggregate.getActivityOrderEntity().getOrderId();
    }

    protected abstract CreateQuotaOrderAggregate buildOrderAggregate(SkuRechargeEntity skuRechargeEntity, ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);

}
