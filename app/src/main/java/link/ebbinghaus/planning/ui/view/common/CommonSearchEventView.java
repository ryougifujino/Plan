package link.ebbinghaus.planning.ui.view.common;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.po.Event;

/**
 * Created by WINFIELD on 2016/9/13.
 */
public interface CommonSearchEventView {

    /** 刷新搜索到的结果 */
    void refreshResult(List<Event> result);
}
