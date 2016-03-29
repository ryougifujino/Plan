package link.ebbinghaus.planning.presenter;

import com.yurikami.lib.entity.Datetime;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public interface PlanningDisplaySpecWeekPresenter {

    /**
     * 第一次渲染按周显示视图,<br>
     * 将会做一些初始化工作(如设置RecyclerVie的Adapter之类)
     */
    void initWeekView();

    /**
     * 获得某一天所在星期的具体计划信息
     * @param datetime 某一天
     * @return 某一天所在星期的具体计划信息
     */
    List<Event> obtainSpecWeekEvents(Datetime datetime);

}
