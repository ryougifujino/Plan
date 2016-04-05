package link.ebbinghaus.planning.ui.presenter.planning.build;

/**
 * Created by WINFIELD on 2016/3/8.
 */
public interface PlanningBuildPresenter {
    /**
     * 对toolbar进行设置
     */
    void configureToolbar();

    /**
     * 配置相关联的Viewpager和TabLayout
     */
    void configureRelatedViewPagerTabLayout();

    /**
     * 保存(不退出本Activity,但要清空)Event(普通或学习)
     */
    void saveSpecificEvent();

    /**
     * 完成(保存并退出Activity)Event(普通或学习)
     */
    void doneSpecificEvent();

    /**
     * 保存(不退出本Activity,但要清空)Event(模糊)
     */
    void saveAbstractEvent();

    /**
     * 完成(保存并退出Activity)Event(模糊)
     */
    void doneAbstractEvent();

}
