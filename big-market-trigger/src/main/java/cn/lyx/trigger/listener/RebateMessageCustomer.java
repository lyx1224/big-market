package cn.lyx.trigger.listener;


import cn.lyx.domain.activity.model.entity.SkuRechargeEntity;
import cn.lyx.domain.activity.service.IRaffleActivityAccountQuotaService;
import cn.lyx.domain.rebate.event.SendRebateMessageEvent;
import cn.lyx.domain.rebate.model.valobj.RebateTypeVO;
import cn.lyx.types.event.BaseEvent;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static java.lang.Character.getType;

/**
 * @author lyx
 * @description 监听；行为返利消息
 * @since 2024/12/21
 */
@Slf4j
@Component
public class RebateMessageCustomer {

    @Value("${spring.rabbitmq.topic.send_rebate}")
    private String topic;

    @Resource
    private IRaffleActivityAccountQuotaService raffleActivityAccountQuotaService;
    @RabbitListener(queuesToDeclare = @Queue(value = "${spring.rabbitmq.topic.send_rebate}"))
    public void listener(String message){
        try{
            log.error("监听用户行为返利消息 topic：{} message：{}",topic,message);
            // 1. 转换消息
            BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> eventMessage = JSON.parseObject(message, new TypeReference<BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage>>() {
            }.getType());
            SendRebateMessageEvent.RebateMessage rebateMessage = eventMessage.getData();
            if(!RebateTypeVO.SKU.getCode().equals(rebateMessage.getRebateType())){
                log.info("监听用户行为返利消息 - 非SKU奖励暂时不处理 topic:{} message:{}", topic,message );
                return;
            }

            //2.入账奖励
            SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
            skuRechargeEntity.setUserId(rebateMessage.getUserId());
            skuRechargeEntity.setSku(Long.valueOf(rebateMessage.getRebateConfig()));
            skuRechargeEntity.setOutBusinessNo(rebateMessage.getBizId());
            raffleActivityAccountQuotaService.createOrder(skuRechargeEntity);
            log.error("监听用户行为返利消息，成功！ topic：{} message：{}",topic,message);

        }catch (Exception e){
            log.error("监听用户行为返利消息，消费失败 topic：{} message：{}",topic,message,e);
            throw e;
        }
    }
}
