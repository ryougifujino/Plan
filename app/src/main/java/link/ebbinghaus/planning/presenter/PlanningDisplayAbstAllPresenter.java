package link.ebbinghaus.planning.presenter;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public interface PlanningDisplayAbstAllPresenter {

    /**
     * 第一次渲染按周显示视图,<br>
     * 将会做一些初始化工作(如设置RecyclerVie的Adapter之类)
     */
    void initAbstAllView();

    /**
     * 获取所有的模糊计划
     * @return 获取所有的模糊计划
     */
    List<Event> obtainAllAbstractEvents();
}
