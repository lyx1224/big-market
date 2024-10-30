package cn.lyx.domain.strategy.service.rule.chain;

/**
 * @author lyx
 * @description 责任链装配接口
 * @since 2024/10/30
 */
public interface ILogicChainArmory {
    ILogicChain appendNext(ILogicChain next);

    ILogicChain next();
}
