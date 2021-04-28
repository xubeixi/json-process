package com.xubeixi.json.process;

/**
 * @author xubeixi
 * @date 2021-04-25 17:40
 * JSON序列化时对指定的属性值进行加工
 */
@FunctionalInterface
public interface ProcessValue<T> {
    /**
     * 加工条件
     * @param value 属性值
     * @return 是否需要加工
     */
    default boolean processCondition(Object value) {
        return true;
    }

    /**
     * 加工逻辑
     * @param value      属性值
     * @param annotation 自定义的注解对象
     * @return 加工后的结果
     */
    Object processLogic(Object value, T annotation);
}
