package com.yurikami.lib.net.parser.impl;

import com.google.gson.Gson;
import com.yurikami.lib.net.parser.Parser;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by WINFIELD on 2016/4/20.
 */
public class ModelParser<T> implements Parser<T> {
    private Class<T> mClass;
    private Type mTypeOfT;

    /**
     * T中没有泛型的用这个构造函数<br>
     * 或者 new TypeToken<类型>(){}.getRawType()
     */
    public ModelParser(Class<T> clazz) {
        if (clazz==null){
            throw new IllegalArgumentException("Class can't be null");
        }
        this.mClass = clazz;
    }

    /** T中有泛型的用这个构造函数 */
    public ModelParser(Type typeOfT) {
        if (typeOfT==null){
            throw new IllegalArgumentException("typeOfT can't be null");
        }
        this.mTypeOfT = typeOfT;
    }

    @Override
    public T parse(Response response) {
        Gson gson = new Gson();
        try {
            String json = response.body().string();
            if (mClass != null){
                return gson.fromJson(json,mClass);
            }
            return gson.fromJson(json,mTypeOfT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
