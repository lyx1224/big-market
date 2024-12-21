package cn.lyx.domain.rebate.service;

import cn.lyx.domain.rebate.event.SendRebateMessageEvent;
import cn.lyx.domain.rebate.model.aggregate.BehaviorRebateAggregate;
import cn.lyx.domain.rebate.model.entity.BehaviorEntity;
import cn.lyx.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.lyx.domain.rebate.model.entity.TaskEntity;
import cn.lyx.domain.rebate.model.valobj.DailyBehaviorRebateVO;
import cn.lyx.domain.rebate.model.valobj.TaskStateVO;
import cn.lyx.domain.rebate.repository.IBehaviorRebateRepository;
import cn.lyx.types.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import cn.lyx.types.common.Constants;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyx
 * @description 行为返利服务实现
 * @since 2024/12/14
 */
@Service
public class BehaviorRebateService implements IBehaviorRebateService{

    @Resource
    private IBehaviorRebateRepository behaviorRebateRepository;

    @Resource
    private SendRebateMessageEvent sendRebateMessageEvent;



    @Override
    public List<String> createOrder(BehaviorEntity behaviorEntity) {
        // 1. 查询返利配置
        List<DailyBehaviorRebateVO> dailyBehaviorRebateVOS = behaviorRebateRepository.queryDailyBehaviorRebateConfig(behaviorEntity.getBehaviorTypeVO());
        if(null == dailyBehaviorRebateVOS || dailyBehaviorRebateVOS.isEmpty()) return new ArrayList<>();

        // 2. 构建聚合对象
        List<String> orderIds = new ArrayList<>();
        List<BehaviorRebateAggregate> behaviorRebateAggregates = new ArrayList<>();
        for(DailyBehaviorRebateVO dailyBehaviorRebateVO : dailyBehaviorRebateVOS){
            // 拼装业务ID；用户ID_返利类型_外部透彻业务ID
            String bizId = behaviorEntity.getUserId() + Constants.UNDERLINE + dailyBehaviorRebateVO.getRebateType() + Constants.UNDERLINE + behaviorEntity.getOutBusinessNo();
            BehaviorRebateOrderEntity behaviorRebateOrderEntity = BehaviorRebateOrderEntity.builder()
                        .userId(behaviorEntity.getUserId())
                        .orderId(RandomStringUtils.randomNumeric(12))
                        .behaviorType(dailyBehaviorRebateVO.getBehaviorType())
                        .rebateDesc(dailyBehaviorRebateVO.getRebateDesc())
                        .rebateType(dailyBehaviorRebateVO.getRebateType())
                        .rebateConfig(dailyBehaviorRebateVO.getRebateConfig())
                        .bizId(bizId)
                        .build();
            orderIds.add(behaviorRebateOrderEntity.getOrderId());

            // MQ消息对象
            SendRebateMessageEvent.RebateMessage rebateMessage = SendRebateMessageEvent.RebateMessage.builder()
                    .userId(behaviorEntity.getUserId())
                    .rebateType(dailyBehaviorRebateVO.getRebateType())
                    .rebateConfig(dailyBehaviorRebateVO.getRebateConfig())
                    .bizId(bizId)
                    .build();

            // 构建事件消息
            BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> rebateMessageEventMessage = sendRebateMessageEvent.buildEventMessage(rebateMessage);

            // 组装任务对象
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setUserId(behaviorEntity.getUserId());
            taskEntity.setTopic(sendRebateMessageEvent.topic());//BaseEvent中topic就是用来组装到任务中进行透传的
            taskEntity.setMessageId(rebateMessageEventMessage.getId());
            taskEntity.setMessage(rebateMessageEventMessage);
            taskEntity.setState(TaskStateVO.create);


            BehaviorRebateAggregate behaviorRebateAggregate = BehaviorRebateAggregate.builder()
                    .userId(behaviorEntity.getUserId())
                    .behaviorRebateOrderEntity(behaviorRebateOrderEntity)
                    .taskEntity(taskEntity)
                    .build();

            behaviorRebateAggregates.add(behaviorRebateAggregate);
        }

        // 3. 存储聚合对象数据
        behaviorRebateRepository.saveUserRebateRecord(behaviorEntity.getUserId(), behaviorRebateAggregates);

        // 4. 返回订单ID集合
        return orderIds;
    }
}
