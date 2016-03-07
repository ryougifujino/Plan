package link.ebbinghaus.planning.custom.util;

import android.widget.Toast;

import link.ebbinghaus.planning.custom.other.App;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class CommonUtils {
    private static Toast sToast;
    public static void showShortToast(String msg){
        showToast(msg, Toast.LENGTH_LONG);
    }
    public static void showLongToast(String msg){
        showToast(msg, Toast.LENGTH_SHORT);
    }
    public static void showToast(String msg, int duration){
        if (sToast == null){
            sToast = Toast.makeText(App.getContext(), msg, duration);
        } else{
            sToast.setText(msg);
            sToast.setDuration(duration);
        }
        sToast.show();
    }
}
