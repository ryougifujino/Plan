package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import link.ebbinghaus.planning.custom.db.dao.EventGroupDao;
import link.ebbinghaus.planning.model.entity.po.EventGroup;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class EventGroupDaoAdapter extends BaseDaoAdapter<EventGroup> {
    private EventGroupDao dao;

    public EventGroupDaoAdapter() {
        super(new EventGroupDao());
        dao = (EventGroupDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }
}
