package link.ebbinghaus.planning.ui.view.planning.done;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.po.Event;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public interface PlanningDoneView {

    /**
     * 初始化toolbar
     */
    void initToolbar();

    /**
     * 设置(初始化)RecyclerView
     * @param events RecyclerView需要的数据
     */
    void setRecyclerView(List<Event> events);
}
