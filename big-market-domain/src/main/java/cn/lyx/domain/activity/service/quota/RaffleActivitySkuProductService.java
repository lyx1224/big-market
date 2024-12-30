package cn.lyx.domain.activity.service.quota;

import cn.lyx.domain.activity.model.entity.SkuProductEntity;
import cn.lyx.domain.activity.repository.IActivityRepository;
import cn.lyx.domain.activity.service.IRaffleActivitySkuProductService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lyx
 * @description
 * @since 2024/12/30
 */
@Service
public class RaffleActivitySkuProductService implements IRaffleActivitySkuProductService {
    @Resource
    IActivityRepository repository;
    @Override
    public List<SkuProductEntity> querySkuProductEntityListByActivityId(Long activityId) {
        return repository.querySkuProductEntityListByActivityId(activityId);
    }
}
