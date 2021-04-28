package com.xubeixi.json.predefined.bigdecimal;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xubeixi.json.process.jackson.ProcessSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

/**
 * @author xubeixi
 * @date 2021-04-27 10:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JsonSerialize(using = ProcessSerializer.class)
@JacksonAnnotationsInside
public @interface BigDecimalProcess {
    /**
     * 格式化规则
     */
    String format() default "";

    /**
     * 保留位数
     * 默认保留2位小数
     */
    int scale() default 2;

    /**
     * 舍入模式
     * 默认使用四舍五入
     */
    int roundingMode() default BigDecimal.ROUND_HALF_UP;
}
