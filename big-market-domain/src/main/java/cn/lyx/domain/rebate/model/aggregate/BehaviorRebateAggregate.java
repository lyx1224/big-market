package cn.lyx.domain.rebate.model.aggregate;

import cn.lyx.domain.rebate.model.entity.BehaviorRebateOrderEntity;
import cn.lyx.domain.rebate.model.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lyx
 * @description 用户行为返利聚合对象
 * @since 2024/12/15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BehaviorRebateAggregate {

    /** 用户ID */
    private String userId;
    /** 行为返利订单实体对象 */
    private BehaviorRebateOrderEntity behaviorRebateOrderEntity;
    /** 任务实体对象 */
    private TaskEntity taskEntity;
}
