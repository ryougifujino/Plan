package com.yurikami.lib.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by WINFIELD on 2016/4/21.
 */
public class SharedPreferencesUtils {

    @SuppressLint("CommitPrefEdits")
    private static SharedPreferences.Editor save(Context context,String saveKey,Object model){
         SharedPreferences.Editor editor = context.getSharedPreferences(saveKey, Context.MODE_PRIVATE).edit();
        editor.putString(saveKey,new Gson().toJson(model));
        return editor;
    }

    /**
     * 异步保存一个对象到SharedPreferences里
     * @param context
     * @param saveKey SharedPreferences和putString时的key
     * @param model 欲持久化的对象
     */
    public static void saveAsync(Context context,String saveKey,Object model){
        save(context,saveKey,model).apply();
    }

    /**
     * 同步保存一个对象到SharedPreferences里
     * @param context
     * @param saveKey SharedPreferences和putString时的key
     * @param model 欲持久化的对象
     */
    public static void saveSync(Context context,String saveKey,Object model){
        save(context,saveKey,model).commit();
    }

    /** 从SharedPreference中获取Model */
    public static <T> T getModel(Context context,String getKey,Class<T> clazz){
        SharedPreferences sp = context.getSharedPreferences(getKey,Context.MODE_PRIVATE);
        try {
            return new Gson().fromJson(sp.getString(getKey,""),clazz);
        }catch (JsonSyntaxException e){
            LogUtils.d("Allowed error",e.getMessage());
        }
        return null;
    }

    /** 从SharedPreference中获取Model */
    public static Object getModel(Context context, String getKey, Type type){
        SharedPreferences sp = context.getSharedPreferences(getKey,Context.MODE_PRIVATE);
        try {
            return new Gson().fromJson(sp.getString(getKey,""),type);
        }catch (JsonSyntaxException e){
            LogUtils.d("Allowed error",e.getMessage());
        }
        return null;
    }
}
