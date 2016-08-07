package link.ebbinghaus.planning.core.service.impl;

import com.yurikami.lib.model.Datetime;
import com.yurikami.lib.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.app.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.app.constant.module.PlanningDisplayConstant;
import link.ebbinghaus.planning.core.db.decorator.impl.EventSubtypeDaoDecorator;
import link.ebbinghaus.planning.core.model.local.vo.planning.display.SpecEventDetailVo;
import link.ebbinghaus.planning.core.db.decorator.impl.EventDaoDecorator;
import link.ebbinghaus.planning.core.db.decorator.impl.EventGroupDaoDecorator;
import link.ebbinghaus.planning.core.db.decorator.impl.LearningEventGroupDaoDecorator;
import link.ebbinghaus.planning.core.service.PlanningDisplaySpecificService;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.model.local.sys.Tab;
import link.ebbinghaus.planning.ui.view.planning.display.fragment.PlanningDisplayEventGroupFragment;
import link.ebbinghaus.planning.ui.view.planning.display.fragment.PlanningDisplaySpecMonthFragment;
import link.ebbinghaus.planning.ui.view.planning.display.fragment.PlanningDisplaySpecWeekFragment;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplaySpecificServiceImpl implements PlanningDisplaySpecificService {

    @Override
    public List<Tab> makePlanningDisplaySpecificTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_SPEC_MONTH, new PlanningDisplaySpecMonthFragment()));
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_SPEC_WEEK, new PlanningDisplaySpecWeekFragment()));
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_SPEC_GROUP, PlanningDisplayEventGroupFragment.newInstance(true)));
        return tabs;

    }

    @Override
    public List<Event>[] eventsToBlocks(List<Event> events, int dayOfMonth) {
        List<Event>[] blocks = new List[dayOfMonth];
        for (int i = 0 ;i < blocks.length ;i++){
            blocks[i] = new ArrayList<>();
        }
        for (Event event: events){
            long dateL = event.getEventExpectedFinishedDate();
            Integer day = DateUtils.day(dateL);
            blocks[day - 1].add(event);
        }
        return blocks;
    }

    @Override
    public void makeDayWeekListitems(List<Datetime> dayWeekListitems, int dayInMonth, Datetime datetime) {
        Datetime startDate = Datetime.buildDate(datetime.getYear(), datetime.getMonth(), 1);
        int startWeek = DateUtils.dayOfWeek(startDate);
        for (int day = 1; day <= dayInMonth ;day++){
            Datetime dayWeek = Datetime.buildDate(null, null, day);
            int w = (startWeek + day -1) % 7;
            dayWeek.setWeek( (w == 0) ? 7 : w );
            dayWeekListitems.add(dayWeek);
        }
    }

    @Override
    public List<Event> findSpecMonthEvents(Datetime datetime) {
        EventDaoDecorator dao = new EventDaoDecorator();
        List<Event> events = dao.selectSpecMonthEvents(datetime);
        dao.closeDB();
        return events;
    }

    @Override
    public List<Event> findSpecWeekEvents(Datetime datetime) {
        EventDaoDecorator dao = new EventDaoDecorator();
        List<Event> events = dao.selectSpecWeekEvents(datetime);
        dao.closeDB();
        return events;
    }

    @Override
    public void findSpecEventDetailTo(SpecEventDetailVo specEventDetail) {
        LearningEventGroupDaoDecorator learningEventGroupDao = new LearningEventGroupDaoDecorator();
        EventGroupDaoDecorator eventGroupDao = new EventGroupDaoDecorator();
        EventSubtypeDaoDecorator eventSubtypeDao = new EventSubtypeDaoDecorator();

        Long learningEventGroupId = specEventDetail.event.getLearningEventGroupId();
        Long eventGroupId = specEventDetail.event.getEventGroupId();
        Long eventSubtypeId = specEventDetail.event.getEventSubtypeId();

        specEventDetail.learningEventGroup = learningEventGroupDao.selectByPrimaryKey(learningEventGroupId);
        specEventDetail.eventGroup = eventGroupDao.selectByPrimaryKey(eventGroupId);
        specEventDetail.eventSubtype = eventSubtypeDao.selectByPrimaryKey(eventSubtypeId);

        eventSubtypeDao.closeDB();
        eventGroupDao.closeDB();
        learningEventGroupDao.closeDB();
    }

    @Override
    public void removeSpecEventAndProcessRelated(Event event) {
        EventDaoDecorator dao = new EventDaoDecorator();
        if (event.getEventType() == EventConfig.TYPE_SPEC_LEARNING){
            dao.deleteLearningEvents(event.getLearningEventGroupId(),event.getEventGroupId(),event.getGreekAlphabetId());
        }else if (event.getEventType() == EventConfig.TYPE_SPEC_NORMAL){
            dao.deleteNormalEvent(event.getPkEventId(),event.getEventGroupId(),event.getGreekAlphabetId());
        }
        dao.closeDB();
    }

    @Override
    public void updateEvent(Event event) {
        EventDaoDecorator dao = new EventDaoDecorator();
        dao.updateByPrimaryKey(event);
        dao.closeDB();
    }


}
