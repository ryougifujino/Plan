package link.ebbinghaus.planning.core.db.decorator.impl;

import java.util.List;

import link.ebbinghaus.planning.core.db.dao.EventGroupDao;
import link.ebbinghaus.planning.core.model.local.po.EventGroup;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class EventGroupDaoDecorator extends BaseDaoDecorator<EventGroup> {
    private EventGroupDao dao;

    public EventGroupDaoDecorator() {
        super(new EventGroupDao());
        dao = (EventGroupDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }

    /**
     * 查找所有的具体计划组
     * @return 所有的具体计划组
     */
    public List<EventGroup> selectAllSpecEventGroup(){
        return dao.selectAllSpecEventGroup();
    }

    /**
     * 查找所有的模糊计划组
     * @return 所有的模糊计划组
     */
    public List<EventGroup> selectAllAbstEventGroup(){
        return dao.selectAllAbstEventGroup();
    }
}
