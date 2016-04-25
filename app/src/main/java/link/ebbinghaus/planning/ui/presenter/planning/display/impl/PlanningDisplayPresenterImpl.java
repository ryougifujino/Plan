package link.ebbinghaus.planning.ui.presenter.planning.display.impl;

import java.util.List;

import link.ebbinghaus.planning.core.service.PlanningDisplayService;
import link.ebbinghaus.planning.core.model.local.sys.Tab;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplayServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplayPresenter;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplayView;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class PlanningDisplayPresenterImpl implements PlanningDisplayPresenter {
    private PlanningDisplayView mView;
    private PlanningDisplayService mPlanningDisplayService;

    public PlanningDisplayPresenterImpl(PlanningDisplayView planningDisplayView) {
        this.mView = planningDisplayView;
        mPlanningDisplayService = new PlanningDisplayServiceImpl();
    }


    @Override
    public void configureRelatedViewPagerTabLayout() {
        List<Tab> tabs = mPlanningDisplayService.makePlanningDisplayTabs();
        mView.bindViewPagerToTabLayout(tabs);
    }

    @Override
    public void preprocessToolbarDate() { mView.presetToolbarDate();}

    @Override
    public void configureToolbarDate() {
        mView.setToolbarDate();
    }

}
