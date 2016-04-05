package link.ebbinghaus.planning.core.service.impl;

import java.util.List;

import link.ebbinghaus.planning.core.service.AchievementService;
import link.ebbinghaus.planning.core.model.po.Achievement;
import link.ebbinghaus.planning.core.db.decorator.impl.AchievementDaoDecorator;

/**
 * Created by WINFIELD on 2016/4/2.
 */
public class AchievementServiceImpl implements AchievementService {

    @Override
    public List<Achievement> findAllAchievement() {
        AchievementDaoDecorator dao = new AchievementDaoDecorator();
        List<Achievement> achievements = dao.selectAll();
        dao.closeDB();
        return achievements;
    }
}
