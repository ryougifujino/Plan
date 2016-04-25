package link.ebbinghaus.planning.ui.view.main.activity;

import android.os.Bundle;

import com.yurikami.lib.base.BaseActivity;

import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.view.main.RegisterView;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
