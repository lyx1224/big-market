package cn.lyx.domain.strategy.service.armory;

/**
 * @author lyx
 * @description 策略装配库（兵工厂），负责初始化策略计算
 * @since 2024/10/21
 */
public interface IStrategyArmory {
    boolean assembleLotteryStrategy(Long strategyId);

    Integer getRandomAwardId(Long strategyId);
}
