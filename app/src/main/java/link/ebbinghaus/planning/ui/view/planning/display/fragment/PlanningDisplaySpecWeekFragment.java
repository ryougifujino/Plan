package link.ebbinghaus.planning.ui.view.planning.display.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;
import com.yurikami.lib.entity.Datetime;
import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.ui.adapter.planning.display.spec.WeekRecyclerViewAdapter;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplaySpecWeekPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.display.impl.PlanningDisplaySpecWeekPresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplaySpecWeekView;
import link.ebbinghaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningDisplaySpecWeekFragment extends BaseFragment implements PlanningDisplaySpecWeekView,
        PlanningDisplayFragment.OnToolbarDateChangeListener{

    @Bind(R.id.rv_planning_display_spec_week) RecyclerView mRecyclerView;
    private WeekRecyclerViewAdapter mWeekRecyclerViewAdapter;
    private PlanningDisplaySpecWeekPresenter mPresenter;
    private Datetime mDateOfToday = DateUtils.dateOfToday();
    //标识变量,用于控制当执行了onCreateView后,onResume不再重复执行渲染工作
    private boolean isCallOnCreateView = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_spec_week, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new PlanningDisplaySpecWeekPresenterImpl(this);
        mPresenter.initWeekView();

        return v;
    }

    @Override
    public void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mWeekRecyclerViewAdapter = new WeekRecyclerViewAdapter(mActivity,mPresenter.obtainSpecWeekEvents(mDateOfToday));
        mRecyclerView.setAdapter(mWeekRecyclerViewAdapter);

    }

    @Override
    public void registerToolbarDateChangeListener() {
        PlanningDisplayFragment planningDisplayFragment = (PlanningDisplayFragment) getParentFragment().getParentFragment();
        planningDisplayFragment.addOnToolbarDateChangeListener(this);
    }

    @Override
    public void setOnCreateViewFlag() {
        isCallOnCreateView = true;
    }

    @Override
    public void onDateChanged(Datetime datetime) {
        mDateOfToday = datetime;
        mWeekRecyclerViewAdapter.refresh(mPresenter.obtainSpecWeekEvents(mDateOfToday));
        LogUtils.d(TAG,"onDateChanged");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isCallOnCreateView){
            mWeekRecyclerViewAdapter.refresh(mPresenter.obtainSpecWeekEvents(mDateOfToday));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isCallOnCreateView = false;
    }
}
