package cn.lyx.infrastructure.persistent.dao;

import cn.lyx.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lyx
 * @description 策略规则DAO
 * @since 2024/10/19
 */
@Mapper
public interface IStrategyRuleDao {
    List<StrategyRule> queryStrategyRuleList();

    StrategyRule queryStrategyRule(StrategyRule strategyRuleReq);
}
