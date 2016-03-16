package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import link.ebbinghaus.planning.custom.db.dao.LearningEventGroupDao;
import link.ebbinghaus.planning.custom.db.daoadapter.BaseDaoAdapter;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class LearningEventGroupDaoAdapter implements BaseDaoAdapter<LearningEventGroup> {
    private LearningEventGroupDao dao;

    @Override
    public void add(LearningEventGroup learningEventGroup) {
        dao = new LearningEventGroupDao();
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        dao.removeByPrimaryKey(pk);
    }

    @Override
    public void modifyByPrimaryKey(LearningEventGroup learningEventGroup) {
        dao.modifyByPrimaryKey(learningEventGroup);
    }

    @Override
    public LearningEventGroup findByPrimaryKey(Integer pk) {
        return dao.findByPrimaryKey(pk);
    }

    @Override
    public void closeDB() {
        dao.closeDB();
    }
}
