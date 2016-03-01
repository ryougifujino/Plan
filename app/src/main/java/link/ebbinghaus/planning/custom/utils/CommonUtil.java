package link.ebbinghaus.planning.custom.utils;

import android.widget.Toast;

import link.ebbinghaus.planning.custom.other.CustomApplication;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class CommonUtil {
    private static Toast toast;
    public static void showShortToast(String msg){
        showToast(msg, Toast.LENGTH_LONG);
    }
    public static void showLongToast(String msg){
        showToast(msg, Toast.LENGTH_SHORT);
    }
    public static void showToast(String msg, int duration){
        if (toast == null){
            toast = Toast.makeText(CustomApplication.getContext(), msg, duration);
        } else{
            toast.setText(msg);
            toast.setDuration(duration);
        }
        toast.show();
    }
}
