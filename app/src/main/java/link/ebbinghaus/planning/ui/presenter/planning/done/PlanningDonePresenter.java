package link.ebbinghaus.planning.ui.presenter.planning.done;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.po.Event;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public interface PlanningDonePresenter {

    /**
     * 获取完成模块需要显示的具体计划数据
     * @return 完成模块需要的具体计划数据
     */
    List<Event> obtainDoneModuleEvents();

    /**
     * 初始化完成模块的视图
     */
    void initPlanningDoneView();

    /**
     * 查出数据并滤出未完成的计划
     * @return 未完成计划
     */
    List<Event> filteredUnfinishedEvents();

    /**
     * 查出数据并滤出完成的计划
     * @return 完成的计划
     */
    List<Event> filteredFinishedEvents();

}
