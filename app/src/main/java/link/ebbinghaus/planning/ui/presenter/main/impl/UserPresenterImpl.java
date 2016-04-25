package link.ebbinghaus.planning.ui.presenter.main.impl;

import link.ebbinghaus.planning.core.service.MainService;
import link.ebbinghaus.planning.core.service.impl.MainServiceImpl;
import link.ebbinghaus.planning.ui.presenter.main.UserPresenter;
import link.ebbinghaus.planning.ui.view.main.UserView;

/**
 * Created by WINFIELD on 2016/4/21.
 */
public class UserPresenterImpl implements UserPresenter{
    private UserView mView;
    private MainService mMainService;

    public UserPresenterImpl(UserView view) {
        this.mView = view;
        mMainService = new MainServiceImpl();
    }
}
