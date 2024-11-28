package cn.lyx.domain.activity.service;

import cn.lyx.domain.activity.model.entity.ActivityCountEntity;
import cn.lyx.domain.activity.model.entity.ActivityEntity;
import cn.lyx.domain.activity.model.entity.ActivitySkuEntity;
import cn.lyx.domain.activity.repository.IActivityRepository;
import cn.lyx.domain.activity.service.rule.factory.DefaultActivityChainFactory;

/**
 * @author lyx
 * @description 抽奖活动的支撑类
 * @since 2024/11/27
 */
public class RaffleActivitySupport {

    protected DefaultActivityChainFactory defaultActivityChainFactory;

    protected IActivityRepository activityRepository;

    public RaffleActivitySupport(DefaultActivityChainFactory defaultActivityChainFactory, IActivityRepository activityRepository) {
        this.defaultActivityChainFactory = defaultActivityChainFactory;
        this.activityRepository = activityRepository;
    }

    public ActivitySkuEntity queryActivitySku(Long sku){
        return activityRepository.queryActivitySku(sku);
    }

    public ActivityEntity queryRaffleActivityByActivityId(Long activityId){
        return activityRepository.queryRaffleActivityByActivityId(activityId);
    }

    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId){
        return activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
    }

}
