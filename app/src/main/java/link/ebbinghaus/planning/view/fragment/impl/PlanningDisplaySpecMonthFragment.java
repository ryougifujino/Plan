package link.ebbinghaus.planning.view.fragment.impl;


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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.adapter.planning.display.spec.MonthRecyclerViewAdapter;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.presenter.PlanningDisplaySpecMonthPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningDisplaySpecMonthPresenterImpl;
import link.ebbinghaus.planning.view.fragment.PlanningDisplaySpecMonthView;
import link.ebbinhaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningDisplaySpecMonthFragment extends BaseFragment implements PlanningDisplaySpecMonthView,
        PlanningDisplayFragment.OnToolbarDateChangeListener {

    @Bind(R.id.rv_planning_display_spec_month) RecyclerView mRecyclerView;
    private PlanningDisplaySpecMonthPresenter mPresenter;
    private MonthRecyclerViewAdapter mMonthRecyclerViewAdapter;
    private Datetime mDateOfToday = DateUtils.dateOfToday();
    //标识变量,用于控制当执行了onCreateView后,onResume不再重复执行渲染工作
    private boolean isCallOnCreateView = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_planning_display_spec_month, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new PlanningDisplaySpecMonthPresenterImpl(this);
        mPresenter.initMonthView(mDateOfToday);


        PlanningDisplayFragment planningDisplayFragment = (PlanningDisplayFragment) getParentFragment().getParentFragment();
        planningDisplayFragment.setOnToolbarDateChangeListener(this);

        isCallOnCreateView = true;
        return v;
    }


    @Override
    public void setRecyclerView(List<Event> events) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mMonthRecyclerViewAdapter = new MonthRecyclerViewAdapter(mActivity, events, mDateOfToday);
        mRecyclerView.setAdapter(mMonthRecyclerViewAdapter);
        setOnFragmentStopListener(mMonthRecyclerViewAdapter);
    }

    @Override
    public void refreshRecyclerView(Datetime datetime, List<Event> events) {
        mMonthRecyclerViewAdapter.refresh(datetime, events);
    }

    @Override
    public void onDateChanged(Datetime datetime) {
        mPresenter.renderMonthView(datetime);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isCallOnCreateView) {
            mPresenter.renderMonthView(mDateOfToday);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isCallOnCreateView = false;
    }

}
