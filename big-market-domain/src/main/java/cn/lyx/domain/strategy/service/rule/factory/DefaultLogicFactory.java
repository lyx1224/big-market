package cn.lyx.domain.strategy.service.rule.factory;

import cn.lyx.domain.strategy.model.entity.RuleActionEntity;
import cn.lyx.domain.strategy.service.annotation.LogicStrategy;
import cn.lyx.domain.strategy.service.rule.ILogicFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lyx
 * @description
 * @since 2024/10/26
 */
@Service
public class DefaultLogicFactory {
    public Map<String, ILogicFilter<?>> logicFilterMap = new ConcurrentHashMap<>();

    //构造函数：传入一个规则过滤器列表，通过注解去查找过滤器的规则名称，然后将两者填入map中
    public DefaultLogicFactory(List<ILogicFilter<?>> logicFilters) {
        logicFilters.forEach(logic -> {
            LogicStrategy strategy = AnnotationUtils.findAnnotation(logic.getClass(), LogicStrategy.class);
            if (null != strategy) {
                logicFilterMap.put(strategy.logicMode().getCode(), logic);
            }
        });
    }

    public <T extends RuleActionEntity.RaffleEntity> Map<String, ILogicFilter<T>> openLogicFilter() {
        // 下面这种双重转换是为了绕过 Java 编译器的类型检查，
        // 因为直接将 Map<String, ILogicFilter<?>> 转换为 Map<String, ILogicFilter<T>> 会引发编译错误。
        return (Map<String, ILogicFilter<T>>) (Map<?, ?>) logicFilterMap;
    }
    @Getter
    @AllArgsConstructor
    public enum LogicModel {

        RULE_WIGHT("rule_weight","【抽奖前规则】根据抽奖权重返回可抽奖范围KEY","before"),
        RULE_BLACKLIST("rule_blacklist","【抽奖前规则】黑名单规则过滤，命中黑名单则直接返回","before"),
        RULE_LOCK("rule_lock","【抽奖中规则】抽奖n次后，对应奖品可解锁抽奖","center"),
        RULE_LUCK_AWARD("rule_luck_award","【抽奖后规则】幸运奖兜底","after"),

        ;

        private final String code;
        private final String info;
        private final String type;

        public static boolean isCenter(String code){
            return "center".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }

        public static boolean isAfter(String code){
            return "after".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }
    }
}
