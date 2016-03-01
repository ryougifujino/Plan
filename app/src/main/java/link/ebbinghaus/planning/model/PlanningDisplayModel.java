package link.ebbinghaus.planning.model;

import java.util.List;

import link.ebbinghaus.planning.model.entity.sys.Tab;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public interface PlanningDisplayModel {

    /**
     * 创建一个PlanningDisplay的最顶层tab
     * @return tabs
     */
    List<Tab> makePlanningDisplayTabs();
}
