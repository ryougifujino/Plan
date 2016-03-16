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
}
