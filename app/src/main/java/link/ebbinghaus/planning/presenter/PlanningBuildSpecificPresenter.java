package link.ebbinghaus.planning.presenter;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public interface PlanningBuildSpecificPresenter {

    /**
     * 根据是普通计划还是学习计划切换到相应的面板上去
     */
    void switchBuildPanel();

    /**
     * 配置计划子类型
     */
    void configureSubtype();

    /**
     * 配置学习计划的方案(策略)
     */
    void configureStrategy();

    /**
     * 配置计划日期
     */
    void configureExpectedFinishDate();

    /**
     * 配置是否提醒
     */
    void configureRemind(boolean isChecked);

    /**
     * 配置提醒时间
     */
    void configureRemindTime();

    /**
     * 配置是否显示学习计划的序号
     */
    void configureSequence(boolean isChecked);

    /**
     * 配置是否显示希腊字母标记
     */
    void configureGreekAlphabet(boolean isChecked);

    /**
     * 配置计划组
     */
    void configureEventGroup();
}
