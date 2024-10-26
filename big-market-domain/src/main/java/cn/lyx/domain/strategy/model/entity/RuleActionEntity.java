package cn.lyx.domain.strategy.model.entity;

import cn.lyx.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import lombok.*;

/**
 * @author lyx
 * @description 规则动作实体
 * @since 2024/10/25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleActionEntity<T extends RuleActionEntity.RaffleEntity> {
    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();
    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();
    private String ruleModel;
    private T data;
    static public class RaffleEntity{

    }
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static public class RaffleBeforeEntity extends RaffleEntity{
        /**
         * 策略ID
         */
        private Long strategyId;

        /**
         * 权重值Key；用于抽奖时可以选择权重抽奖。
         */
        private String ruleWeightValueKey;

        /**
         * 奖品ID；用于黑名单用户直接返回指定奖品
         */
        private Integer awardId;
    }

    static public class RaffleCenterEntity extends RaffleEntity{

    }

    static public class RaffleAfterEntity extends RaffleEntity{

    }
}
