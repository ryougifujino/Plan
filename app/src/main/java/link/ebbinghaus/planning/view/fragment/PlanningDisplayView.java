package link.ebbinghaus.planning.view.fragment;

import java.util.List;

import link.ebbinghaus.planning.model.entity.sys.Tab;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public interface PlanningDisplayView {

    /**
     * 将ViewPager和TabLayout联系在一起,也可以进行一些对ViewPager、TabLayout的设置
     * @param tabs 与Tab相关的参数
     */
    void bindViewPagerToTabLayout(List<Tab> tabs);

    /**
     * 预设置Toolbar上的Date(TextView)
     */
    void presetToolbarDate();
    /**
     * 设置Toolbar上的Date(TextView)
     */
    void setToolbarDate();
}
