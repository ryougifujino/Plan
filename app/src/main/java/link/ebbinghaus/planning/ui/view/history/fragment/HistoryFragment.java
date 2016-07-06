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
import link.ebbinghaus.planning.ui.adapter.planning.done.FinishRecyclerViewAdapter;
import link.ebbinghaus.planning.ui.presenter.history.HistoryPresenter;
import link.ebbinghaus.planning.ui.presenter.history.impl.HistoryPresenterImpl;
import link.ebbinghaus.planning.ui.view.history.HistoryView;

public class HistoryFragment extends BaseFragment implements HistoryView {

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.rv_history) RecyclerView mRecyclerView;
    private DrawerLayout mDrawerLayout;
    private FinishRecyclerViewAdapter mFinishRecyclerViewAdapter;
    private HistoryPresenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new HistoryPresenterImpl(this);
        configureToolbar();
        initHistory();

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
    public void initHistory() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        // TODO: 2016/5/8 用自定义的代替
        mFinishRecyclerViewAdapter = new FinishRecyclerViewAdapter(mActivity,mPresenter.obtainSpecDoneEvents());
        mRecyclerView.setAdapter(mFinishRecyclerViewAdapter);
    }
}
