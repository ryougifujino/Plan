package com.yurikami.lib.util;

/**
 * 不好划分类型的工具
 */
public class Utils {

    /**
     * 将int数值转换为布尔型数值
     * @param value 目标int数值
     * @return 1的话返回true,其他返回false
     */
    public static boolean int2Bool(int value){
        return value == 1 ? true : false;
    }

    /**
     * 判断目标对象是否包含有为null的对象
     * @param objs 目标对象
     * @return 如果目标对象有任何一个为null,则返回true,否则返回false
     */
    public static boolean isAnyNull(Object... objs){
        for (Object obj : objs) {
            if (obj == null){
                return true;
            }
        }
        return false;
    }


    /**
     * 参数可为null的布尔值相等判断
     * @param boolean1
     * @param boolean2
     * @return true:两个值的布尔值一致或都为null false:两个值的布尔值不一致或者其中一个为null
     */
    public static boolean equals(Boolean boolean1,Boolean boolean2){
        return boolean1 == boolean2;
    }

    /**
     * 获取LruCache缓存用的推荐内存大小(单位KB)
     * @return LruCache缓存用的推荐内存大小
     */
    public static int cacheMemory(){
        return (int) (Runtime.getRuntime().maxMemory() / 1024 / 8);
    }

}
