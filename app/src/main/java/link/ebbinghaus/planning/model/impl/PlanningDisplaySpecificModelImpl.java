package link.ebbinghaus.planning.model.impl;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.PlanningDisplayConstant;
import link.ebbinghaus.planning.model.PlanningDisplaySpecificModel;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDisplaySpecGroupFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDisplaySpecMonthFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDisplaySpecWeekFragment;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplaySpecificModelImpl implements PlanningDisplaySpecificModel {

    @Override
    public List<Tab> makePlanningDisplayTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_SPEC_MONTH, new PlanningDisplaySpecMonthFragment()));
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_SPEC_WEEK, new PlanningDisplaySpecWeekFragment()));
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_SPEC_GROUP, new PlanningDisplaySpecGroupFragment()));
        return tabs;

    }
}
