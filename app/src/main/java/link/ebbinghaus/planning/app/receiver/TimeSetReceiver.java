package link.ebbinghaus.planning.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yurikami.lib.util.LogUtils;

import link.ebbinghaus.planning.app.service.AmendDatabaseProcessAndRelatedService;

public class TimeSetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AmendDatabaseProcessAndRelatedService.class);
        context.startService(i);
        LogUtils.d("update","Time Set");
    }
}
