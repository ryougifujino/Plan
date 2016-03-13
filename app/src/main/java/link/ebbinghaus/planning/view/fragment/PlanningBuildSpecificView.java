package link.ebbinghaus.planning.view.fragment;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public interface PlanningBuildSpecificView {

    /**
     * 根据普通计划还是学习计划设置面板
     */
    void setBuildPanel();

    /**
     * 设置学习计划的方案(策略)
     */
    void setStrategy();

    /**
     * 设置计划日期
     */
    void setExpectedFinishDate();

    /**
     * 设置是否提醒
     */
    void setRemind(boolean isChecked);

    /**
     * 设置提醒时间
     */
    void setRemindTime();

    /**
     * 设置是否显示学习计划的序号
     */
    void setSequence(boolean isChecked);

    /**
     * 设置是否显示希腊字母标记
     */
    void setGreekAlphabet(boolean isChecked);

    /**
     * 设置计划组
     */
    void setEventGroup();
}
