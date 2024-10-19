package cn.lyx.infrastructure.persistent.dao;

import cn.lyx.infrastructure.persistent.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lyx
 * @description 抽奖策略奖品明细配置-概率、规则DAO
 * @since 2024/10/19
 */
@Mapper
public interface IStrategyAwardDao {
    List<StrategyAward> queryStrategyAwardList();
}
