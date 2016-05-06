package link.ebbinghaus.planning.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import link.ebbinghaus.planning.app.service.SyncLocalDatabaseService;

public class SyncLocalDatabaseReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, SyncLocalDatabaseService.class);
        context.startService(i);
    }
}
