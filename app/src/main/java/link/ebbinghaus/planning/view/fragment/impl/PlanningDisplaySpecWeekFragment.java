package link.ebbinghaus.planning.view.fragment.impl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;
import com.yurikami.lib.entity.Datetime;

import link.ebbinghaus.planning.custom.util.CommonUtils;
import link.ebbinghaus.planning.view.fragment.PlanningDisplaySpecWeekView;
import link.ebbinhaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningDisplaySpecWeekFragment extends BaseFragment implements PlanningDisplaySpecWeekView,
        PlanningDisplayFragment.OnToolbarDateChangeListener{


    public PlanningDisplaySpecWeekFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_spec_week, container, false);

        PlanningDisplayFragment planningDisplayFragment = (PlanningDisplayFragment) getParentFragment().getParentFragment();
        planningDisplayFragment.setOnToolbarDateChangeListener(this);

        return v;
    }

    @Override
    public void onDateChanged(Datetime datetime) {
        CommonUtils.showLongToast(" WEEK " + datetime.getYear() + "  " + datetime.getMonth() + "  " + datetime.getDay());
    }
}
