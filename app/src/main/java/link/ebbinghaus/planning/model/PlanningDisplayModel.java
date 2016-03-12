package link.ebbinghaus.planning.model;

import java.util.List;

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
     * 创建PlanningBuild的最顶层tabs
     * @return PlanningBuild的tabs
     */
    List<Tab> makePlanningBuildTabs();
}
