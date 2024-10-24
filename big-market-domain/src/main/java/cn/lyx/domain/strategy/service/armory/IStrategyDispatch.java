package cn.lyx.domain.strategy.service.armory;

/**
 * @author lyx
 * @description 策略抽奖的调度（各种抽象策略的应用）
 * @since 2024/10/24
 */
public interface IStrategyDispatch {
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);
}
