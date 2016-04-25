package link.ebbinghaus.planning.ui.presenter.achievement.impl;

import java.util.List;

import link.ebbinghaus.planning.core.service.AchievementService;
import link.ebbinghaus.planning.core.model.local.po.Achievement;
import link.ebbinghaus.planning.core.service.impl.AchievementServiceImpl;
import link.ebbinghaus.planning.ui.presenter.achievement.AchievementPresenter;
import link.ebbinghaus.planning.ui.view.achievement.AchievementView;

/**
 * Created by WINFIELD on 2016/4/2.
 */
public class AchievementPresenterImpl implements AchievementPresenter {

    private AchievementView mView;
    private AchievementService mAchievementService;

    public AchievementPresenterImpl(AchievementView view) {
        this.mView = view;
        mAchievementService = new AchievementServiceImpl();
    }

    @Override
    public List<Achievement> obtainAchievements() {
        return mAchievementService.findAllAchievement();
    }
}
