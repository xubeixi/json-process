package com.xubeixi.json.process;

import java.lang.annotation.*;

/**
 * @author xubeixi
 * @date 2021-04-26 11:18
 * 指定多个注解顺序的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ProcessorOrder {
    /**
     * 指定需要有序执行的注解的顺序
     */
    Class<? extends Annotation>[] orderlyClasses();
}
