package link.ebbinghaus.planning.core.db.decorator.impl;

import link.ebbinghaus.planning.core.db.dao.LearningEventGroupDao;
import link.ebbinghaus.planning.core.model.po.LearningEventGroup;

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

    /**
     * 在完成一个学习计划后更新相应的学习计划组
     * @param learningEventGroup 更新用到的数据
     */
    public void updateLearningEventGroupAfterFinishing1Event(LearningEventGroup learningEventGroup){
        dao.beginTransaction();
        try {
            dao.updateLearningEventGroupAfterFinishing1Event(learningEventGroup);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }
}
