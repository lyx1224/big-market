package cn.lyx.domain.strategy.service;

import cn.lyx.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

/**
 * @author lyx
 * @description 奖品查询接口
 * @since 2024/11/17
 */
public interface IRaffleAward {
    /**
     * 根据策略ID查询抽奖奖品列表配置
     *
     * @param strategyId 策略ID
     * @return 奖品列表
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);
    /**
     * 根据策略ID查询抽奖奖品列表配置
     *
     * @param activityId 活动ID
     * @return 奖品列表
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardListByActivityId(Long activityId);
}
