package link.ebbinghaus.planning.model.impl;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.module.PlanningBuildConstant;
import link.ebbinghaus.planning.custom.db.daoadapter.impl.DefaultInputValueDaoAdapter;
import link.ebbinghaus.planning.custom.db.daoadapter.impl.EventDaoAdapter;
import link.ebbinghaus.planning.custom.db.daoadapter.impl.EventGroupDaoAdapter;
import link.ebbinghaus.planning.custom.db.daoadapter.impl.EventSubtypeDaoAdapter;
import link.ebbinghaus.planning.custom.db.daoadapter.impl.FastTemplateDaoAdapter;
import link.ebbinghaus.planning.model.PlanningBuildModel;
import link.ebbinghaus.planning.model.entity.po.DefaultInputValue;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.view.fragment.impl.PlanningBuildAbstractFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningBuildSpecificFragment;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class PlanningBuildModelImpl implements PlanningBuildModel {
    private DefaultInputValueDaoAdapter mDefaultInputValueDao = new DefaultInputValueDaoAdapter();
    private EventSubtypeDaoAdapter mEventSubtypeDao = new EventSubtypeDaoAdapter();
    private FastTemplateDaoAdapter mFastTemplateDao = new FastTemplateDaoAdapter();
    private EventGroupDaoAdapter mEventGroupDao = new EventGroupDaoAdapter();

    @Override
    public List<Tab> makePlanningBuildTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(PlanningBuildConstant.TAB_NAME_SPECIFIC, new PlanningBuildSpecificFragment()));
        tabs.add(new Tab(PlanningBuildConstant.TAB_NAME_ABSTRACT, new PlanningBuildAbstractFragment()));
        return tabs;
    }

    @Override
    public void persistLearningEvent(Event event) {
        EventDaoAdapter eventDao = new EventDaoAdapter();
        eventDao.add(event);
        eventDao.closeDB();
    }

    @Override
    public void persistEvent(Event event, EventSubtype eventSubtype, EventGroup eventGroup) {

    }

    @Override
    public void persistAbstractEvent(Event event) {

    }

    @Override
    public DefaultInputValue getDefaultInputValue() {
        return mDefaultInputValueDao.findAll().get(0);
    }

    @Override
    public List<EventSubtype> getAllEventSubtype() {
        return mEventSubtypeDao.findAll();
    }

    @Override
    public List<FastTemplate> getAllSpecLearningFastTemplate() {
        return mFastTemplateDao.findSpecLearningAll();
    }

    @Override
    public List<FastTemplate> getAllSpecNormalFastTemplate() {
        return mFastTemplateDao.findSpecNormalAll();
    }

    @Override
    public List<FastTemplate> getAllAbstFastTemplate() {
        return mFastTemplateDao.findAbstractAll();
    }

    @Override
    public List<EventGroup> getAllEventGroup() {
        return mEventGroupDao.findAll();
    }

    @Override
    public void closeDB() {
        mDefaultInputValueDao.closeDB();
        mEventSubtypeDao.closeDB();
        mFastTemplateDao.closeDB();
        mEventGroupDao.closeDB();
    }
}
