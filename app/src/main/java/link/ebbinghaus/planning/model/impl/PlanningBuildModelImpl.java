package link.ebbinghaus.planning.model.impl;

import com.yurikami.lib.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.entity.EventConfig;
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
import link.ebbinghaus.planning.model.entity.vo.InputEventVo;
import link.ebbinghaus.planning.view.fragment.impl.PlanningBuildAbstractFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningBuildSpecificFragment;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class PlanningBuildModelImpl implements PlanningBuildModel {
    private DefaultInputValueDaoDecorator mDefaultInputValueDao = new DefaultInputValueDaoDecorator();
    private EventSubtypeDaoDecorator mEventSubtypeDao = new EventSubtypeDaoDecorator();
    private FastTemplateDaoDecorator mFastTemplateDao = new FastTemplateDaoDecorator();
    private EventGroupDaoDecorator mEventGroupDao = new EventGroupDaoDecorator();
    private EventDaoDecorator mEventDao = new EventDaoDecorator();

    @Override
    public List<Tab> makePlanningBuildTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(PlanningBuildConstant.TAB_NAME_SPECIFIC, new PlanningBuildSpecificFragment()));
        tabs.add(new Tab(PlanningBuildConstant.TAB_NAME_ABSTRACT, new PlanningBuildAbstractFragment()));
        return tabs;
    }

    @Override
    public void addLearningEvent(InputEventVo inputEvent) {
        switch (inputEvent.getStrategy()){
            case LearningEventGroupConfig.TYPE_COMPREHENSIVE:
                mEventDao.insertLearningEvents(inputEvent, LearningEventGroupConfig.STRATEGY_COMPREHENSIVE);
                break;
            case LearningEventGroupConfig.TYPE_MEMORIAL:
                mEventDao.insertLearningEvents(inputEvent, LearningEventGroupConfig.STRATEGY_MEMORIAL);
                break;
            case LearningEventGroupConfig.TYPE_MEMORIAL_PRO:
                mEventDao.insertLearningEvents(inputEvent, LearningEventGroupConfig.STRATEGY_MEMORIAL_PRO);
                break;
            case LearningEventGroupConfig.TYPE_PERSISTENT:
                mEventDao.insertLearningEvents(inputEvent, LearningEventGroupConfig.STRATEGY_PERSISTENT);
                break;
        }
    }

    @Override
    public void addNormalEvent(Event event) {
        event.setEventSequence(null);
        event.setEventProcess(
                DateUtils.isInSameDate(DateUtils.currentDateTimestamp(), event.getEventExpectedFinishedDate())
                        ? EventConfig.PROCESS_TODO
                        : EventConfig.PROCESS_NOT_STARTED);
        mEventDao.insert(event);
    }

    @Override
    public void addAbstractEvent(Event event) {

    }

    @Override
    public DefaultInputValue findDefaultInputValue() {
        return mDefaultInputValueDao.selectAll().get(0);
    }

    @Override
    public List<EventSubtype> findAllEventSubtype() {
        return mEventSubtypeDao.selectAll();
    }

    @Override
    public List<FastTemplate> findAllSpecLearningFastTemplate() {
        return mFastTemplateDao.findSpecLearningAll();
    }

    @Override
    public List<FastTemplate> findAllSpecNormalFastTemplate() {
        return mFastTemplateDao.findSpecNormalAll();
    }

    @Override
    public List<FastTemplate> findAllAbstFastTemplate() {
        return mFastTemplateDao.findAbstractAll();
    }

    @Override
    public List<EventGroup> findAllEventGroup() {
        return mEventGroupDao.selectAll();
    }

    @Override
    public void closeDB() {
        mDefaultInputValueDao.closeDB();
        mEventSubtypeDao.closeDB();
        mFastTemplateDao.closeDB();
        mEventGroupDao.closeDB();
        mEventDao.closeDB();
    }
}
