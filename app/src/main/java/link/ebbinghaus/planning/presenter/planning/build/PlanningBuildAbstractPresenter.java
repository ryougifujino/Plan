package link.ebbinghaus.planning.presenter.planning.build;

import link.ebbinghaus.planning.model.entity.po.EventGroup;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public interface PlanningBuildAbstractPresenter {

    /**
     * 初始化地注册一些必要的监听器
     */
    void registerListeners();

    /**
     *
     * 把模板填入描述控件(EditText)中
     * @param template 模板
     */
    void configureDescription(String template);

    /**
     * 配置计划组
     * @param eventGroup 计划组
     */
    void configureEventGroup(EventGroup eventGroup);

    /**
     * 设置默认值
     */
    void setDefaultValues();
}
