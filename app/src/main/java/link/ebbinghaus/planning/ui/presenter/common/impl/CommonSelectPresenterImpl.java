package link.ebbinghaus.planning.ui.presenter.common.impl;

import link.ebbinghaus.planning.ui.presenter.common.CommonSelectPresenter;
import link.ebbinghaus.planning.ui.view.common.CommonSelectView;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class CommonSelectPresenterImpl implements CommonSelectPresenter {
    private CommonSelectView mView;

    public CommonSelectPresenterImpl(CommonSelectView commonSelectView) {
        this.mView = commonSelectView;
    }

    @Override
    public void configureToolbar() {
        mView.setToolbar();
        mView.initToolbarAddDialog();
    }

    @Override
    public void getAndSetSenderData() {
        mView.setSenderData();
    }

    @Override
    public void configureRecyclerView() {
        mView.chooseRecyclerViewAdapter();
        mView.setRecyclerView();
        mView.setOnCreateButtonClickListener();
    }

}
