package cn.lyx.domain.award.repository;

import cn.lyx.domain.award.model.aggregate.GiveOutPrizesAggregate;
import cn.lyx.domain.award.model.aggregate.UserAwardRecordAggregate;

/**
 * @author lyx
 * @description
 * @since 2024/12/1
 */
public interface IAwardRepository {
    void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate);

    String queryAwardConfig(Integer awardId);

    void saveGiveOutPrizesAggregate(GiveOutPrizesAggregate giveOutPrizesAggregate);

    String queryAwardKey(Integer awardId);
}
