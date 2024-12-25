package cn.lyx.domain.award.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lyx
 * @description 分发奖品实体对象
 * @since 2024/12/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistributeAwardEntity {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 奖品ID
     */
    private Integer awardId;
    /**
     * 奖品配置信息
     */
    private String awardConfig;

}

