package com.mc.block.commom;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ClassUtils {

    /**
     * 对象属性转Map集合
     * @param obj 转化对象
     * @param ignoreNull 是否转化null值
     * @param ignore 忽略属性
     * @return HashMap
     */
    public static HashMap<String, Object> fields(Object obj, boolean ignoreNull, String ... ignore){
        HashMap<String, Object> map = new HashMap<>();
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        fort: for (Field field : fields) {
                for (String s : ignore) {
                    if(s.equals(field.getName())){
                        continue fort;
                    }
                }
                field.setAccessible(true);
                try {
                    if(ignoreNull && field.get(obj) == null){
                        continue;
                    }
                    map.put(field.getName(), field.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                field.setAccessible(false);
        }
        return map;
    }
}
