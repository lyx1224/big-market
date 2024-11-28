package cn.lyx.domain.activity.service.rule;

import cn.lyx.domain.activity.model.entity.ActivityCountEntity;
import cn.lyx.domain.activity.model.entity.ActivityEntity;
import cn.lyx.domain.activity.model.entity.ActivitySkuEntity;

/**
 * @author lyx
 * @description 下单规则过滤接口
 * @since 2024/11/27
 */
public interface IActionChain extends IActionChainArmory {

    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);

}
