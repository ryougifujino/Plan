package link.ebbinghaus.planning.presenter;

import com.yurikami.lib.entity.Datetime;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public interface PlanningDisplaySpecMonthPresenter {

    /**
     * 第一次渲染按月显示视图,<br>
     * 将会做一些初始化工作(如设置RecyclerVie的Adapter之类)
     * @param datetime 目标日期
     */
    void initMonthView(Datetime datetime);

    /**
     * 渲染目标日期的那个月的按月视图,<br>
     * 调用此方法之前必须先调用过一次initMonthView方法
     * @param datetime 目标日期
     */
    void renderMonthView(Datetime datetime);

}
