package cn.lyx.domain.strategy.service.raffle;

import cn.lyx.domain.strategy.model.valobj.RuleTreeVO;
import cn.lyx.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import cn.lyx.domain.strategy.repository.IStrategyRepository;
import cn.lyx.domain.strategy.service.AbstractRaffleStrategy;
import cn.lyx.domain.strategy.service.armory.IStrategyDispatch;
import cn.lyx.domain.strategy.service.rule.chain.ILogicChain;
import cn.lyx.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.lyx.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.lyx.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lyx
 * @description 用户抽奖流程中一些过滤规则的实现（包括before/center/after规则）
 * @since 2024/10/26
 */
@Slf4j
@Service
public class DefaultRaffleStrategy extends AbstractRaffleStrategy {
    public DefaultRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch, DefaultChainFactory defaultChainFactory, DefaultTreeFactory defaultTreeFactory) {
        super(repository, strategyDispatch, defaultChainFactory, defaultTreeFactory);
    }

    @Override
    public DefaultChainFactory.StrategyAwardVO raffleLogicChain(String userId, Long strategyId) {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(strategyId);
        return logicChain.logic(userId,strategyId);
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO raffleLogicTree(String userId, Long strategyId, Integer awardId) {
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModelVO(strategyId,awardId);
        if(null == strategyAwardRuleModelVO){
            return DefaultTreeFactory.StrategyAwardVO.builder().awardId(awardId).build();
        }
        RuleTreeVO ruleTreeVO = repository.queryRuleTreeVOByTreeId(strategyAwardRuleModelVO.getRuleModels());
        if (null == ruleTreeVO) {
            throw new RuntimeException("存在抽奖策略配置的规则模型 Key，未在库表 rule_tree、rule_tree_node、rule_tree_line 配置对应的规则树信息 " + strategyAwardRuleModelVO.getRuleModels());
        }
        IDecisionTreeEngine treeEngine = defaultTreeFactory.openLogicTree(ruleTreeVO);
        return treeEngine.process(userId, strategyId, awardId);
    }
}
