package com.xubeixi.json.process;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xubeixi
 * @date 2021-04-25 21:56
 * 加工器工厂
 */
public class ProcessorFactory {
    private static boolean enable = false;

    private static List<ProcessorDefinition> processorDefinitions = new ArrayList<>();

    public static void open() {
        enable = true;
    }

    /**
     * 添加加工器
     * @param processorDefinition 加工器
     */
    public static void addProcessor(ProcessorDefinition processorDefinition) {
        if (processorDefinition != null
                && processorDefinition.getAnnotationClass() != null
                && processorDefinition.getProcessValue() != null) {
            processorDefinitions.add(processorDefinition);
        }
    }

    public static boolean isEnable() {
        return enable;
    }

    public static List<ProcessorDefinition> getProcessorDefinitions() {
        return processorDefinitions;
    }
}
