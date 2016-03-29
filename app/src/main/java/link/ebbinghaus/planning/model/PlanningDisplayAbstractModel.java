package link.ebbinghaus.planning.model;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.sys.Tab;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public interface PlanningDisplayAbstractModel {

    /**
     * 创建PlanningBuildAbstract的最顶层tabs
     * @return PlanningBuildAbstract的tabs
     */
    List<Tab> makePlanningDisplayAbstractTabs();

    /**
     * 找出所有的模糊计划
     * @return
     */
    List<Event> findAllAbstEvent();

}
