package link.ebbinghaus.planning.ui.presenter.planning.build;

import com.yurikami.lib.model.Datetime;

import link.ebbinghaus.planning.core.model.local.po.EventGroup;
import link.ebbinghaus.planning.core.model.local.po.EventSubtype;
import link.ebbinghaus.planning.core.model.local.vo.planning.build.InputEventVo;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public interface PlanningBuildSpecificPresenter {

    /**
     * 初始化地注册一些必要的监听器
     */
    void registerListeners();

    /**
     * 从数据库里获取输入默认值,然后再设置
     * @param inputEvent 输入界面的底层值
     */
    void getAndSetDefaultInputValues(InputEventVo inputEvent);

    /**
     * 根据是普通计划还是学习计划切换到相应的面板上去
     * @param eventType true:普通计划 false:学习计划
     */
    void switchBuildPanel(boolean eventType);

    /**
     * 配置计划子类型
     * @param result 从SelectActivity返回的结果
     */
    void configureEventSubtype(EventSubtype result);

    /**
     *
     * 把模板填入描述控件(EditText)中
     * @param template 模板
     */
    void configureDescription(String template);

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
     * @param eventGroup 计划组
     */
    void configureEventGroup(EventGroup eventGroup);

    /** 初始化宽度图表 */
    void initChart(Datetime initDate);

    /**
     * 更新宽度图表
     * @param startDateTimestamp 开始年月日时间戳
     * @param strategy 如果是学习计划将会有方案
     */
    void updateChart(long startDateTimestamp, int[] strategy);

}
