package link.ebbinghaus.planning.view.activity.impl;

import android.os.Bundle;

import com.yurikami.lib.base.BaseActivity;

import link.ebbinghaus.planning.view.activity.PlanningBuildView;
import link.ebbinhaus.planning.R;

public class PlanningBuildActivity extends BaseActivity implements PlanningBuildView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_build);
    }
}
