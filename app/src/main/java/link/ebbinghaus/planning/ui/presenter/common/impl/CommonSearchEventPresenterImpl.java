package link.ebbinghaus.planning.ui.presenter.common.impl;

import link.ebbinghaus.planning.ui.presenter.common.CommonSearchEventPresenter;
import link.ebbinghaus.planning.ui.view.common.CommonSearchEventView;

/**
 * Created by WINFIELD on 2016/10/18.
 */

public class CommonSearchEventPresenterImpl implements CommonSearchEventPresenter {

    private CommonSearchEventView mView;

    public CommonSearchEventPresenterImpl(CommonSearchEventView view) {
        this.mView = view;
    }
}
