package com.yurikami.lib.util;

import android.content.ContentValues;

/**
 * 辅助类,帮助ContentValues实例进行put时剔除value为空的情况
 */
public class NonNullContentValues {
    private ContentValues values;

    public NonNullContentValues(ContentValues values) {
        this.values = values;
    }

    public void put(String key, String value) {
        if (value != null) {
            values.put(key, value);
        }
    }


    public void put(String key, Byte value) {
        if (value != null) {
            values.put(key, value);
        }
    }


    public void put(String key, Short value) {
        if (value != null) {
            values.put(key, value);
        }
    }


    public void put(String key, Integer value) {
        if (value != null) {
            values.put(key, value);
        }
    }


    public void put(String key, Long value) {
        if (value != null) {
            values.put(key, value);
        }
    }


    public void put(String key, Float value) {
        if (value != null) {
            values.put(key, value);
        }
    }


    public void put(String key, Double value) {
        if (value != null) {
            values.put(key, value);
        }
    }


    public void put(String key, Boolean value) {
        if (value != null) {
            values.put(key, value);
        }
    }


    public void put(String key, byte[] value) {
        if (value != null) {
            values.put(key, value);
        }
    }
}
