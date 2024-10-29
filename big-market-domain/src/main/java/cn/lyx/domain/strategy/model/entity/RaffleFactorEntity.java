package cn.lyx.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lyx
 * @description 抽奖因子实体
 * @since 2024/10/25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleFactorEntity {

    private String userId;
    private Long strategyId;
    private Integer awardId;
}
