package com.yurikami.lib.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by WINFIELD on 2016/3/7.
 */
public class ViewGroupUtils {

    public static LinearLayout newLinearLayout(Context context, ViewGroup.LayoutParams lp,int orientation){
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(orientation);
        ll.setLayoutParams(lp);
        return ll;
    }

    public static LinearLayout newHorizontalLl(Context context, ViewGroup.LayoutParams lp){
        return newLinearLayout(context, lp, LinearLayout.HORIZONTAL);
    }

    public static LinearLayout newVerticalLl(Context context, ViewGroup.LayoutParams lp){
        return newLinearLayout(context, lp, LinearLayout.VERTICAL);
    }

    public static void snipParent(ViewGroup viewGroup){
        if (viewGroup != null) {
            ViewGroup parent = (ViewGroup) viewGroup.getParent();
            if (parent != null) {
                parent.removeView(viewGroup);
            }
        }
    }

}
