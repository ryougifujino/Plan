package link.ebbinghaus.planning.core.service;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.po.Event;

/**
 * Created by WINFIELD on 2016/4/5.
 */
public interface HistoryService {

    /**
     * 查找所有已经完成的具体计划
     * @return 所有已经完成的具体计划
     */
    List<Event> findAllDoneSpecEvents();
}
