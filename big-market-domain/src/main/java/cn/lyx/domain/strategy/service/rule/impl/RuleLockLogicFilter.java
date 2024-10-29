package cn.lyx.domain.strategy.service.rule.impl;

import cn.lyx.domain.strategy.model.entity.RuleActionEntity;
import cn.lyx.domain.strategy.model.entity.RuleMatterEntity;
import cn.lyx.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.lyx.domain.strategy.repository.IStrategyRepository;
import cn.lyx.domain.strategy.service.annotation.LogicStrategy;
import cn.lyx.domain.strategy.service.rule.ILogicFilter;
import cn.lyx.domain.strategy.service.rule.factory.DefaultLogicFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lyx
 * @description 用户抽奖n次后，对应奖品可解锁抽奖
 * @since 2024/10/27
 */
@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_LOCK)
public class RuleLockLogicFilter implements ILogicFilter<RuleActionEntity.RaffleCenterEntity> {
    @Resource
    private IStrategyRepository repository;

    private Long userRaffleCount = 0L;
    @Override
    public RuleActionEntity<RuleActionEntity.RaffleCenterEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-次数锁 userId:{} strategyId:{} ruleModel:{}"
                , ruleMatterEntity.getUserId()
                , ruleMatterEntity.getStrategyId()
                , ruleMatterEntity.getRuleModel());
        String ruleValue = repository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(),ruleMatterEntity.getAwardId(),ruleMatterEntity.getRuleModel());
        Long raffleCount = Long.parseLong(ruleValue);
        if(userRaffleCount>=raffleCount){
            return RuleActionEntity.<RuleActionEntity.RaffleCenterEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }
        return RuleActionEntity.<RuleActionEntity.RaffleCenterEntity>builder()
                .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                .build();
    }
}
