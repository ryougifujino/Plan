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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.adapter.planning.display.spec.MonthRecyclerViewAdapter;
import link.ebbinghaus.planning.custom.other.mock.MonthEventsMock;
import link.ebbinghaus.planning.model.entity.Event;
import link.ebbinghaus.planning.view.fragment.PlanningDisplaySpecMonthView;
import link.ebbinhaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningDisplaySpecMonthFragment extends BaseFragment implements PlanningDisplaySpecMonthView {

    @Bind(R.id.rv_planning_display_spec_month) RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_spec_month, container, false);
        ButterKnife.bind(this, v);


        List<Event> events = MonthEventsMock.getOneMonthEvents();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        Datetime datetime = Datetime.buildDate(2016, 4, null);
        MonthRecyclerViewAdapter adapter = new MonthRecyclerViewAdapter(mActivity,events,datetime);
        mRecyclerView.setAdapter(adapter);
        setFragmentLifeCycleListener(adapter);


        return v;
    }

}
