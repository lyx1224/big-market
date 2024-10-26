package cn.lyx.domain.strategy.service.rule;

import cn.lyx.domain.strategy.model.entity.RuleActionEntity;
import cn.lyx.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @author lyx
 * @description 抽奖规则过滤接口
 * @since 2024/10/25
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {
    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
