package cn.lyx.domain.award.service.distribute;

import cn.lyx.domain.award.model.entity.DistributeAwardEntity;

/**
 * @author lyx
 * @description 分发奖品接口
 * @since 2024/12/24
 */
public interface IDistributeAward {

    void giveOutPrizes(DistributeAwardEntity distributeAwardEntity);
}
