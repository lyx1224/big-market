package cn.lyx.trigger.api.dto;

import lombok.Data;

/**
 * @author lyx
 * @description 抽奖奖品列表，请求对象
 * @since 2024/11/17
 */
@Data
public class RaffleAwardListRequestDTO {
    // 用户ID
    private String userId;
    // 抽奖活动ID
    private Long activityId;

}
