package link.ebbinghaus.planning.app.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.util.UniqueUtils;
import link.ebbinghaus.planning.core.model.local.vo.planning.build.InputEventVo;

/**
 * Created by WINFIELD on 2016/5/7.
 */
public class NotificationAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        InputEventVo inputEvent = intent.getParcelableExtra("NotificationInformation");
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.common_black_diamond)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("计划提醒")
                .setContentText(inputEvent.getDescription())
                .setTicker("ticker");
        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }else {
            notification = builder.getNotification();
        }
        notification.defaults = Notification.DEFAULT_ALL;
        nm.notify(UniqueUtils.getLoopId("NotificationId"),notification);
    }
}
