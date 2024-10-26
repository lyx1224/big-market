package cn.lyx.infrastructure.persistent.dao;

import cn.lyx.domain.strategy.model.entity.StrategyEntity;
import cn.lyx.infrastructure.persistent.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lyx
 * @description 策略总表 DAO
 * @since 2024/10/19
 */
@Mapper
public interface IStrategyDao {
    List<Strategy> queryStrategyList();

    Strategy queryStrategyByStrategyId(Long strategyId);

}
