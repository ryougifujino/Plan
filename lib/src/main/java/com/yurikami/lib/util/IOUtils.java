package com.yurikami.lib.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by WINFIELD on 2016/4/2.
 */
public class IOUtils {

    /**
     * 从Assets文件夹里获取一张位图
     * @param context 上下文
     * @param bitmapPath 位图相对Assets的路径
     * @return 位图
     */
    public static Bitmap getBitmapFromAssets(Context context, String bitmapPath){
        Bitmap bitmap = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(bitmapPath);
            bitmap  = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
