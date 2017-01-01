package link.ebbinghaus.planning.app.util;

import android.widget.Toast;

import link.ebbinghaus.planning.app.App;

/**
 * 依赖于整个App的Context但不依赖于业务的工具类
 */
public class CommonUtils {
    private static Toast sToast;
    public static void showShortToast(String msg){
        showToast(msg, Toast.LENGTH_LONG);
    }
    public static void showLongToast(String msg){
        showToast(msg, Toast.LENGTH_SHORT);
    }
    public static void showShortToast(int msgRes){
        showToast(App.getContext().getString(msgRes), Toast.LENGTH_LONG);
    }
    public static void showLongToast(int msgRes){
        showToast(App.getContext().getString(msgRes), Toast.LENGTH_SHORT);
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
