package cn.lyx.domain.rebate.repository;

import cn.lyx.domain.rebate.model.aggregate.BehaviorRebateAggregate;
import cn.lyx.domain.rebate.model.valobj.BehaviorTypeVO;
import cn.lyx.domain.rebate.model.valobj.DailyBehaviorRebateVO;

import java.util.List;

/**
 * @author lyx
 * @description 行为返利服务仓储接口
 * @since 2024/12/14
 */
public interface IBehaviorRebateRepository {
    List<DailyBehaviorRebateVO> queryDailyBehaviorRebateConfig(BehaviorTypeVO behaviorTypeVO);
    void saveUserRebateRecord(String userId, List<BehaviorRebateAggregate> behaviorRebateAggregates);


}
