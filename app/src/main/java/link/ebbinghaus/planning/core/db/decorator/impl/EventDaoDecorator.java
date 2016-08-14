package link.ebbinghaus.planning.core.db.decorator.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.yurikami.lib.model.Datetime;
import com.yurikami.lib.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.app.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.core.db.dao.EventDao;
import link.ebbinghaus.planning.core.db.dao.EventGroupDao;
import link.ebbinghaus.planning.core.db.dao.GreekAlphabetDao;
import link.ebbinghaus.planning.core.db.dao.LearningEventGroupDao;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.model.local.po.LearningEventGroup;
import link.ebbinghaus.planning.core.model.local.vo.planning.build.InputEventVo;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class EventDaoDecorator extends BaseDaoDecorator<Event> {
    private EventDao dao;
    private LearningEventGroupDao learningEventGroupDao;
    private EventGroupDao eventGroupDao;
    private GreekAlphabetDao greekAlphabetDao;

    public EventDaoDecorator(){
        super(new EventDao());
        dao = (EventDao) super.dao;
        learningEventGroupDao = new LearningEventGroupDao();
        eventGroupDao = new EventGroupDao();
        greekAlphabetDao = new GreekAlphabetDao();
    }

    @Override
    public void closeDB() {
        super.closeDB();
        learningEventGroupDao.closeDB();
        eventGroupDao.closeDB();
    }

    /**
     * 插入一个学习计划组<br>
     * (包括Events和对应的LearningEventGroup,计划组对应学习计划数量增加)
     * @param inputEvent 插入的信息
     * @param strategy 策略
     * @return 插入的学习计划组的id
     */
    public List<Long> insertLearningEvents(InputEventVo inputEvent, int[] strategy){
        List<Long> eventIds = new ArrayList<>();
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

            eventIds.add(dao.insert(e));
            for (int i = 1; i < strategy.length; i++) {
                e = new Event();
                e.copyFrom(inputEvent);
                e.setLearningEventGroupId(legPk);
                e.setEventSequence(i + 1);
                e.setEventExpectedFinishedDate(DateUtils.timestampAfter(inputEvent.getEventExpectedFinishedDate(), strategy[i] - 1));
                eventIds.add(dao.insert(e));
            }
            if (inputEvent.getEventGroupId() != null) {
                eventGroupDao.updateLearningEventCount(inputEvent.getEventGroupId(), strategy.length);
            }
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
        return eventIds;
    }

    /**
     * 插入一个普通计划(计划组对应普通计划数量增加)<br>
     * @param event 插入信息
     * @return 插入的普通计划的id
     */
    public Long insertNormalEvent(Event event){
        Long id;
        dao.beginTransaction();
        try {
            event.setEventSequence(null);

            id = dao.insert(event);
            if (event.getEventGroupId() != null) {
                eventGroupDao.updateNormalEventCount(event.getEventGroupId(), 1);
            }
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
        return id;

    }

    /**
     * 插入一个模糊计划(计划组对应模糊计划数量增加)<br>
     * @param event 插入信息
     */
    public void insertAbstractEvent(Event event){
        dao.beginTransaction();
        try {
            event.setEventType(EventConfig.TYPE_ABSTRACT);
            dao.insert(event);
            if (event.getEventGroupId() != null) {
                eventGroupDao.updateAbstractEventCount(event.getEventGroupId(), 1);
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
        return dao.selectSpecWeekEvents(datetime.getYear(), datetime.getMonth(), datetime.getDay());
    }

    /**
     * 查找所有的模糊计划
     * @return 所有的模糊计划
     */
    public List<Event> selectAbstAllEvents(){
        return dao.selectAbstAllEvents();
    }

    /**
     * 根据计划组类型和计划组id查找相关的具体计划或模糊计划
     * @param eventGroupType 计划组类型 true:具体计划 false:模糊计划
     * @param eventGroupId 计划组id
     * @return 计划组相关的具体计划或模糊计划
     */
    public List<Event> selectEventGroupDetail(boolean eventGroupType, Long eventGroupId){
        return dao.selectEventGroupDetail(eventGroupType,eventGroupId);
    }

    /**
     * 查找从今天算起的过去两天的具体计划
     * @return 从今天(包括今天)算起的过去两天的具体计划
     */
    public List<Event> selectLast2DaysSpecEvents(){
        return dao.selectLast2DaysSpecEvents();
    }

    /**
     * 查找从某天起后的30天的具体计划（包括那天）
     * @param startDateTimestamp 开始日期时间戳
     * @return 从某天起的30天具体计划
     */
    public List<Event> select30daysSpecEventsFrom(long startDateTimestamp){
        return dao.selectSpecEventsFrom(startDateTimestamp, 30,true);
    }

    /**
     * 查找所有已经完成的具体计划
     * @return 所有已经完成的具体计划
     */
    public List<Event> selectAllDoneSpecEvents(){
        return dao.selectAllDoneSpecEvents();
    }

    /**
     * 查找出所有即将被置为失败的学习计划的id
     * @return 即将被置为失败的学习计划的id
     */
    public List<Long> selectAllFailedLearningEventIds(){
        List<Long> ids = new ArrayList<>();
        for (Event event : dao.selectAllFailedLearningEvents()) {
            ids.add(event.getPkEventId());
        }
        return ids;
    }

    /**
     * 删除学习计划<br>
     * 并且会删除相关的学习计划组；处理相关的计划组、希腊字母
     * @param learningEventGroupId 学习计划组id
     * @param eventGroupId  计划组id
     * @param greekAlphabetId 希腊字母id
     */
    public void deleteLearningEvents(@NonNull Long learningEventGroupId, @Nullable Long eventGroupId,
                                     @Nullable Long greekAlphabetId){
        dao.beginTransaction();
        try {
            int decreaseCount = dao.deleteEventsByLearningEventGroupId(learningEventGroupId);
            learningEventGroupDao.deleteByPrimaryKey(learningEventGroupId);
            eventGroupDao.updateLearningEventCount(eventGroupId, -decreaseCount);
            greekAlphabetDao.updateUsage(greekAlphabetId,-1);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    /**
     * 删除普通计划<br>
     * 并且会处理相关的计划组、希腊字母
     * @param normalEventId 普通计划id
     * @param eventGroupId 学习计划组id
     * @param greekAlphabetId 希腊字母id
     */
    public void deleteNormalEvent(@NonNull Long normalEventId, @Nullable Long eventGroupId,
                                  @Nullable Long greekAlphabetId) {
        dao.beginTransaction();
        try {
            dao.deleteByPrimaryKey(normalEventId);
            eventGroupDao.updateNormalEventCount(eventGroupId,-1);
            greekAlphabetDao.updateUsage(greekAlphabetId,-1);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }

    }

    /**
     * 更新计划进程以及因日期改变产生的相关变化<br>
     * 系统时间向后调整无影响，向前调整会发生错乱（比如穿越到未来完成之类的，无视之）
     */
    public void updateEventsProcessAndRelated() {
        dao.beginTransaction();
        try {
            //更新普通计划
            dao.updateExpiredNormalEvents();
            //更新学习计划
            dao.updateFailedLearningEventGroups();
            dao.updateFailedLearningEvents();

            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }
}
