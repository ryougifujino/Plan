package link.ebbinghaus.planning.presenter.impl;

import link.ebbinghaus.planning.presenter.CommonSelectPresenter;
import link.ebbinghaus.planning.view.activity.CommonSelectView;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class CommonSelectPresenterImpl implements CommonSelectPresenter {
    private CommonSelectView mCommonSelectView;

    public CommonSelectPresenterImpl(CommonSelectView commonSelectView) {
        this.mCommonSelectView = commonSelectView;
    }

    @Override
    public void configureToolbar() {
        mCommonSelectView.setToolbar();
        mCommonSelectView.initToolbarAddDialog();
    }

    @Override
    public void getAndSetSenderData() {
        mCommonSelectView.setSenderData();
    }

    @Override
    public void configureRecyclerView() {
        mCommonSelectView.chooseRecyclerViewAdapter();
        mCommonSelectView.setRecyclerView();
        mCommonSelectView.setOnCreateButtonClickListener();
    }

    @Override
    public void addNewItemToDatabaseAndRefreshUi(String content) {

    }

}
