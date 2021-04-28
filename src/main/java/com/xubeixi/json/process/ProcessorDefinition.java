package com.xubeixi.json.process;

import java.lang.annotation.Annotation;

/**
 * @author xubeixi
 * @date 2021-04-25 18:06
 * 加工器定义类
 */
public class ProcessorDefinition {
    /**
     * 自定义注解Class
     */
    private Class<? extends Annotation> annotationClass;

    /**
     * 属性值的加工逻辑
     */
    private ProcessValue processValue;

    public ProcessorDefinition(Class<? extends Annotation> annotationClass, ProcessValue processValue) {
        this.annotationClass = annotationClass;
        this.processValue = processValue;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public ProcessValue getProcessValue() {
        return processValue;
    }
}
