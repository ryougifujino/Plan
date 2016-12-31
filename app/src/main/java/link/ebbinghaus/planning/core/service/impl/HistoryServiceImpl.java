package link.ebbinghaus.planning.core.service.impl;

import java.util.List;

import link.ebbinghaus.planning.core.db.decorator.impl.EventDaoDecorator;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.service.HistoryService;

/**
 * Created by WINFIELD on 2016/4/5.
 */
public class HistoryServiceImpl implements HistoryService {
    @Override
    public List<Event> findAllDoneSpecEvents() {
        EventDaoDecorator dao = new EventDaoDecorator();
        List<Event> events = dao.selectAllDoneSpecEvents();
        dao.closeDB();
        return events;
    }

    @Override
    public List<Event> findAllExpiredSpecEvents() {
        EventDaoDecorator dao = new EventDaoDecorator();
        List<Event> events = dao.selectAllExpiredSpecEvents();
        dao.closeDB();
        return events;
    }
}
