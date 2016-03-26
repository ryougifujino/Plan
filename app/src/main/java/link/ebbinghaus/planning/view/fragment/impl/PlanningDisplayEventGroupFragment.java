package link.ebbinghaus.planning.view.fragment.impl;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.adapter.planning.display.GroupRecyclerViewAdapter;
import link.ebbinghaus.planning.presenter.PlanningDisplayEventGroupPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningDisplayEventGroupPresenterImpl;
import link.ebbinghaus.planning.view.fragment.PlanningDisplayEventGroupView;
import link.ebbinhaus.planning.R;

/**
 * 具体计划和抽象计划<strong>共用</strong>的计划组Activity
 */
public class PlanningDisplayEventGroupFragment extends BaseFragment implements PlanningDisplayEventGroupView {
    public static final String KEY_FLAG = "Flag";
    /**
     * 具体:true 抽象:false
     */
    private boolean mFlag;
    private boolean isCallOnCreateView = false;
    private GroupRecyclerViewAdapter mGroupRecyclerViewAdapter;
    private PlanningDisplayEventGroupPresenter mPresenter;
    @Bind(R.id.rv_planning_display_event_group) RecyclerView mRecyclerView;

    /**
     * 创建一个具体计划组或抽象计划组Fragment
     * @param flag 具体:true 抽象:false
     * @return Fragment实例
     */
    public static PlanningDisplayEventGroupFragment newInstance(boolean flag){
        PlanningDisplayEventGroupFragment groupFragment = new PlanningDisplayEventGroupFragment();
        Bundle args = new Bundle();
        args.putBoolean(KEY_FLAG, flag);
        groupFragment.setArguments(args);
        return groupFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_event_group, container, false);
        ButterKnife.bind(this,v);
        mFlag = getArguments().getBoolean(KEY_FLAG);
        mPresenter = new PlanningDisplayEventGroupPresenterImpl(this);
        mPresenter.initEventGroupView();

        isCallOnCreateView = true;
        return v;
    }

    @Override
    public void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mGroupRecyclerViewAdapter = new GroupRecyclerViewAdapter(mActivity, mFlag);
        mRecyclerView.setAdapter(mGroupRecyclerViewAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isCallOnCreateView) {
            mGroupRecyclerViewAdapter.refresh();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isCallOnCreateView = false;
    }
}
