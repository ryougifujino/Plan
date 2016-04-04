package com.yurikami.lib.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by WINFIELD on 2016/3/23.
 */
public class ViewUtils {

    /**
     * 设置一个attr资源下的背景
     * @param context 上下文
     * @param target 要设置背景的目标view
     * @param attrRes attr资源
     */
    public static void setAttrBackground(Context context,View target,int attrRes){
        int[] attrs = new int[]{attrRes};
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        target.setBackgroundResource(backgroundResource);
        typedArray.recycle();
    }

    /**
     * 把一个图片转换为黑白图片
     * @param drawable 目标图片
     * @return 目标图片的黑白图片
     */
    public static Drawable convertToGrayscale(Drawable drawable)
    {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        drawable.setColorFilter(filter);
        return drawable;
    }
}
