package cn.lyx.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author lyx
 * @description 策略总表
 * @create 2024/10/19
 */
@Data
public class Strategy {
    /**自增ID */
    private Long id;
    /**抽奖策略ID */
    private Long strategyId;
    /**抽奖策略描述 */
    private String strategyDesc;
    /**抽象规则模型 */
    private String ruleModels;
    /**创建时间 */
    private Date createTime;
    /**更新时间 */
    private Date updateTime;


}
