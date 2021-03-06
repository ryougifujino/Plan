package link.ebbinghaus.planning.ui.presenter.planning.display;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.model.local.po.EventGroup;

/**
 * Created by WINFIELD on 2016/3/29.
 */
public interface PlanningDisplayEventGroupDetailPresenter {

    /**
     *
     * 获取计划组详情页面所需数据
     * @param eventGroupType true:具体计划 false:模糊计划
     * @param eventGroup 计划组信息,这里会用到计划组id
     * @return 计划组详情页面列表的数据
     */
    List<Event> obtainEventGroupDetailData(boolean eventGroupType,EventGroup eventGroup);

    /**
     * 根据id获取计划组最新值
     * @param pk 计划组主键
     * @return 计划组最新值
     */
    EventGroup obtainEventGroup(long pk);

    /**
     * 配置RecyclerView
     */
    void configureRecyclerView();

}
