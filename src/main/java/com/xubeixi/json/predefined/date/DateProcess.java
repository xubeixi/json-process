package com.xubeixi.json.predefined.date;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xubeixi.json.process.jackson.ProcessSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xubeixi
 * @date 2021-04-27 10:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JsonSerialize(using = ProcessSerializer.class)
@JacksonAnnotationsInside
public @interface DateProcess {
    /**
     * 日期是String格式时必须指定解析规则
     */
    String parse() default "";

    /**
     * 根据不同的类型设置了不同的缺省格式
     * LocalDate："yyyy-MM-dd"
     * LocalTime："HH:mm:ss"
     * 其它的都是："yyyy-MM-dd HH:mm:ss"
     */
    String format() default "";

    /**
     * Field是String类型时默认是字符串日期，设置true表示得到的是String式的时间戳，多见于远程调用其它服务
     */
    boolean isTimeStamp() default false;
}
