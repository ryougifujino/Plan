package link.ebbinghaus.planning.view.planning.build;

import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.entity.vo.planning.build.InputEventVo;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public interface PlanningBuildAbstractView {

    /**
     * 设置一些监听器
     */
    void setListeners();

    /**
     * 设置计划组显示的默认文本(为"无")
     */
    void setDefaultEventGroupText();

    /**
     * 获取控件中的值,并以InputEventVo对象的形式返回
     * @return 控件中的值
     */
    InputEventVo getInputEvent();

    /**
     * 设置快速模板到描述EditText里面
     * @param template 模板
     */
    void setFastTemplate(String template);


    /**
     * 设置计划组
     * @param eventGroup
     */
    void setEventGroup(EventGroup eventGroup);


}
