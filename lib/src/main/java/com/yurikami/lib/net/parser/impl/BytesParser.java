package com.yurikami.lib.net.parser.impl;

import com.yurikami.lib.net.parser.Parser;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by WINFIELD on 2016/4/22.
 */
public class BytesParser implements Parser<byte[]> {

    @Override
    public byte[] parse(Response response) {
        try {
            return response.body().bytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
