package com.mc.block.commom;

public class StringUtils {
    /**
     * String 非null, 非空校验
     * @param s
     * @return
     */
    public static boolean isEmpty(String s){
        return s==null||"".equals(s);
    }

    /**
     * String 非null, 非空校验 且去掉前后空格
     * @param s
     * @return
     */
    public static boolean isEmptyAndTrim(String s){
        return s==null||"".equals(s.trim());
    }

    public static boolean isNotBlank(String s){
        return !isEmpty(s);
    }

    /**
     *  [str,str] 字符串转数组
     * @param str
     * @return
     */
    public static String[] toArray(String str){
        if(str != null && str.startsWith("[") && str.endsWith("]")){
           return str.substring(1, str.length() - 1).trim().split(",");
        }
        return null;
    }
}
