package link.ebbinghaus.planning.view.planning.done;

import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public interface PlanningDoneFinishView {

    /**
     * 获取Intent数据
     * @return 获取的Intent数据
     */
    Event getIntentData();

    /**
     * 设置学习计划组的默认值
     */
    void setDefaultLearningEventGroupValues();

    /**
     * 初始化toolbar
     */
    void initToolbar();

    /**
     * 根据类型设置对应的视图类型<br>
     * 是学习计划并且序号为1则显示完整视图<br>
     * 如果是学习计划但序号不为1或者是普通计划则只显示总结框
     * @param type true:显示完整 false:只显示总结
     */
    void setViewByType(boolean type);

    /**
     * 根据类型选择性设置视图里面控件的监听器
     * @param type true:显示完整 false:只显示总结
     */
    void registerListenerByType(boolean type);

    /**
     * 初始化一些控件,如数字选择器等等
     */
    void initWidget();

    /**
     * 设置一个效率单选对话框
     */
    void setEfficiencyDialog();

    /**
     * 设置一个理解程度单选对话框
     */
    void setUnderstandingDegreeDialog();

    /**
     * 验证完成表单
     * @return true:验证通过 false:验证不通过
     */
    boolean validFinishForm();

    /**
     * 退出本视图
     */
    void exitPlanningDoneView();

}
