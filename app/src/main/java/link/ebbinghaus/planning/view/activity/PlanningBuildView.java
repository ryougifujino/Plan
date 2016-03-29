package link.ebbinghaus.planning.view.activity;

import java.util.List;

import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.entity.vo.planning.build.InputEventVo;

/**
 * Created by WINFIELD on 2016/3/8.
 */
public interface PlanningBuildView {
    /**
     * 对toolbar进行设置
     */
    void setToolbar();

    /**
     * 将ViewPager和TabLayout联系在一起,也可以进行一些对ViewPager、TabLayout的设置
     * @param tabs 与Tab相关的参数
     */
    void bindViewPagerToTabLayout(List<Tab> tabs);

    /**
     * 获取填写的具体计划信息
     * @param inputEventVo 具体计划的主要信息(spec build form里的信息)
     * @return 获取的信息是否合法(即是否通过验证)
     */
    boolean obtainSpecificInputEvent(InputEventVo inputEventVo);

    /**
     * 获取填写的模糊计划信息
     * @param inputEventVo 获取得模糊计划的信息
     * @return 获取的信息是否合法(即是否通过验证)
     */
    boolean obtainAbstractEvent(InputEventVo inputEventVo);

    /**
     * 用于保存成功后调用,重置具体制定的form以准备下一次保存
     */
    void resetSpecForm();

    /**
     * 用于保存成功后调用,重置模糊制定的form以准备下一次保存
     */
    void resetAbstForm();

    /**
     * 退出计划制定Activity
     */
    void exitPlanningBuildActivity();
}
