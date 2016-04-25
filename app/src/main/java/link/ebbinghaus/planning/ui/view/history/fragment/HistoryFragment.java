package link.ebbinghaus.planning.ui.view.history.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
    private FinishRecyclerViewAdapter mFinishRecyclerViewAdapter;
    private HistoryPresenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new HistoryPresenterImpl(this);
        initHistory();

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initHistory() {
        mActivity.setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.history_title);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mFinishRecyclerViewAdapter = new FinishRecyclerViewAdapter(mActivity,mPresenter.obtainSpecDoneEvents());
        mRecyclerView.setAdapter(mFinishRecyclerViewAdapter);
    }
}
