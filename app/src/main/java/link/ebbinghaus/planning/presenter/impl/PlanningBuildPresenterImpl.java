package link.ebbinghaus.planning.presenter.impl;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDisplayModel;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.impl.PlanningDisplayModelImpl;
import link.ebbinghaus.planning.presenter.PlanningBuildPresenter;
import link.ebbinghaus.planning.view.activity.PlanningBuildView;

/**
 * Created by WINFIELD on 2016/3/8.
 */
public class PlanningBuildPresenterImpl implements PlanningBuildPresenter {
    private PlanningBuildView mPlanningBuildView;
    private PlanningDisplayModel mPlanningDisplayModel;

    public PlanningBuildPresenterImpl(PlanningBuildView planningBuildView) {
        this.mPlanningBuildView = planningBuildView;
        mPlanningDisplayModel = new PlanningDisplayModelImpl();
    }

    @Override
    public void configureRelatedViewPagerTabLayout() {
        List<Tab> tabs = mPlanningDisplayModel.makePlanningBuildTabs();
        mPlanningBuildView.bindViewPagerToTabLayout(tabs);
    }
}
