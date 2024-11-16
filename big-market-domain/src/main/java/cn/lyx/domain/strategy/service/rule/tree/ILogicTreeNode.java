package cn.lyx.domain.strategy.service.rule.tree;

import cn.lyx.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author lyx
 * @description 规则树接口
 * @since 2024/11/11
 */
public interface ILogicTreeNode{
    DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId,String ruleValue);

}
