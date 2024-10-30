package cn.lyx.domain.strategy.service.rule.chain;

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
    Integer logic(String userId, Long strategyId);


}
