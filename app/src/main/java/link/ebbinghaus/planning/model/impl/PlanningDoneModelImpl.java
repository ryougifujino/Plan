package link.ebbinghaus.planning.model.impl;

import java.util.List;

import link.ebbinghaus.planning.custom.db.decorator.impl.EventDaoDecorator;
import link.ebbinghaus.planning.model.PlanningDoneModel;
import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public class PlanningDoneModelImpl implements PlanningDoneModel {

    @Override
    public List<Event> findLast2DaysSpecEventsFromToday() {
        EventDaoDecorator dao = new EventDaoDecorator();
        List<Event> events = dao.selectLast2DaysSpecEvents();
        dao.closeDB();
        return events;
    }
}
