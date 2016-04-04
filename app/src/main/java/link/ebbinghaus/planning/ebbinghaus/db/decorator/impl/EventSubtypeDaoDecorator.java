package link.ebbinghaus.planning.ebbinghaus.db.decorator.impl;

import link.ebbinghaus.planning.ebbinghaus.db.dao.EventSubtypeDao;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;

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
