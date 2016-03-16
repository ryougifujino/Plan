package link.ebbinghaus.planning.model.impl;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.PlanningBuildConstant;
import link.ebbinghaus.planning.custom.db.dao.EventDao;
import link.ebbinghaus.planning.model.PlanningBuildModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.view.fragment.impl.PlanningBuildAbstractFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningBuildSpecificFragment;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class PlanningBuildModelImpl implements PlanningBuildModel {

    @Override
    public List<Tab> makePlanningBuildTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(PlanningBuildConstant.TAB_NAME_SPECIFIC, new PlanningBuildSpecificFragment()));
        tabs.add(new Tab(PlanningBuildConstant.TAB_NAME_ABSTRACT, new PlanningBuildAbstractFragment()));
        return tabs;
    }

    @Override
    public void persistLearningEvent(Event event) {
        EventDao eventDao = new EventDao();
        eventDao.add(event);
        eventDao.closeDB();
    }

    @Override
    public void persistEvent(Event event, EventSubtype eventSubtype, EventGroup eventGroup) {

    }

    @Override
    public void persistAbstractEvent(Event event) {

    }
}
