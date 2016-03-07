package link.ebbinghaus.planning.custom.other;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

import link.ebbinghaus.planning.model.entity.sys.SystemInfo;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class App extends Application {
    private static Context sContext;
    private static SystemInfo sSystemInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        initSystemInfo();
    }

    private void initSystemInfo() {
        sSystemInfo = new SystemInfo();
        WindowManager wm = (WindowManager) sContext.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        sSystemInfo.setWindowWidth(point.x);
        sSystemInfo.setWindowHeight(point.y);
    }


    public static Context getContext(){
        return sContext;
    }

    public static SystemInfo getSystemInfo(){
        return sSystemInfo;
    }
}
