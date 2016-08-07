package link.ebbinghaus.planning.core.service;

import com.yurikami.lib.model.Datetime;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.sys.Tab;
import link.ebbinghaus.planning.core.model.local.vo.planning.display.SpecEventDetailVo;
import link.ebbinghaus.planning.core.model.local.po.Event;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public interface PlanningDisplaySpecificService {

    /**
     * 创建PlanningBuildSpecific的最顶层tabs
     * @return PlanningBuildSpecific的tabs
     */
    List<Tab> makePlanningDisplaySpecificTabs();

    /**
     * 将某月里的Event集合排列成按天排序的Event集合的数组
     * @param events 一个月里的Event
     * @param dayInMonth 这个月的天数
     * @return rows 按天排序的Event数组
     */
    List<Event>[] eventsToBlocks(List<Event> events, int dayInMonth);

    /**
     * 制作某一个月的日和星期的集合
     * @param dayWeekListitems (存放用)日和星期的集合
     * @param dayInMonth 这个月的天数
     * @param datetime 某年某月,必有 year month
     */
    void makeDayWeekListitems(List<Datetime> dayWeekListitems, int dayInMonth, Datetime datetime);

    /**
     * 查找某年某月的具体计划信息
     * @param datetime 某年某月
     * @return 某年某月的具体计划信息
     */
    List<Event> findSpecMonthEvents(Datetime datetime);

    /**
     * 查找某一天所在星期的具体计划信息
     * @param datetime 某一天
     * @return 某一天所在星期的具体计划信息
     */
    List<Event> findSpecWeekEvents(Datetime datetime);

    /**
     * 查找完整的具体计划详情(已有event信息)
     * @param specEventDetail 具体计划详情(已有event)
     */
    void findSpecEventDetailTo(SpecEventDetailVo specEventDetail);

    /**
     * 删除具体计划<br>
     * 这个具体计划可能是学习计划，也可能是普通计划<br>
     * 并且会处理其相关的计划组、希腊字母表、学习计划组
     * @param event 要删除的计划信息
     */
    void removeSpecEventAndProcessRelated(Event event);

    /** 常规更新 */
    void updateEvent(Event event);

}
