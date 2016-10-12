package link.ebbinghaus.planning.ui.view.common.activity;

import android.os.Bundle;

import com.yurikami.lib.base.BaseActivity;

import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.view.common.CommonSearchEventView;

public class CommonSearchEventActivity extends BaseActivity implements CommonSearchEventView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_search_event);
    }
}
