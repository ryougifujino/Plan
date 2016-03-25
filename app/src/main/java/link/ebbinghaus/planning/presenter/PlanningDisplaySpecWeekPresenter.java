package link.ebbinghaus.planning.presenter;

import com.yurikami.lib.entity.Datetime;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public interface PlanningDisplaySpecWeekPresenter {

    /**
     * 获取某日所在周的具体计划
     * @param datetime 某日(有年月日)
     * @return 某日所在的那周的具体计划
     */
    List<Event> obtainSpecWeekEvents(Datetime datetime);

    /**
     * 根据获得的具体计划渲染出按周显示视图
     */
    void renderWeekView();

    /**
     * 根据改变的数据刷新按周显示视图
     */
    void refreshWeekView();

}
