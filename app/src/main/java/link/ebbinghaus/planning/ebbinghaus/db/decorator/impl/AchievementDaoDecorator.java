package link.ebbinghaus.planning.ebbinghaus.db.decorator.impl;

import link.ebbinghaus.planning.ebbinghaus.db.dao.AchievementDao;
import link.ebbinghaus.planning.model.entity.po.Achievement;

/**
 * Created by WINFIELD on 2016/4/1.
 */
public class AchievementDaoDecorator extends BaseDaoDecorator<Achievement>{
    private AchievementDao dao;

    public AchievementDaoDecorator() {
        super(new AchievementDao());
        dao = (AchievementDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }
}
