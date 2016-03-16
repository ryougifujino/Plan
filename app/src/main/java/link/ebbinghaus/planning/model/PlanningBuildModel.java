package link.ebbinghaus.planning.model;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;
import link.ebbinghaus.planning.model.entity.sys.Tab;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public interface PlanningBuildModel {

    /**
     * 创建PlanningBuild的最顶层tabs
     * @return PlanningBuild的tabs
     */
    List<Tab> makePlanningBuildTabs();

    /**
     * 向数据库保存一个制定好的学习计划
     * @param event 学习计划分项
     */
    void persistLearningEvent(Event event);

    /**
     * 向数据库保存一个制定好的普通计划
     * @param event 普通计划
     * @param eventSubtype 计划子类型
     * @param eventGroup 计划组
     */
    void persistEvent(Event event,EventSubtype eventSubtype,EventGroup eventGroup);

    /**
     * 向数据库保存一个制定好的模糊计划
     * @param event
     */
    void persistAbstractEvent(Event event);
}
