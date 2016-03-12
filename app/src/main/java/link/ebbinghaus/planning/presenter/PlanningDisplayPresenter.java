package link.ebbinghaus.planning.presenter;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public interface PlanningDisplayPresenter {

    /**
     * 配置相关联的Viewpager和TabLayout
     */
    void configureRelatedViewPagerTabLayout();

    /**
     * 对Toolbar上的Date(TextView)进行预处理
     */
    void preprocessToolbarDate();

    /**
     * 配置Toolbar上的Date(TextView)
     */
    void configureToolbarDate();

}
