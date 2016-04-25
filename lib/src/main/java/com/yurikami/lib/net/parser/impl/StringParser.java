package com.yurikami.lib.net.parser.impl;

import java.io.IOException;

import com.yurikami.lib.net.parser.Parser;
import okhttp3.Response;

/**
 * Created by WINFIELD on 2016/4/20.
 */
public class StringParser implements Parser<String> {

    @Override
    public String parse(Response response) {
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
