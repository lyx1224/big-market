package cn.lyx.domain.rebate.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lyx
 * @description 用户行为类型枚举值对象
 * @since 2024/12/14
 */
@Getter
@AllArgsConstructor
public enum BehaviorTypeVO {

    SIGN("sign","签到（日历）"),
    OPENAI_PAY("openai_pay","openai 外部支付完成"),
    ;

    private final String code;
    private final String info;
}
