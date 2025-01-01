package cn.lyx.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lyx
 * @description 抽奖请求参数
 * @since 2024/11/17
 */
@Data
public class RaffleStrategyRequestDTO implements Serializable {
    // 抽奖策略ID
    private Long strategyId;
}
