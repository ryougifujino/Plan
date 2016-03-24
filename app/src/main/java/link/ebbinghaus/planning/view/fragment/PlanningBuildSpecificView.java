package link.ebbinghaus.planning.view.fragment;

import link.ebbinghaus.planning.model.entity.po.DefaultInputValue;
import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;
import link.ebbinghaus.planning.model.entity.vo.InputEventVo;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public interface PlanningBuildSpecificView {

    /**
     * 设置一些监听器
     */
    void setListeners();

    /**
     * 获取控件中的值,并以Event对象的形式返回
     * @return 控件中的值
     */
    InputEventVo getInputEvent();

    /**
     * 设置数据库里存放的输入预设值
     */
    void setDefaultInputValue(DefaultInputValue defaultInputValue);

    /**
     * 根据普通计划还是学习计划设置面板
     */
    void setBuildPanel();

    /**
     * 设置计划子类型
     * @param result 从SelectActivity返回的结果,用于设置
     */
    void setSubtype(EventSubtype result);

    /**
     * 设置快速模板到描述EditText里面
     * @param template 模板
     */
    void setFastTemplate(String template);

    /**
     * 选择学习计划的方案(策略)
     */
    void selectStrategy();

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
     * @param eventGroup
     */
    void setEventGroup(EventGroup eventGroup);
}
