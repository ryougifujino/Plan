package link.ebbinghaus.planning.ui.presenter.planning.display.impl;

import java.util.List;

import link.ebbinghaus.planning.core.service.PlanningDisplayAbstractService;
import link.ebbinghaus.planning.core.model.sys.Tab;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplayAbstractServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplayAbstractPresenter;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplayAbstractView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplayAbstractPresenterImpl implements PlanningDisplayAbstractPresenter {
    private PlanningDisplayAbstractView mView;
    private PlanningDisplayAbstractService mPlanningDisplayAbstractService;

    public PlanningDisplayAbstractPresenterImpl(PlanningDisplayAbstractView planningDisplayAbstractView) {
        this.mView = planningDisplayAbstractView;
        mPlanningDisplayAbstractService = new PlanningDisplayAbstractServiceImpl();
    }

    @Override
    public void configureRelatedViewPagerTabLayout() {
        List<Tab> tabs = mPlanningDisplayAbstractService.makePlanningDisplayAbstractTabs();
        mView.bindViewPagerToTabLayout(tabs);
    }


}
