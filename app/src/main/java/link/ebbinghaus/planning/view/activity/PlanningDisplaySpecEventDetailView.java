package link.ebbinghaus.planning.view.activity;

/**
 * Created by WINFIELD on 2016/3/24.
 */
public interface PlanningDisplaySpecEventDetailView {

    /**
     * 获取Intent数据
     */
    void getIntentData();

    /**
     * 选择视图模式(学习计划模式或普通计划模式)
     */
    void selectViewMode();

    /**
     * 给本视图的控件注册对应的监听器
     */
    void registerViewListener();

    /**
     * 用整理好的视图数据填充视图
     */
    void fillViewWithData();
}
