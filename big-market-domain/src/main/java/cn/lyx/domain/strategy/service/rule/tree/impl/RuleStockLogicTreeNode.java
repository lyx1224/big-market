package cn.lyx.domain.strategy.service.rule.tree.impl;

import cn.lyx.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.lyx.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.lyx.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.lyx.domain.strategy.service.rule.tree.factory.DefaultTreeFactory.TreeActionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lyx
 * @description 从库存提取结点
 * @since 2024/11/11
 */
@Slf4j
@Component("rule_stock")
public class RuleStockLogicTreeNode implements ILogicTreeNode {
    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .build();
    }
}
