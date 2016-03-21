package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import link.ebbinghaus.planning.custom.db.dao.EventDao;
import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class EventDaoAdapter extends BaseDaoAdapter<Event> {
    private EventDao dao;

    public EventDaoAdapter(){
        super(new EventDao());
        dao = (EventDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }
}
