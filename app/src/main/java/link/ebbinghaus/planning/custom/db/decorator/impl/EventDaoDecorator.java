package link.ebbinghaus.planning.custom.db.decorator.impl;

import link.ebbinghaus.planning.custom.db.dao.EventDao;
import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class EventDaoDecorator extends BaseDaoDecorator<Event> {
    private EventDao dao;

    public EventDaoDecorator(){
        super(new EventDao());
        dao = (EventDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }
}
