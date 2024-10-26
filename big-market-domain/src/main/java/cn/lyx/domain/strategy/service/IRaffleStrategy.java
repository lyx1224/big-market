package cn.lyx.domain.strategy.service;

import cn.lyx.domain.strategy.model.entity.RaffleAwardEntity;
import cn.lyx.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * @author lyx
 * @description 抽奖策略接口
 * @since 2024/10/25
 */
public interface IRaffleStrategy {
    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);
}
