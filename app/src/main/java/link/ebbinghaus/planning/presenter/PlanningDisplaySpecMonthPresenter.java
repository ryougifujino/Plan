package link.ebbinghaus.planning.presenter;

import com.yurikami.lib.entity.Datetime;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public interface PlanningDisplaySpecMonthPresenter {

    /**
     * 获取某个月的具体计划
     * @param datetime 某个月(但有年月日)
     * @return 某个月的具体计划
     */
    List<Event> obtainSpecEvents(Datetime datetime);

    /**
     * 根据获得的具体计划渲染出按月显示视图
     */
    void renderMonthView();

    /**
     * 根据改变的数据刷新按月显示视图
     */
    void refreshMonthView();

    /**
     * 关闭数据库
     */
    void closeDB();
}
