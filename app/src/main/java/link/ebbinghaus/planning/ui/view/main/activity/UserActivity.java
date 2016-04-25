package link.ebbinghaus.planning.ui.view.main.activity;

import android.os.Bundle;

import com.yurikami.lib.base.BaseActivity;

import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.presenter.main.UserPresenter;
import link.ebbinghaus.planning.ui.presenter.main.impl.UserPresenterImpl;
import link.ebbinghaus.planning.ui.view.main.UserView;

public class UserActivity extends BaseActivity implements UserView {

    private UserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mPresenter = new UserPresenterImpl(this);

    }
}
