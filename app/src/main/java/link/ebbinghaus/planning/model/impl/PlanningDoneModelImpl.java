package link.ebbinghaus.planning.model.impl;

import java.util.List;

import link.ebbinghaus.planning.ebbinghaus.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.ebbinghaus.db.decorator.impl.EventDaoDecorator;
import link.ebbinghaus.planning.ebbinghaus.db.decorator.impl.LearningEventGroupDaoDecorator;
import link.ebbinghaus.planning.model.PlanningDoneModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public class PlanningDoneModelImpl implements PlanningDoneModel {

    @Override
    public List<Event> findLast2DaysSpecEventsFromToday() {
        EventDaoDecorator dao = new EventDaoDecorator();
        List<Event> events = dao.selectLast2DaysSpecEvents();
        dao.closeDB();
        return events;
    }

    @Override
    public void finishEvent(Event event, LearningEventGroup leg) {
        //更新学习计划组(如果是学习计划的话)
        if (event.getEventType() == EventConfig.TYPE_SPEC_LEARNING){
            if (event.getEventSequence() == 1) {
                int knowledgeQuantity = (int) (leg.getLearningDuration() * leg.getEfficiency() * leg.getUnderstandingDegree());
                leg.setKnowledgeQuantity(knowledgeQuantity);
            }

            LearningEventGroupDaoDecorator legDao = new LearningEventGroupDaoDecorator();
            legDao.updateLearningEventGroupAfterFinishing1Event(leg);
            legDao.closeDB();
        }

        //更新计划
        event.setEventProcess(EventConfig.PROCESS_FINISHED);    /* 同PROCESS_SUCCEED */
        event.setEventFinishedTime(System.currentTimeMillis());
        event.setIsEventFinished(true);

        EventDaoDecorator eventDao = new EventDaoDecorator();
        eventDao.updateByPrimaryKey(event);
        eventDao.closeDB();
    }
}
