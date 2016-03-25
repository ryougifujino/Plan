package link.ebbinghaus.planning.view.fragment.impl;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;

import link.ebbinghaus.planning.view.fragment.PlanningDisplayEventGroupView;
import link.ebbinhaus.planning.R;

/**
 * 具体计划和抽象计划<strong>共用</strong>的计划组Activity
 */
public class PlanningDisplayEventGroupFragment extends BaseFragment implements PlanningDisplayEventGroupView {


    public PlanningDisplayEventGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planning_display_event_group, container, false);
    }

}
