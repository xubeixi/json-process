package com.xubeixi.json.process;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author xubeixi
 * @date 2021-04-26 11:17
 * 进行最终的加工及一些工具方法
 */
public class ProcessHandler {
    /**
     * 循环指定的加工器对value进行加工返回
     * @param value 原值
     * @param processors 属性上指定的加工器list
     * @return 最终值
     */
    public static Object execute(Object value, List<Processor> processors) {
        for (Processor processor : processors) {
            if (processor.getProcessValue().processCondition(value)) {
                value = processor.getProcessValue().processLogic(value, processor.getAnnotation());
            }
        }
        return value;
    }

    /**
     * 按照指定的顺序进行依次加工
     * @param processors 当前属性值的所有加工器
     * @param orderlyClasses 客户端指定的有序的注解数组
     * @return 排序后的加工器list
     */
    public static List<Processor> orderProcessorsByAnnotationArray(List<Processor> processors, Class[] orderlyClasses) {
        // 处理一些异常情况
        if(processors.size() < orderlyClasses.length) {
            for (int i = 0; i < orderlyClasses.length; i++) {
                //orderlyClass在processors是否存在
                boolean exists = false;
                for (Processor processor : processors) {
                    if(orderlyClasses[i] == processor.getAnnotation().annotationType()){
                        exists = true;
                    }
                }
                if(!exists) {
                    orderlyClasses[i] = null;
                }
            }

            orderlyClasses = Stream.of(orderlyClasses).filter(Objects::nonNull).toArray(Class[]::new);
        }

        // 将Class[]转换为Class为key，下标为value的Map
        Map<Class, Integer> classOrderMap = new HashMap<>();
        for (int i = 0; i < orderlyClasses.length; i++) {
            classOrderMap.put(orderlyClasses[i], i);
        }

        // 另外拷贝一个list，供替换前一部分有序
        List<Processor> needOrder = new ArrayList<>(processors.size());
        needOrder.addAll(processors);
        // 创建一个list，供保存不需要有序的Processor
        List<Processor> noNeedOrder = new ArrayList<>();
        for (Processor processor : processors) {
            if (classOrderMap.containsKey(processor.getAnnotation().annotationType())) {
                Integer order = classOrderMap.get(processor.getAnnotation().annotationType());
                needOrder.set(order, processor);
            } else {
                noNeedOrder.add(processor);
            }
        }

        // 按下标插入并截取掉无用部分
        needOrder.addAll(orderlyClasses.length, noNeedOrder);
        return needOrder.subList(0, processors.size());
    }
}
