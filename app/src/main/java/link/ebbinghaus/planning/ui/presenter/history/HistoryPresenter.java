package link.ebbinghaus.planning.ui.presenter.history;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.po.Event;

/**
 * Created by WINFIELD on 2016/4/5.
 */
public interface HistoryPresenter {

    /**
     * 获取已完成的具体计划
     * @return 已完成的具体计划
     */
    List<Event> obtainSpecDoneEvents();

    /**
     * 获取已过期的具体计划
     * @return 已过期的具体计划
     */
    List<Event> obtainSpecExpiredEvents();
}
