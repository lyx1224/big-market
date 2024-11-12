package cn.lyx.domain.strategy.service.rule.tree.factory.engine;

import cn.lyx.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import org.springframework.stereotype.Service;

/**
 * @author lyx
 * @description 执行引擎
 * @since 2024/11/11
 */
@Service
public interface IDecisionTreeEngine {
    DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId);
}
