package cn.lyx.domain.strategy.service.rule.tree.impl;

import cn.lyx.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.lyx.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.lyx.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lyx
 * @description 次数锁节点
 * @since 2024/11/11
 */
@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                .build();
    }
}
