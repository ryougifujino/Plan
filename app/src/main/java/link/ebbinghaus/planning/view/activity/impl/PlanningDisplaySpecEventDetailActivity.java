package link.ebbinghaus.planning.view.activity.impl;

import android.os.Bundle;

import com.yurikami.lib.base.BaseActivity;

import link.ebbinghaus.planning.custom.constant.Constant;
import link.ebbinhaus.planning.R;

public class PlanningDisplaySpecEventDetailActivity extends BaseActivity {
    public static final String INTENT_NAME_EVENT = Constant.PACKAGE_NAME + "Event";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_display_spec_event_detail);
    }
}
