package link.ebbinghaus.planning.presenter;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;

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
}
