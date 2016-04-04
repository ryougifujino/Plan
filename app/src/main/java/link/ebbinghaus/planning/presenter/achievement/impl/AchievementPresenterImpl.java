package link.ebbinghaus.planning.presenter.achievement.impl;

import java.util.List;

import link.ebbinghaus.planning.model.AchievementModel;
import link.ebbinghaus.planning.model.entity.po.Achievement;
import link.ebbinghaus.planning.model.impl.AchievementModelImpl;
import link.ebbinghaus.planning.presenter.achievement.AchievementPresenter;
import link.ebbinghaus.planning.view.achievement.AchievementView;

/**
 * Created by WINFIELD on 2016/4/2.
 */
public class AchievementPresenterImpl implements AchievementPresenter{

    private AchievementView mView;
    private AchievementModel mAchievementModel;

    public AchievementPresenterImpl(AchievementView view) {
        this.mView = view;
        mAchievementModel = new AchievementModelImpl();
    }

    @Override
    public List<Achievement> obtainAchievements() {
        return mAchievementModel.findAllAchievement();
    }
}
