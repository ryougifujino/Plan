package com.yurikami.lib.net.parser.impl;

import com.yurikami.lib.net.parser.Parser;

import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by WINFIELD on 2016/4/22.
 */
public class InputStreamParser implements Parser<InputStream> {

    @Override
    public InputStream parse(Response response) {
        return response.body().byteStream();
    }
}
