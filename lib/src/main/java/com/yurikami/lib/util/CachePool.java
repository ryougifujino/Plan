package com.yurikami.lib.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WINFIELD on 2016/3/7.
 */
public class CachePool<K,V> {
    private static CachePool instance;
    private Map<K,V> mCaches;

    private CachePool() {
        this.mCaches = new HashMap<>();
    }

    public synchronized static CachePool getInstance(){
        if(instance == null){
            instance = new CachePool();
        }
        return instance;
    }

    public void clearCaches(){
        mCaches.clear();
    }

    /**
     * 在Activity或者Fragment生命周期结束时调用
     */
    public synchronized void destroyCachePool(){
        clearCaches();
        instance = null;
    }

    public V getCache(K key){
        return mCaches.get(key);
    }

    public boolean putCache(K key, V value){
        mCaches.put(key,value);
        return true;
    }
}
