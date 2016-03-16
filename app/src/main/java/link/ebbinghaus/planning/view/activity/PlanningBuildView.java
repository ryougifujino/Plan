package link.ebbinghaus.planning.view.activity;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.sys.Tab;

/**
 * Created by WINFIELD on 2016/3/8.
 */
public interface PlanningBuildView {
    /**
     * 将ViewPager和TabLayout联系在一起,也可以进行一些对ViewPager、TabLayout的设置
     * @param tabs 与Tab相关的参数
     */
    void bindViewPagerToTabLayout(List<Tab> tabs);

    /**
     * 获取填写的具体计划信息
     * @param event 具体计划的主要信息
     */
    void obtainSpecificEvent(Event event);

    /**
     * 获取填写的模糊计划信息
     */
    void obtainAbstractEvent();

    /**
     * 退出计划制定Activity
     */
    void exitPlanningBuildActivity();
}
