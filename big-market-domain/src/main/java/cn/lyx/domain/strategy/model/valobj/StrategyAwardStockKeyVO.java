package cn.lyx.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lyx
 * @description 策略奖品库存KEY的标识值对象（当消耗一个库存时需要向缓存存储一个被消耗的奖品的信息）
 * @since 2024/11/14
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardStockKeyVO {
    // 策略ID
    private Long strategyId;
    // 奖品ID
    private Integer awardId;
}
