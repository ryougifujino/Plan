package link.ebbinghaus.planning.model;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.entity.sys.Tab;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public interface PlanningDisplayModel {

    /**
     * 创建PlanningDisplay的最顶层tabs
     * @return PlanningDisplay顶层tabs
     */
    List<Tab> makePlanningDisplayTabs();

    /**
     * 查找所有具体计划组或者模糊计划组
     * @return 所有具体计划组或者模糊计划组
     */
    List<EventGroup> findAllEventGroup();

}
