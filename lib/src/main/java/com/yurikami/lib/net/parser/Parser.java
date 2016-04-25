package com.yurikami.lib.net.parser;

import okhttp3.Response;

/**
 * response解析器
 */
public interface Parser<T> {

    /**
     * 解析response
     * @param response
     * @return 结果
     */
    T parse(Response response);
}
