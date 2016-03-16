package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import link.ebbinghaus.planning.custom.db.dao.EventSubtypeDao;
import link.ebbinghaus.planning.custom.db.daoadapter.BaseDaoAdapter;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class EventSubtypeDaoAdapter implements BaseDaoAdapter<EventSubtype> {
    private EventSubtypeDao dao;

    @Override
    public void add(EventSubtype eventSubtype) {
        dao = new EventSubtypeDao();
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        dao.removeByPrimaryKey(pk);
    }

    @Override
    public void modifyByPrimaryKey(EventSubtype eventSubtype) {
        dao.modifyByPrimaryKey(eventSubtype);
    }

    @Override
    public EventSubtype findByPrimaryKey(Integer pk) {
        return dao.findByPrimaryKey(pk);
    }

    @Override
    public void closeDB() {
        dao.closeDB();
    }
}
