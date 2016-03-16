package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import link.ebbinghaus.planning.custom.db.dao.EventDao;
import link.ebbinghaus.planning.custom.db.daoadapter.BaseDaoAdapter;
import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class EventDaoAdapter implements BaseDaoAdapter<Event> {
    private EventDao dao;

    @Override
    public void add(Event event) {
        dao = new EventDao();
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        dao.removeByPrimaryKey(pk);
    }

    @Override
    public void modifyByPrimaryKey(Event event) {
        dao.modifyByPrimaryKey(event);
    }

    @Override
    public Event findByPrimaryKey(Integer pk) {
        return dao.findByPrimaryKey(pk);
    }

    @Override
    public void closeDB() {
        dao.closeDB();
    }
}
