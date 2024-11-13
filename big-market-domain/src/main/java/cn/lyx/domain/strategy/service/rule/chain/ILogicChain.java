package cn.lyx.domain.strategy.service.rule.chain;

import cn.lyx.domain.strategy.service.rule.chain.factory.DefaultChainFactory;

/**
 * @author lyx
 * @description 抽奖策略规则责任链接口
 * @since 2024/10/29
 */
public interface ILogicChain extends ILogicChainArmory{
    /**
     * 责任链接口
     *
     * @param userId     用户ID
     * @param strategyId 策略ID
     * @return 奖品ID
     */
    DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId);


}
