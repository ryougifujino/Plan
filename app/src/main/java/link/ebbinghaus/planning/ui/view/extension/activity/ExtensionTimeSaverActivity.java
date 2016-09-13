package link.ebbinghaus.planning.ui.view.extension.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yurikami.lib.base.BaseActivity;

import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.view.extension.ExtensionTimeSaverView;

public class ExtensionTimeSaverActivity extends BaseActivity implements ExtensionTimeSaverView{

    static class MyHandler extends Handler{
        
        private int seconds = 0;
        private boolean isContinueCount = true;

        @Override
        public void handleMessage(Message msg) {
            seconds++;
            if (isContinueCount){
                sendEmptyMessageDelayed(0,1000);
            }
        }

        void startTimer(){
            seconds = 0;
            isContinueCount = true;
            sendEmptyMessageDelayed(0,1000);
        }

        int stopTimer(){
            isContinueCount = false;
            return seconds;
        }
    }

    private MyHandler mHandler = new MyHandler();
    
    private TextView timeDisplay;
    private Button start;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extension_time_saver);
        
        timeDisplay = (TextView) findViewById(R.id.time_display);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.startTimer();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDisplay.setText(String.valueOf(mHandler.stopTimer()));
            }
        });
    }
}
