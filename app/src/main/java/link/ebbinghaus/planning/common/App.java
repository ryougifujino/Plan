package link.ebbinghaus.planning.common;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

import link.ebbinghaus.planning.core.db.DBHelper;
import link.ebbinghaus.planning.core.model.sys.SystemInfo;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class App extends Application {
    private static Context sContext;
    private static SystemInfo sSystemInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext(); /*! 这句话必须在这里 */

        //一些初始化操作(轻量级)
        initSystemInfo();
        DBHelper.createDatabase();
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
