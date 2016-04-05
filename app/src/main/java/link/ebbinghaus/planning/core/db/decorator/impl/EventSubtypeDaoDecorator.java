package link.ebbinghaus.planning.core.db.decorator.impl;

import link.ebbinghaus.planning.core.db.dao.EventSubtypeDao;
import link.ebbinghaus.planning.core.model.po.EventSubtype;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class EventSubtypeDaoDecorator extends BaseDaoDecorator<EventSubtype> {
    private EventSubtypeDao dao;

    public EventSubtypeDaoDecorator() {
        super(new EventSubtypeDao());
        dao = (EventSubtypeDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }
}
