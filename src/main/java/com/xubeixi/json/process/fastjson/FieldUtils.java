package com.xubeixi.json.process.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

import java.lang.reflect.Field;

/**
 * @author xubeixi
 * @date 2021-04-16 16:48
 * 属性工具类
 */
class FieldUtils {

    /**
     * 递归获取域对象，子类找不到就找父类，直到找到或递归到Object为止
     * @param clazz 序列化对象的Class类
     * @param fieldName 属性名
     * @return 属性对象
     */
    static Field getField(Class<?> clazz, String fieldName){
        //获取父类class
        Class<?> superclass = clazz.getSuperclass();
        if (null == superclass){
            //父类为空证明是Object类，结束递归
            return null;
        }
        Field declaredField = null;
        try {
            //忽略报错
            declaredField = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            //此处忽略报错，递归查找
            return getField(superclass, fieldName);
        }
        //找到了返回
        return declaredField;
    }

    /**
     * 根据域名找到真正的域对象
     * @param clazz 序列化对象的Class类
     * @param fieldName 属性名
     * @return 属性对象
     */
    static Field getResultField(Class<?> clazz, String fieldName){
        Class<?> superclass = clazz.getSuperclass();
        if (null == superclass){
            //父类为空证明是Object类，结束递归
            return null;
        }
        //遍历全部的域
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if (!field.isAnnotationPresent(JSONField.class)){
                continue;
            }
            JSONField jsonField = field.getAnnotation(JSONField.class);
            if (fieldName.equals(jsonField.name())){
                return field;
            }
        }
        return getResultField(superclass, fieldName);
    }
}
