package link.ebbinghaus.planning.presenter.planning.display.impl;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDisplaySpecificModel;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.impl.PlanningDisplaySpecificModelImpl;
import link.ebbinghaus.planning.presenter.planning.display.PlanningDisplaySpecificPresenter;
import link.ebbinghaus.planning.view.planning.display.PlanningDisplaySpecificView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplaySpecificPresenterImpl implements PlanningDisplaySpecificPresenter {
    private PlanningDisplaySpecificView mView;
    private PlanningDisplaySpecificModel mPlanningDisplaySpecificModel;

    public PlanningDisplaySpecificPresenterImpl(PlanningDisplaySpecificView planningDisplaySpecificView) {
        this.mView = planningDisplaySpecificView;
        mPlanningDisplaySpecificModel = new PlanningDisplaySpecificModelImpl();
    }

    @Override
    public void configureRelatedViewPagerTabLayout() {
        List<Tab> tabs = mPlanningDisplaySpecificModel.makePlanningDisplaySpecificTabs();
        mView.bindViewPagerToTabLayout(tabs);
    }

}
