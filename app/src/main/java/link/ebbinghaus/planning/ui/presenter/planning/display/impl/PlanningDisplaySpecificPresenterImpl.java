package link.ebbinghaus.planning.ui.presenter.planning.display.impl;

import java.util.List;

import link.ebbinghaus.planning.core.service.PlanningDisplaySpecificService;
import link.ebbinghaus.planning.core.model.local.sys.Tab;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplaySpecificServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplaySpecificPresenter;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplaySpecificView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplaySpecificPresenterImpl implements PlanningDisplaySpecificPresenter {
    private PlanningDisplaySpecificView mView;
    private PlanningDisplaySpecificService mPlanningDisplaySpecificService;

    public PlanningDisplaySpecificPresenterImpl(PlanningDisplaySpecificView planningDisplaySpecificView) {
        this.mView = planningDisplaySpecificView;
        mPlanningDisplaySpecificService = new PlanningDisplaySpecificServiceImpl();
    }

    @Override
    public void configureRelatedViewPagerTabLayout() {
        List<Tab> tabs = mPlanningDisplaySpecificService.makePlanningDisplaySpecificTabs();
        mView.bindViewPagerToTabLayout(tabs);
    }

}
