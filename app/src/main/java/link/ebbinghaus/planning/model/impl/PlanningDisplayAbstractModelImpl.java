package link.ebbinghaus.planning.model.impl;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.module.PlanningDisplayConstant;
import link.ebbinghaus.planning.model.PlanningDisplayAbstractModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDisplayAbstAllFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDisplayEventGroupFragment;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplayAbstractModelImpl implements PlanningDisplayAbstractModel {
    @Override
    public List<Tab> makePlanningDisplayAbstractTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_ABST_ALL, new PlanningDisplayAbstAllFragment()));
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_ABST_GROUP, PlanningDisplayEventGroupFragment.newInstance(false)));
        return tabs;
    }

    @Override
    public List<Event> findAllAbstEvent() {

        return null;
    }
}
