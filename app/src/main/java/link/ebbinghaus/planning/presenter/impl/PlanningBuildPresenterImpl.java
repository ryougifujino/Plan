package link.ebbinghaus.planning.presenter.impl;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningBuildModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.impl.PlanningBuildModelImpl;
import link.ebbinghaus.planning.presenter.PlanningBuildPresenter;
import link.ebbinghaus.planning.view.activity.PlanningBuildView;

/**
 * Created by WINFIELD on 2016/3/8.
 */
public class PlanningBuildPresenterImpl implements PlanningBuildPresenter {
    private PlanningBuildView mPlanningBuildView;
    private PlanningBuildModel mPlanningBuildModel;

    public PlanningBuildPresenterImpl(PlanningBuildView planningBuildView) {
        this.mPlanningBuildView = planningBuildView;
        mPlanningBuildModel = new PlanningBuildModelImpl();
    }

    @Override
    public void configureToolbar() {
        mPlanningBuildView.setToolbar();
    }

    @Override
    public void configureRelatedViewPagerTabLayout() {
        List<Tab> tabs = mPlanningBuildModel.makePlanningBuildTabs();
        mPlanningBuildView.bindViewPagerToTabLayout(tabs);
    }

    @Override
    public void saveSpecificEvent() {
        Event event = new Event();
        mPlanningBuildView.obtainSpecificEvent(event);
        mPlanningBuildModel.persistLearningEvent(event);

    }

    @Override
    public void doneSpecificEvent() {

    }
}
