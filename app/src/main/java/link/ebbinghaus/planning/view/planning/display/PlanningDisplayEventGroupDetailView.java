package link.ebbinghaus.planning.view.planning.display;

/**
 * Created by WINFIELD on 2016/3/29.
 */
public interface PlanningDisplayEventGroupDetailView {

    /**
     * 获取Intent数据
     */
    void getIntentData();

    /**
     * 给recyclerView设置一个头部
     */
    void setRecyclerViewHeader();

    /**
     * 为计划组详情列表设置适配器
     */
    void setRecyclerViewAdapter();

}
