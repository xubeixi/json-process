package com.xubeixi.json.process.fastjson;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.xubeixi.json.process.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xubeixi
 * @date 2021-04-16 16:48
 * FastJson通过值过滤器对value进行加工
 */
public class ProcessFilter implements ValueFilter {
    @Override
    public Object process(Object instance, String name, Object value) {
        if (ProcessorFactory.isEnable()) {
            if (null == instance || StringUtils.isEmpty(name) || null == value) {
                return value;
            }

            Class<?> instanceClazz = instance.getClass();
            //获取该值对应的域对象
            Field field = FieldUtils.getField(instanceClazz, name);
            //如果使用了@JSONField自定义域名可能会出现找不到报错的情况
            if (null == field) {
                field = FieldUtils.getResultField(instanceClazz, name);
            }

            //获取加工器List
            List<ProcessorDefinition> processorDefinitions = ProcessorFactory.getProcessorDefinitions();
            List<Processor> processors = new ArrayList<>();
            for (ProcessorDefinition processorDefinition : processorDefinitions) {
                //检查该域对象是否有自定义注解
                if (null != field && field.isAnnotationPresent(processorDefinition.getAnnotationClass())) {
                    //获取注解实例
                    Annotation annotation = field.getAnnotation(processorDefinition.getAnnotationClass());
                    //属性使用了自定义注解修饰的才加入相应的加工器
                    if (annotation != null) {
                        Processor processor = new Processor(annotation, processorDefinition.getProcessValue());
                        processors.add(processor);
                    }
                }
            }

            if (processors.size() > 0) {
                ProcessorOrder processorOrder = field.getAnnotation(ProcessorOrder.class);
                // 指定了注解顺序则先对processors进行排序
                if (processorOrder != null) {
                    processors = ProcessHandler.orderProcessorsByAnnotationArray(processors, processorOrder.orderlyClasses());
                }
                value = ProcessHandler.execute(value, processors);
            }
        }

        return value;
    }
}
