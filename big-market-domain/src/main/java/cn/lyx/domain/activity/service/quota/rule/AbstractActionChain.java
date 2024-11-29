package cn.lyx.domain.activity.service.quota.rule;

/**
 * @author lyx
 * @description 下单规则责任链抽象类
 * @since 2024/11/27
 */
public abstract class AbstractActionChain implements IActionChain{
    private IActionChain next;

    public  IActionChain next(){
        return next;
    }

    public IActionChain appendNext(IActionChain next){
        this.next = next;
        return next;
    }
}
