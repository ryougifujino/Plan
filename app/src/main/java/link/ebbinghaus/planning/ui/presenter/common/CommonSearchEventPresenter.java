package link.ebbinghaus.planning.ui.presenter.common;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.po.Event;

/**
 * Created by WINFIELD on 2016/10/18.
 */

public interface CommonSearchEventPresenter {

    List<Event> searchEvents(String key, boolean specific);

}
