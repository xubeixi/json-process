package com.xubeixi.json.process.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.xubeixi.json.process.*;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xubeixi
 * @date 2021-04-25 23:33
 * Jackson通过serialize对value进行加工
 */
public class ProcessSerializer extends JsonSerializer<Object> implements ContextualSerializer {
    private List<Processor> processors = new ArrayList<>();

    public ProcessSerializer() {
        super();
    }

    private ProcessSerializer(List<Processor> processors) {
        this.processors = processors;
    }

    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Object executedValue = ProcessHandler.execute(value, processors);
        jsonGenerator.writeObject(executedValue);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        //beanProperty为null的直接跳过
        if (beanProperty != null) {
            if (ProcessorFactory.isEnable()) {
                List<ProcessorDefinition> processorDefinitions = ProcessorFactory.getProcessorDefinitions();
                List<Processor> processors = new ArrayList<>();
                for (ProcessorDefinition processorDefinition : processorDefinitions) {
                    Annotation annotation = beanProperty.getAnnotation(processorDefinition.getAnnotationClass());
                    if (annotation == null) {
                        annotation = beanProperty.getContextAnnotation(processorDefinition.getAnnotationClass());
                    }
                    //属性使用了自定义注解修饰的才加入相应的加工器
                    if (annotation != null) {
                        Processor processor = new Processor(annotation, processorDefinition.getProcessValue());
                        processors.add(processor);
                    }
                }

                if (processors.size() > 0) {
                    ProcessorOrder processorOrder = beanProperty.getAnnotation(ProcessorOrder.class);
                    if (processorOrder == null) {
                        processorOrder = beanProperty.getContextAnnotation(ProcessorOrder.class);
                    }
                    // 指定了注解顺序则先对processors进行排序
                    if (processorOrder != null) {
                        return new ProcessSerializer(ProcessHandler.orderProcessorsByAnnotationArray(processors, processorOrder.orderlyClasses()));
                    }
                    return new ProcessSerializer(processors);
                }
            }

            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }

        return serializerProvider.findNullValueSerializer(beanProperty);
    }
}
