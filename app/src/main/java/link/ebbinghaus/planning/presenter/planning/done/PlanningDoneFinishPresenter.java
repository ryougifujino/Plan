package link.ebbinghaus.planning.presenter.planning.done;

import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public interface PlanningDoneFinishPresenter {

    /**
     * 初始化PlanningDoneFinish,做一些获取Intent数据和初始化控件的工作
     */
    void initPlanningDoneFinish();

    /**
     * 构造效率单选对话框
     */
    void buildEfficiencyDialog();

    /**
     * 构造理解程度单选对话框
     */
    void buildUnderstandingDegreeDialog();

    /**
     * 完成计划,在这之前需进行验证
     * @param event 计划数据
     * @param learningEventGroup 如果是学习计划组的话,里面将有值
     */
    void finishEvent(Event event,LearningEventGroup learningEventGroup);
}
