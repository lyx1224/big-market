package cn.lyx.domain.rebate.service;

import cn.lyx.domain.rebate.model.entity.BehaviorEntity;

import java.util.List;

/**
 * @author lyx
 * @description 行为返利服务接口
 * @since 2024/12/14
 */
public interface IBehaviorRebateService {
     List<String> createOrder(BehaviorEntity behaviorEntity);
}
