package link.ebbinghaus.planning.view.fragment;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public interface PlanningDoneView {

    /**
     * 设置(初始化)RecyclerView
     * @param events RecyclerView需要的数据
     */
    void setRecyclerView(List<Event> events);
}
