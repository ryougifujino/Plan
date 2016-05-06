package com.yurikami.lib.net.parser.impl;

import com.google.gson.Gson;
import com.yurikami.lib.net.parser.Parser;
import com.yurikami.lib.util.JsonUtils;

import java.io.Reader;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by WINFIELD on 2016/4/20.
 */
public class BigModelParser<T> implements Parser<T> {
    private Class<T> mClass;
    private Type mTypeOfT;

    /**
     * T中没有泛型的用这个构造函数<br>
     * 或者 new TypeToken<类型>(){}.getRawType()
     */
    public BigModelParser(Class<T> clazz) {
        if (clazz==null){
            throw new IllegalArgumentException("Class can't be null");
        }
        this.mClass = clazz;
    }

    /** T中有泛型的用这个构造函数 */
    public BigModelParser(Type typeOfT) {
        if (typeOfT==null){
            throw new IllegalArgumentException("typeOfT can't be null");
        }
        this.mTypeOfT = typeOfT;
    }

    @Override
    public T parse(Response response) {
        Gson gson = JsonUtils.create();
        Reader json = response.body().charStream();
        if (mClass != null){
            return gson.fromJson(json,mClass);
        }
        return gson.fromJson(json,mTypeOfT);
    }
}