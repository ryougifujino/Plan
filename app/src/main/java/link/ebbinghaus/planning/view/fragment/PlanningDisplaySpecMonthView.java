package link.ebbinghaus.planning.view.fragment;

import com.yurikami.lib.entity.Datetime;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public interface PlanningDisplaySpecMonthView {

    /**
     * 设置RecyclerView相关的东西
     * @param events 设置RecyclerView用到的数据
     */
    void setRecyclerView(List<Event> events);

    /**
     * 根据获取的新数据刷新RecyclerView
     * @param datetime 根据这个日期获取的刷新的数据
     * @param events 刷新的数据
     */
    void refreshRecyclerView(Datetime datetime,List<Event> events);
}
