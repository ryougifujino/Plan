package link.ebbinghaus.planning.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.common.constant.module.PlanningDisplayConstant;
import link.ebbinghaus.planning.core.service.PlanningDisplayService;
import link.ebbinghaus.planning.core.db.decorator.impl.EventGroupDaoDecorator;
import link.ebbinghaus.planning.core.model.po.Event;
import link.ebbinghaus.planning.core.model.po.EventGroup;
import link.ebbinghaus.planning.core.model.sys.Tab;
import link.ebbinghaus.planning.core.db.decorator.impl.EventDaoDecorator;
import link.ebbinghaus.planning.ui.view.planning.display.fragment.PlanningDisplayAbstractFragment;
import link.ebbinghaus.planning.ui.view.planning.display.fragment.PlanningDisplaySpecificFragment;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class PlanningDisplayServiceImpl implements PlanningDisplayService {

    @Override
    public List<Tab> makePlanningDisplayTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(PlanningDisplayConstant.TAB_NAME_SPECIFIC, new PlanningDisplaySpecificFragment()));
        tabs.add(new Tab(PlanningDisplayConstant.TAB_NAME_ABSTRACT, new PlanningDisplayAbstractFragment()));
        return tabs;
    }

    @Override
    public List<EventGroup> findAllEventGroup(boolean flag) {
        List<EventGroup> eventGroups;
        EventGroupDaoDecorator dao = new EventGroupDaoDecorator();
        if (flag){
            eventGroups = dao.selectAllSpecEventGroup();
        }else {
            eventGroups = dao.selectAllAbstEventGroup();
        }
        dao.closeDB();
        return eventGroups;
    }

    @Override
    public List<Event> findEventGroupDetail(boolean eventGroupType, Long eventGroupId) {
        List<Event> events;
        EventDaoDecorator dao = new EventDaoDecorator();
        events = dao.selectEventGroupDetail(eventGroupType,eventGroupId);
        dao.closeDB();
        return events;
    }

}
