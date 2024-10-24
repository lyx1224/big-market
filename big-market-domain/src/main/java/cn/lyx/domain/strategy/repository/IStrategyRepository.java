package cn.lyx.domain.strategy.repository;

import cn.lyx.domain.strategy.model.entity.StrategyAwardEntity;
import cn.lyx.domain.strategy.model.entity.StrategyEntity;
import cn.lyx.domain.strategy.model.entity.StrategyRuleEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author lyx
 * @description 策略仓储服务接口
 * @since 2024/10/21
 */
public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardSearchRateTable(String key, Integer rateRange, Map<Integer, Integer> strategyAwardSearchRateTable);

    Integer getRateRange(Long strategyId);

    Integer getStrategyAwardAssemble(String key, Integer rateKey);

    StrategyEntity queryStrategyEntityByStrategyIdList(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    int getRateRange(String key);

}
