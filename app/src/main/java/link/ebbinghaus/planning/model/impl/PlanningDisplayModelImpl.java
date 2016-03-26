package link.ebbinghaus.planning.model.impl;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.module.PlanningDisplayConstant;
import link.ebbinghaus.planning.custom.db.decorator.impl.EventGroupDaoDecorator;
import link.ebbinghaus.planning.model.PlanningDisplayModel;
import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.entity.sys.Tab;
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
    public List<EventGroup> findAllEventGroup() {
        EventGroupDaoDecorator dao = new EventGroupDaoDecorator();
        List<EventGroup> eventGroups = dao.selectAll();
        dao.closeDB();
        return eventGroups;
    }

}
