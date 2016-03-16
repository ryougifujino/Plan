package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import link.ebbinghaus.planning.custom.db.dao.EventGroupDao;
import link.ebbinghaus.planning.custom.db.daoadapter.BaseDaoAdapter;
import link.ebbinghaus.planning.model.entity.po.EventGroup;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class EventGroupDaoAdapter implements BaseDaoAdapter<EventGroup> {
    private EventGroupDao dao;

    @Override
    public void add(EventGroup eventGroup) {
        dao = new EventGroupDao();
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        dao.removeByPrimaryKey(pk);
    }

    @Override
    public void modifyByPrimaryKey(EventGroup eventGroup) {
        dao.modifyByPrimaryKey(eventGroup);
    }

    @Override
    public EventGroup findByPrimaryKey(Integer pk) {
        return dao.findByPrimaryKey(pk);
    }

    @Override
    public void closeDB() {
        dao.closeDB();
    }
}
