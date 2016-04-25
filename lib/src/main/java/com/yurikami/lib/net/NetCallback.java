package com.yurikami.lib.net;

import okhttp3.Call;

/**
 * 网络回调，返回结果用
 */
public interface NetCallback {

    /**
     * 网络请求成功（并且200<=code<300）时调用
     * @param result 请求结果
     * @param call 请求
     */
    void onSuccess(Object result, Call call);

    /**
     * 网络请求失败时调用
     * @param errorMsg 错误信息
     * @param call 请求
     */
    void onFailure(String errorMsg, Call call);
}
