package link.ebbinghaus.planning.model;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Achievement;

/**
 * Created by WINFIELD on 2016/4/2.
 */
public interface AchievementModel {

    /**
     * 获取所有的成就
     * @return 所有成就
     */
    List<Achievement> findAllAchievement();
}
