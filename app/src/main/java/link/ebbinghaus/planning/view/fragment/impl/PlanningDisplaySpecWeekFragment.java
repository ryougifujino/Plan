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
import link.ebbinghaus.planning.custom.adapter.planning.display.spec.WeekRecyclerViewAdapter;
import link.ebbinghaus.planning.custom.util.CommonUtils;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.presenter.PlanningDisplaySpecWeekPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningDisplaySpecWeekPresenterImpl;
import link.ebbinghaus.planning.view.fragment.PlanningDisplaySpecWeekView;
import link.ebbinhaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningDisplaySpecWeekFragment extends BaseFragment implements PlanningDisplaySpecWeekView,
        PlanningDisplayFragment.OnToolbarDateChangeListener{

    @Bind(R.id.rv_planning_display_spec_week) RecyclerView mRecyclerView;
    private WeekRecyclerViewAdapter mWeekRecyclerViewAdapter;
    private PlanningDisplaySpecWeekPresenter mPresenter;
    private Datetime mDateOfToday = DateUtils.dateOfToday();
    private List<Event> mEvents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_spec_week, container, false);
        mPresenter = new PlanningDisplaySpecWeekPresenterImpl(this);
        mPresenter.renderWeekView();

        PlanningDisplayFragment planningDisplayFragment = (PlanningDisplayFragment) getParentFragment().getParentFragment();
        planningDisplayFragment.setOnToolbarDateChangeListener(this);

        return v;
    }

    @Override
    public void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mWeekRecyclerViewAdapter = new WeekRecyclerViewAdapter(mActivity, mDateOfToday);
        mRecyclerView.setAdapter(mWeekRecyclerViewAdapter);

    }

    @Override
    public void onDateChanged(Datetime datetime) {
        CommonUtils.showLongToast(" WEEK " + datetime.getYear() + "  " + datetime.getMonth() + "  " + datetime.getDay());
    }

}
