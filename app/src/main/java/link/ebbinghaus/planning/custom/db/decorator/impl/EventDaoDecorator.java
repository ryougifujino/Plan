package link.ebbinghaus.planning.custom.db.decorator.impl;

import com.yurikami.lib.entity.Datetime;
import com.yurikami.lib.util.DateUtils;

import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.custom.db.dao.EventDao;
import link.ebbinghaus.planning.custom.db.dao.LearningEventGroupDao;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;
import link.ebbinghaus.planning.model.entity.vo.InputEventVo;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class EventDaoDecorator extends BaseDaoDecorator<Event> {
    private EventDao dao;
    private LearningEventGroupDao learningEventGroupDao;

    public EventDaoDecorator(){
        super(new EventDao());
        dao = (EventDao) super.dao;
        learningEventGroupDao = new LearningEventGroupDao();
    }

    @Override
    public void closeDB() {
        super.closeDB();
        learningEventGroupDao.closeDB();
    }

    /**
     * 插入一个学习计划组(包括Events和对应的LearningEventGroup)
     * @param inputEvent 插入的信息
     * @param strategy 策略
     */
    public void insertLearningEvents(InputEventVo inputEvent, int[] strategy){
        dao.beginTransaction();
        try {
            //插入学习计划组
            LearningEventGroup leg = new LearningEventGroup();
            leg.setStrategy(inputEvent.getStrategy());
            leg.setLearningEventTotal(strategy.length);
            leg.setLearningEventFinishedCount(0);
            long legPk = learningEventGroupDao.insert(leg);
            //插入学习计划
            Event e = new Event();
            e.copyFrom(inputEvent);
            e.setLearningEventGroupId(legPk);
            e.setEventSequence(1);
            e.setEventProcess(
                    DateUtils.isInSameDate(DateUtils.currentDateTimestamp(), e.getEventExpectedFinishedDate())
                            ? EventConfig.PROCESS_IN_PROGRESS
                            : EventConfig.PROCESS_NOT_STARTED);
            dao.insert(e);
            for (int i = 1; i < strategy.length; i++) {
                e = new Event();
                e.copyFrom(inputEvent);
                e.setLearningEventGroupId(legPk);
                e.setEventSequence(i + 1);
                e.setEventProcess(EventConfig.PROCESS_NOT_STARTED);
                e.setEventExpectedFinishedDate(DateUtils.timestampAfter(inputEvent.getEventExpectedFinishedDate(), strategy[i] - 1));
                dao.insert(e);
            }
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    /**
     * 查找某年某个月的具体计划
     * @param datetime 包含某年某月
     * @return 某年某月的具体计划
     */
    public List<Event> selectSpecMonthEvents(Datetime datetime){
        return dao.selectSpecMonthEvents(datetime.getYear(), datetime.getMonth());
    }

    /**
     * 查找某一天所在周的具体计划
     * @param datetime 某一天
     * @return 某一天所在周的具体计划
     */
    public List<Event> selectSpecWeekEvents(Datetime datetime){
        return dao.selectSpecWeekEvents(datetime.getYear(),datetime.getMonth(),datetime.getDay());
    }
}
