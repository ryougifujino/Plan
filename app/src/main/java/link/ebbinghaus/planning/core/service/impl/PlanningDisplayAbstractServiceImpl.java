package link.ebbinghaus.planning.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.app.constant.module.PlanningDisplayConstant;
import link.ebbinghaus.planning.core.db.dao.EventDao;
import link.ebbinghaus.planning.core.db.dao.EventGroupDao;
import link.ebbinghaus.planning.core.db.decorator.impl.EventDaoDecorator;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.model.local.po.EventGroup;
import link.ebbinghaus.planning.core.model.local.sys.Tab;
import link.ebbinghaus.planning.core.service.PlanningDisplayAbstractService;
import link.ebbinghaus.planning.ui.view.planning.display.fragment.PlanningDisplayAbstAllFragment;
import link.ebbinghaus.planning.ui.view.planning.display.fragment.PlanningDisplayEventGroupFragment;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplayAbstractServiceImpl implements PlanningDisplayAbstractService {
    @Override
    public List<Tab> makePlanningDisplayAbstractTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_ABST_ALL, new PlanningDisplayAbstAllFragment()));
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_ABST_GROUP, PlanningDisplayEventGroupFragment.newInstance(false)));
        return tabs;
    }

    @Override
    public List<Event> findAllAbstEvent() {
        EventDaoDecorator dao = new EventDaoDecorator();
        List<Event> events = dao.selectAbstAllEvents();
        dao.closeDB();
        return events;
    }

    @Override
    public List<Event> findAbstEventsByDescription(String key) {
        EventDaoDecorator dao = new EventDaoDecorator();
        List<Event> events = dao.selectAbstEventsByDescription(key);
        dao.closeDB();
        return events;
    }

    @Override
    public void removeAbstEvent(Long pk, Long groupPk) {
        EventDao dao = new EventDao();
        EventGroupDao eventGroupDao = new EventGroupDao();
        dao.beginTransaction();
        try {
            EventGroup eg = eventGroupDao.selectByPrimaryKey(groupPk);
            if (eg != null) {
                eg.setAbstractEventCount(eg.getAbstractEventCount() - 1);
                eventGroupDao.updateByPrimaryKey(eg);
            }
            dao.deleteByPrimaryKey(pk);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
        eventGroupDao.closeDB();
        dao.closeDB();
    }


}
