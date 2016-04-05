package link.ebbinghaus.planning.core.service;

import java.util.List;

import link.ebbinghaus.planning.core.model.po.Achievement;

/**
 * Created by WINFIELD on 2016/4/2.
 */
public interface AchievementService {

    /**
     * 获取所有的成就
     * @return 所有成就
     */
    List<Achievement> findAllAchievement();
}
