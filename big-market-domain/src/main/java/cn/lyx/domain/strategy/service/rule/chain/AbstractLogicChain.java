package cn.lyx.domain.strategy.service.rule.chain;

/**
 * @author lyx
 * @description
 * @since 2024/10/29
 */
public abstract class AbstractLogicChain implements ILogicChain {
    private ILogicChain next;

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    @Override
    public ILogicChain next() {
        return next;
    }
    protected abstract String ruleModel();
}