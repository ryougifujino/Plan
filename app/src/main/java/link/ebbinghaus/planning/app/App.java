package link.ebbinghaus.planning.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.view.WindowManager;

import com.yurikami.lib.util.LogUtils;

import link.ebbinghaus.planning.app.service.AmendDatabaseProcessAndRelatedService;
import link.ebbinghaus.planning.core.db.DBHelper;
import link.ebbinghaus.planning.core.model.local.sys.SystemInfo;

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
        LogUtils.d("Application","Application onCreate()");
        initSystemInfo();
        DBHelper.createDatabase();
//        DBHelper.amendDatabaseProcessAndRelated();
        Intent intent = new Intent(this, AmendDatabaseProcessAndRelatedService.class);
        startService(intent);
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
