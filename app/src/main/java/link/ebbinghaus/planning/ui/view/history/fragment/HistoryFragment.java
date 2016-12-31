package link.ebbinghaus.planning.ui.view.history.fragment;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.adapter.history.HistoryRecyclerViewAdapter;
import link.ebbinghaus.planning.ui.presenter.history.HistoryPresenter;
import link.ebbinghaus.planning.ui.presenter.history.impl.HistoryPresenterImpl;
import link.ebbinghaus.planning.ui.view.history.HistoryView;

public class HistoryFragment extends BaseFragment implements HistoryView {

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.rv_history_done) RecyclerView mDoneRecyclerView;
    @Bind(R.id.rv_history_expired) RecyclerView mExpiredRecyclerView;
    private DrawerLayout mDrawerLayout;
    private HistoryRecyclerViewAdapter mDoneAdapter;
    private HistoryRecyclerViewAdapter mExpiredAdapter;
    private HistoryPresenter mPresenter;

    private boolean isCallOnCreateView = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new HistoryPresenterImpl(this);
        configureToolbar();
        initHistory();

        isCallOnCreateView = true;
        return v;
    }

    private void configureToolbar() {
        mActivity.setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.history_title);
        mToolbar.setNavigationIcon(R.drawable.common_navigation_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        mDrawerLayout = (DrawerLayout) mActivity.findViewById(R.id.dl_main_whole);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isCallOnCreateView) {
            mDoneAdapter.refresh(mPresenter.obtainSpecDoneEvents());
            mExpiredAdapter.refresh(mPresenter.obtainSpecExpiredEvents());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isCallOnCreateView = false;
    }

    @Override
    public void initHistory() {
        mDoneRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.HORIZONTAL,false));
        mDoneAdapter = new HistoryRecyclerViewAdapter(mActivity,mPresenter.obtainSpecDoneEvents());
        mDoneRecyclerView.setAdapter(mDoneAdapter);

        mExpiredRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.HORIZONTAL,false));
        mExpiredAdapter = new HistoryRecyclerViewAdapter(mActivity,mPresenter.obtainSpecExpiredEvents());
        mExpiredRecyclerView.setAdapter(mExpiredAdapter);
    }
}
