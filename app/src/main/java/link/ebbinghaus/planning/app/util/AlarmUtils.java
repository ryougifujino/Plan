package link.ebbinghaus.planning.app.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.LogUtils;
import com.yurikami.lib.util.Utils;

import link.ebbinghaus.planning.app.App;
import link.ebbinghaus.planning.app.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.app.constant.config.entity.LearningEventGroupConfig;
import link.ebbinghaus.planning.app.receiver.NotificationAlarmReceiver;
import link.ebbinghaus.planning.core.model.local.vo.planning.build.InputEventVo;

/**
 * Created by WINFIELD on 2016/5/7.
 */
public class AlarmUtils {

    /**
     * 设置一个通知提醒闹钟
     * @param context
     * @param alarmTime 闹钟相应的时间戳
     * @param inputEvent
     */
    public static void setNotificationAlarm(Context context, long alarmTime, InputEventVo inputEvent){
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, NotificationAlarmReceiver.class);
        i.putExtra("NotificationInformation",inputEvent);
        PendingIntent pi = PendingIntent.getBroadcast(context, UniqueUtils.getLoopId("BroadcastRequestCode"), i, 0);

        //确保执行的准确性
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            manager.setExact(AlarmManager.RTC_WAKEUP, alarmTime, pi);
        }else {
            manager.set(AlarmManager.RTC_WAKEUP, alarmTime, pi);
        }
    }

     /** 设置一个通知闹钟（普通和学习计划都适用）（如果event里的isRemind为true的话，为false将不执行）*/
    public static void setNotificationAlarm(Context context, InputEventVo inputEvent){
        if (Utils.isAnyNull(inputEvent,inputEvent.getIsRemind(),inputEvent.getRemindTime(),inputEvent.getEventExpectedFinishedDate())){
            LogUtils.e("NotificationAlarm","notification time is null");
            return;
        }
        if (inputEvent.getEventType() == null){
            LogUtils.e("NotificationAlarm","notification eventType is null");
            return;
        }
        if (inputEvent.getIsRemind()){
            if (inputEvent.getEventType() == EventConfig.TYPE_SPEC_NORMAL){
                setNotificationAlarm(context,inputEvent.getRemindTime() + inputEvent.getEventExpectedFinishedDate(), inputEvent);
            }else if (inputEvent.getEventType() == EventConfig.TYPE_SPEC_LEARNING){
                //若是学习计划则批量设置
                if (inputEvent.getStrategy() == null){
                    LogUtils.e("NotificationAlarm","notification strategy is null");
                    return;
                }
                //此学习计划的方案
                int [] thisStrategy = LearningEventGroupConfig.STRATEGY[inputEvent.getStrategy()];
                //为每个学习计划项设置提醒
                long firstNotifyTime = inputEvent.getRemindTime() + inputEvent.getEventExpectedFinishedDate();
                for (int i = 0; i < thisStrategy.length;i++){
                    long notifyTime = DateUtils.timestampAfter(firstNotifyTime,thisStrategy[i] - 1);
                    setNotificationAlarm(context,notifyTime, inputEvent);
                }
            }
        }
    }

    /** 设置一个通知闹钟（普通和学习计划都适用）（如果inputEvent里的isRemind为true的话，为false将不执行） */
    public static void setNotificationAlarm(InputEventVo inputEvent){
        setNotificationAlarm(App.getContext(),inputEvent);  //TODO check App.getContext()是否合理
    }
}
