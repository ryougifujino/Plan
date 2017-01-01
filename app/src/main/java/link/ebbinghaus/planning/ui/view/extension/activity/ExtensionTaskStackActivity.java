package link.ebbinghaus.planning.ui.view.extension.activity;

import android.os.Bundle;

import com.yurikami.lib.base.BaseActivity;

import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.view.extension.ExtensionTaskStackView;

public class ExtensionTaskStackActivity extends BaseActivity implements ExtensionTaskStackView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extension_task_stack);
    }
}
