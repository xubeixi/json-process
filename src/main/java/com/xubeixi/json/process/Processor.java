package com.xubeixi.json.process;

import java.lang.annotation.Annotation;

/**
 * @author xubeixi
 * @date 2021-04-26 7:39
 * 加工器
 */
public class Processor {
    /**
     * 自定义注解对象
     */
    private Annotation annotation;

    /**
     * 属性值的加工逻辑
     */
    private ProcessValue processValue;

    public Processor(Annotation annotation, ProcessValue processValue) {
        this.processValue = processValue;
        this.annotation = annotation;
    }

    public ProcessValue getProcessValue() {
        return processValue;
    }

    public Annotation getAnnotation() {
        return annotation;
    }
}
