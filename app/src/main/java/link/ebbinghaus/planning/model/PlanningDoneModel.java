package link.ebbinghaus.planning.model;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public interface PlanningDoneModel {

    /**
     * 查找从今天起(包括)的过去两天具体计划
     * @return 从今天起(包括)的过去两天具体计划
     */
    List<Event> findLast2DaysSpecEventsFromToday();
}
