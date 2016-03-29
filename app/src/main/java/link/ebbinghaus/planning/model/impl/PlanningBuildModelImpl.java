package link.ebbinghaus.planning.model.impl;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.entity.LearningEventGroupConfig;
import link.ebbinghaus.planning.custom.constant.module.PlanningBuildConstant;
import link.ebbinghaus.planning.custom.db.decorator.impl.DefaultInputValueDaoDecorator;
import link.ebbinghaus.planning.custom.db.decorator.impl.EventDaoDecorator;
import link.ebbinghaus.planning.custom.db.decorator.impl.EventGroupDaoDecorator;
import link.ebbinghaus.planning.custom.db.decorator.impl.EventSubtypeDaoDecorator;
import link.ebbinghaus.planning.custom.db.decorator.impl.FastTemplateDaoDecorator;
import link.ebbinghaus.planning.model.PlanningBuildModel;
import link.ebbinghaus.planning.model.entity.po.DefaultInputValue;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.entity.vo.planning.build.InputEventVo;
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
    public void addLearningEvent(InputEventVo inputEvent) {
        EventDaoDecorator eventDao = new EventDaoDecorator();
        switch (inputEvent.getStrategy()){
            case LearningEventGroupConfig.TYPE_COMPREHENSIVE:
                eventDao.insertLearningEvents(inputEvent, LearningEventGroupConfig.STRATEGY_COMPREHENSIVE);
                break;
            case LearningEventGroupConfig.TYPE_MEMORIAL:
                eventDao.insertLearningEvents(inputEvent, LearningEventGroupConfig.STRATEGY_MEMORIAL);
                break;
            case LearningEventGroupConfig.TYPE_MEMORIAL_PRO:
                eventDao.insertLearningEvents(inputEvent, LearningEventGroupConfig.STRATEGY_MEMORIAL_PRO);
                break;
            case LearningEventGroupConfig.TYPE_PERSISTENT:
                eventDao.insertLearningEvents(inputEvent, LearningEventGroupConfig.STRATEGY_PERSISTENT);
                break;
        }
        eventDao.closeDB();
    }

    @Override
    public void addNormalEvent(Event event) {
        EventDaoDecorator dao = new EventDaoDecorator();
        dao.insertNormalEvent(event);
        dao.closeDB();
    }

    @Override
    public void addAbstractEvent(Event event) {
        EventDaoDecorator dao = new EventDaoDecorator();
        dao.insertAbstractEvent(event);
        dao.closeDB();
    }

    @Override
    public DefaultInputValue findDefaultInputValue() {
        DefaultInputValueDaoDecorator dao = new DefaultInputValueDaoDecorator();
        DefaultInputValue defaultInputValue = dao.selectAll().get(0);
        dao.closeDB();
        return defaultInputValue;
    }

    @Override
    public List<EventSubtype> findAllEventSubtype() {
        EventSubtypeDaoDecorator dao = new EventSubtypeDaoDecorator();
        List<EventSubtype> eventSubtypes = dao.selectAll();
        dao.closeDB();
        return eventSubtypes;
    }

    @Override
    public List<FastTemplate> findAllSpecLearningFastTemplate() {
        FastTemplateDaoDecorator dao = new FastTemplateDaoDecorator();
        List<FastTemplate> fastTemplates = dao.selectSpecLearningAll();
        dao.closeDB();
        return fastTemplates;
    }

    @Override
    public List<FastTemplate> findAllSpecNormalFastTemplate() {
        FastTemplateDaoDecorator dao = new FastTemplateDaoDecorator();
        List<FastTemplate> fastTemplates = dao.selectSpecNormalAll();
        dao.closeDB();
        return fastTemplates;
    }

    @Override
    public List<FastTemplate> findAllAbstFastTemplate() {
        FastTemplateDaoDecorator dao = new FastTemplateDaoDecorator();
        List<FastTemplate> fastTemplates = dao.selectAbstractAll();
        dao.closeDB();
        return fastTemplates;
    }

    @Override
    public List<EventGroup> findAllEventGroup() {
        EventGroupDaoDecorator dao = new EventGroupDaoDecorator();
        List<EventGroup> eventGroups = dao.selectAll();
        dao.closeDB();
        return eventGroups;
    }

}
