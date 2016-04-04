package link.ebbinghaus.planning.model;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public interface PlanningDoneModel {

    /**
     * 查找从今天起(包括)的过去两天具体计划
     * @return 从今天起(包括)的过去两天具体计划
     */
    List<Event> findLast2DaysSpecEventsFromToday();

    /**
     * 完成计划
     * @param event 计划数据
     * @param learningEventGroup 如果是学习计划组的话,里面将有值
     */
    void finishEvent(Event event,LearningEventGroup learningEventGroup);
}
