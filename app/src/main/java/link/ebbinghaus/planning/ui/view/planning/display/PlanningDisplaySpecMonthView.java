package link.ebbinghaus.planning.ui.view.planning.display;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public interface PlanningDisplaySpecMonthView {

    /**
     * 初始化RecyclerView
     */
    void initRecyclerView();

    /**
     * 注册ToolbarDateChangeListener监听器
     */
    void registerToolbarDateChangeListener();

    /**
     * 为是否调用onCreateView设置标记
     */
    void setOnCreateViewFlag();

}
