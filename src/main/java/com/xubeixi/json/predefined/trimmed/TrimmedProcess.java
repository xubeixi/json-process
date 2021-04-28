package com.xubeixi.json.predefined.trimmed;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xubeixi.json.process.jackson.ProcessSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xubeixi
 * @date 2021-04-27 12:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JsonSerialize(using = ProcessSerializer.class)
@JacksonAnnotationsInside
public @interface TrimmedProcess {
    /**
     * 去除空白字符的级别
     */
    TrimmerType value() default TrimmerType.ALL_WHITESPACES;
}
