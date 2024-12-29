package cn.lyx.domain.activity.model.entity;

import cn.lyx.domain.activity.model.valobj.OrderTradeTypeVO;
import lombok.Data;

/**
 * @author lyx
 * @description 活动商品充值实体对象
 * @since 2024/11/27
 */
@Data
public class SkuRechargeEntity {
    /** 用户ID */
    private String userId;
    /** 商品SKU - activity + activity count */
    private Long sku;
    /** 幂等业务单号，外部谁充值谁透传，这样来保证幂等（多次调用也能确保结果唯一，不会多次充值）。 */
    private String outBusinessNo;
    /** 获取sku方式 - 需要支付（积分兑换）/不需要支付（签到） */
    private OrderTradeTypeVO orderTradeType = OrderTradeTypeVO.rebate_no_pay_trade;
}
