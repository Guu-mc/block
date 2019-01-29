package com.mc.block.commom;

import java.io.*;
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

    /**
     * 对象转byte
     * @param obj
     * @return
     */
    public static byte[] objectToByte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(bo);
            close(oo);
        }
        return bytes;
    }

    /**
     * byte转对象
     * @param bytes
     * @return
     */
    public static Object byteToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            bi = new ByteArrayInputStream(bytes);
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(bi);
            close(oi);
        }
        return obj;
    }

    public static void close(Closeable io){
        if(io!=null){
            try {
                io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
