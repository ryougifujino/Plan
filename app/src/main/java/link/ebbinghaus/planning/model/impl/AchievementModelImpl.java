package link.ebbinghaus.planning.model.impl;

import java.util.List;

import link.ebbinghaus.planning.ebbinghaus.db.decorator.impl.AchievementDaoDecorator;
import link.ebbinghaus.planning.model.AchievementModel;
import link.ebbinghaus.planning.model.entity.po.Achievement;

/**
 * Created by WINFIELD on 2016/4/2.
 */
public class AchievementModelImpl implements AchievementModel {

    @Override
    public List<Achievement> findAllAchievement() {
        AchievementDaoDecorator dao = new AchievementDaoDecorator();
        List<Achievement> achievements = dao.selectAll();
        dao.closeDB();
        return achievements;
    }
}
