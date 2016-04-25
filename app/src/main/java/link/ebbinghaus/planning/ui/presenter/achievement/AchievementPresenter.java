package link.ebbinghaus.planning.ui.presenter.achievement;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.po.Achievement;

/**
 * Created by WINFIELD on 2016/4/2.
 */
public interface AchievementPresenter {

    /**
     * 获取成就
     * @return 成就
     */
    List<Achievement> obtainAchievements();
}
