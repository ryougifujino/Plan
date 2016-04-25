package link.ebbinghaus.planning.ui.view.planning.display;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.sys.Tab;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public interface PlanningDisplaySpecificView{
    /**
     * 将ViewPager和TabLayout联系在一起,也可以进行一些对ViewPager、TabLayout的设置
     * @param tabs 与Tab相关的参数
     */
    void bindViewPagerToTabLayout(List<Tab> tabs);
}
