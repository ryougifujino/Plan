package link.ebbinghaus.planning.app.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.yurikami.lib.util.DateUtils;

import java.util.List;

import link.ebbinghaus.planning.app.App;
import link.ebbinghaus.planning.app.constant.config.entity.LearningEventGroupConfig;
import link.ebbinghaus.planning.app.receiver.NotificationAlarmReceiver;
import link.ebbinghaus.planning.core.model.local.vo.planning.build.InputEventVo;

/**
 * Created by WINFIELD on 2016/5/7.
 */
public class AlarmUtils {

    /**
     * 设置一个通知提醒闹钟
     *
     * @param context 上下文
     * @param alarmTime  闹钟相应的时间戳
     * @param inputEvent 用来携带通知的信息
     * @param id 用来设置消息的id（和event表里的id相对于）
     */
    public static void setNotificationAlarm(Context context, long alarmTime, InputEventVo inputEvent, Long id) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, NotificationAlarmReceiver.class);
        i.putExtra("NotificationInformation", inputEvent);
        i.putExtra("NotificationId", id.intValue());
        // PendingIntent pi = PendingIntent.getBroadcast(context, UniqueUtils.getLoopId("BroadcastRequestCode"), i, 0); // TODO: 2016/8/14 看看id的作用
        //http://stackoverflow.com/questions/11681095/cancel-an-alarmmanager-pendingintent-in-another-pendingintent
        PendingIntent pi = PendingIntent.getBroadcast(context, id.intValue(), i, 0);

        //确保执行的准确性
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            manager.setExact(AlarmManager.RTC_WAKEUP, alarmTime, pi);
        } else {
            manager.set(AlarmManager.RTC_WAKEUP, alarmTime, pi);
        }
    }

    /**
     * 取消一个通知提醒闹钟
     * @param context 上下文
     * @param ids 取消的闹钟的id（和event表里的id所对应）
     */
    public static void cancelNotificationAlarm(Context context,List<Long> ids){
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, NotificationAlarmReceiver.class);

        for (Long id : ids) {
            PendingIntent pi = PendingIntent.getBroadcast(context, id.intValue(), i, 0);
            pi.cancel();
            manager.cancel(pi);
        }
    }

    /**
     * @see #cancelNotificationAlarm
     */
    public static void cancelNotificationAlarm(List<Long> ids) {
        cancelNotificationAlarm(App.getContext(), ids);
    }

    /**
     * 给普通计划添加通知闹钟
     * @see #setNotificationAlarm
     */
    public static void setNotificationAlarm(Context context, InputEventVo inputEvent, Long normalId) {
        setNotificationAlarm(context, inputEvent.getRemindTime() + inputEvent.getEventExpectedFinishedDate(), inputEvent, normalId);
    }

    /**
     * 给学习计划添加通知闹钟
     * @see #setNotificationAlarm
     */
    public static void setNotificationAlarm(Context context, InputEventVo inputEvent, List<Long> learningIds) {
        //此学习计划的方案
        int[] aStrategy = LearningEventGroupConfig.STRATEGY[inputEvent.getStrategy() - 1];
        //为每个学习计划项设置提醒
        long firstNotifyTime = inputEvent.getRemindTime() + inputEvent.getEventExpectedFinishedDate();
        for (int i = 0; i < aStrategy.length; i++) {
            long notifyTime = DateUtils.timestampAfter(firstNotifyTime, aStrategy[i] - 1);
            setNotificationAlarm(context, notifyTime, inputEvent, learningIds.get(i));
        }
    }


    /**
     * 给普通计划设置通知闹钟
     */
    public static void setNotificationAlarm(InputEventVo inputEvent, Long normalId) {
        if (inputEvent.getIsRemind()) {
            setNotificationAlarm(App.getContext(), inputEvent, normalId); //TODO check App.getContext()是否合理
        }
    }

    /**
     * 给学习计划设置通知闹钟
     */
    public static void setNotificationAlarm(InputEventVo inputEvent, List<Long> learningIds) {
        if (inputEvent.getIsRemind()) {
            setNotificationAlarm(App.getContext(), inputEvent, learningIds);
        }
    }
}
