package link.ebbinghaus.planning.core.db.decorator.impl;

import link.ebbinghaus.planning.core.db.dao.AchievementDao;
import link.ebbinghaus.planning.core.model.po.Achievement;

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
