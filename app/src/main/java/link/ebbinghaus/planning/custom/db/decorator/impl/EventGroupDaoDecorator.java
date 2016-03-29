package link.ebbinghaus.planning.custom.db.decorator.impl;

import java.util.List;

import link.ebbinghaus.planning.custom.db.dao.EventGroupDao;
import link.ebbinghaus.planning.model.entity.po.EventGroup;

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
    public List<EventGroup> selectAllSpectEventGroup(){
        return dao.selectAllSpectEventGroup();
    }

    /**
     * 查找所有的模糊计划组
     * @return 所有的模糊计划组
     */
    public List<EventGroup> selectAllAbstEventGroup(){
        return dao.selectAllAbstEventGroup();
    }
}
