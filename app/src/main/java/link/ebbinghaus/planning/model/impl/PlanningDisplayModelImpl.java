package link.ebbinghaus.planning.model.impl;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.PlanningBuildConstant;
import link.ebbinghaus.planning.custom.constant.PlanningDisplayConstant;
import link.ebbinghaus.planning.model.PlanningDisplayModel;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.view.fragment.impl.PlanningBuildAbstractFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningBuildSpecificFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDisplayAbstractFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDisplaySpecificFragment;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class PlanningDisplayModelImpl implements PlanningDisplayModel {

    @Override
    public List<Tab> makePlanningDisplayTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(PlanningDisplayConstant.TAB_NAME_SPECIFIC, new PlanningDisplaySpecificFragment()));
        tabs.add(new Tab(PlanningDisplayConstant.TAB_NAME_ABSTRACT, new PlanningDisplayAbstractFragment()));
        return tabs;
    }

    @Override
    public List<Tab> makePlanningBuildTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(PlanningBuildConstant.TAB_NAME_SPECIFIC, new PlanningBuildSpecificFragment()));
        tabs.add(new Tab(PlanningBuildConstant.TAB_NAME_ABSTRACT, new PlanningBuildAbstractFragment()));
        return tabs;
    }
}
