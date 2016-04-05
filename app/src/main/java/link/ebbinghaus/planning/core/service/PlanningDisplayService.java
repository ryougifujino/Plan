package link.ebbinghaus.planning.core.service;

import java.util.List;

import link.ebbinghaus.planning.core.model.po.Event;
import link.ebbinghaus.planning.core.model.sys.Tab;
import link.ebbinghaus.planning.core.model.po.EventGroup;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public interface PlanningDisplayService {

    /**
     * 创建PlanningDisplay的最顶层tabs
     * @return PlanningDisplay顶层tabs
     */
    List<Tab> makePlanningDisplayTabs();

    /**
     * 查找所有具体计划组或者模糊计划组
     * @return 所有具体计划组或者模糊计划组
     * @param flag true:具体计划组 false:模糊计划组
     */
    List<EventGroup> findAllEventGroup(boolean flag);

    /**
     * 根据计划组类型和计划组id查找相关的具体计划或模糊计划
     * @param eventGroupType 计划组类型 true:具体计划 false:模糊计划
     * @param eventGroupId 计划组id
     * @return 计划组相关的具体计划或模糊计划
     */
    List<Event> findEventGroupDetail(boolean eventGroupType,Long eventGroupId);

}
