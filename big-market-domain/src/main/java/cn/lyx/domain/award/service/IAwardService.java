package cn.lyx.domain.award.service;

import cn.lyx.domain.award.model.entity.DistributeAwardEntity;
import cn.lyx.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @author lyx
 * @description 奖品服务接口//写入流水和task表
 * @since 2024/11/30
 */
public interface IAwardService {
    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);

    /**
     * 配送发货奖品
     */
    void distributeAward(DistributeAwardEntity distributeAwardEntity);
}
