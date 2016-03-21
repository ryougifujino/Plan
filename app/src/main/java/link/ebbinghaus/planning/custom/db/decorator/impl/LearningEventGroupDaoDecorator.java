package link.ebbinghaus.planning.custom.db.decorator.impl;

import link.ebbinghaus.planning.custom.db.dao.LearningEventGroupDao;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class LearningEventGroupDaoDecorator extends BaseDaoDecorator<LearningEventGroup> {
    private LearningEventGroupDao dao;

    public LearningEventGroupDaoDecorator() {
        super(new LearningEventGroupDao());
        dao = (LearningEventGroupDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }
}
