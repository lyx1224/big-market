package cn.lyx.domain.activity.service.rule;

/**
 * @author lyx
 * @description 抽奖动作责任链装配
 * @since 2024/11/27
 */
public interface IActionChainArmory {

    IActionChain next();

    IActionChain appendNext(IActionChain next);
}
