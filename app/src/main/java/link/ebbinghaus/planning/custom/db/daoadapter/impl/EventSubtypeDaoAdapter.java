package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import link.ebbinghaus.planning.custom.db.dao.EventSubtypeDao;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class EventSubtypeDaoAdapter extends BaseDaoAdapter<EventSubtype> {
    private EventSubtypeDao dao;

    public EventSubtypeDaoAdapter() {
        super(new EventSubtypeDao());
        dao = (EventSubtypeDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }
}
