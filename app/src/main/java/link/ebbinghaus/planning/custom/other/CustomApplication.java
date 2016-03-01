package link.ebbinghaus.planning.custom.other;

import android.app.Application;
import android.content.Context;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class CustomApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
