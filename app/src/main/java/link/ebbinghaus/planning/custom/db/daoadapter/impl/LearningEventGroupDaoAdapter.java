package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import link.ebbinghaus.planning.custom.db.dao.LearningEventGroupDao;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class LearningEventGroupDaoAdapter extends BaseDaoAdapter<LearningEventGroup> {
    private LearningEventGroupDao dao;

    public LearningEventGroupDaoAdapter() {
        super(new LearningEventGroupDao());
        dao = (LearningEventGroupDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }
}
