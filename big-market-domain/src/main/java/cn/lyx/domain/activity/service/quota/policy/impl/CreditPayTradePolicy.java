package cn.lyx.domain.activity.service.quota.policy.impl;

import cn.lyx.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import cn.lyx.domain.activity.model.valobj.OrderStateVO;
import cn.lyx.domain.activity.repository.IActivityRepository;
import cn.lyx.domain.activity.service.quota.policy.ITradePolicy;
import org.springframework.stereotype.Service;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 积分兑换，支付类订单
 * @create 2024-06-08 18:12
 */
@Service("credit_pay_trade")
public class CreditPayTradePolicy implements ITradePolicy {

    private final IActivityRepository activityRepository;

    public CreditPayTradePolicy(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void trade(CreateQuotaOrderAggregate createQuotaOrderAggregate) {
        createQuotaOrderAggregate.setOrderState(OrderStateVO.wait_pay);
        activityRepository.doSaveCreditPayOrder(createQuotaOrderAggregate);
    }

}
