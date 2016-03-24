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
        PlanningDisplayFragment.OnToolbarDateChangeListener{

    @Bind(R.id.rv_planning_display_spec_month) RecyclerView mRecyclerView;
    private PlanningDisplaySpecMonthPresenter mPresenter;
    private MonthRecyclerViewAdapter mMonthRecyclerViewAdapter;
    private Datetime mDateOfToday;
    private List<Event> mEvents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_spec_month, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new PlanningDisplaySpecMonthPresenterImpl(this);
        mEvents = mPresenter.obtainSpecEvents( mDateOfToday = DateUtils.dateOfToday());
        mPresenter.renderMonthView();


        PlanningDisplayFragment planningDisplayFragment = (PlanningDisplayFragment) getParentFragment().getParentFragment();
        planningDisplayFragment.setOnToolbarDateChangeListener(this);

        return v;
    }


    @Override
    public void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mMonthRecyclerViewAdapter = new MonthRecyclerViewAdapter(mActivity, mEvents, mDateOfToday);
        mRecyclerView.setAdapter(mMonthRecyclerViewAdapter);
        setOnFragmentDestroyListener(mMonthRecyclerViewAdapter);
    }

    @Override
    public void onDateChanged(Datetime datetime) {
        mEvents = mPresenter.obtainSpecEvents(datetime);
        mMonthRecyclerViewAdapter.refresh(datetime, mEvents);
    }

    @Override
    public void onResume() {
        super.onResume();
        mEvents = mPresenter.obtainSpecEvents(mDateOfToday);
        mMonthRecyclerViewAdapter.refresh(mDateOfToday, mEvents);
    }
}
