package link.ebbinghaus.planning.presenter.impl;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDisplaySpecificModel;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.impl.PlanningDisplaySpecificModelImpl;
import link.ebbinghaus.planning.presenter.PlanningDisplaySpecificPresenter;
import link.ebbinghaus.planning.view.fragment.PlanningDisplaySpecificView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplaySpecificPresenterImpl implements PlanningDisplaySpecificPresenter {
    private PlanningDisplaySpecificView mPlanningDisplaySpecificView;
    private PlanningDisplaySpecificModel mPlanningDisplaySpecificModel;

    public PlanningDisplaySpecificPresenterImpl(PlanningDisplaySpecificView mPlanningDisplaySpecificView) {
        this.mPlanningDisplaySpecificView = mPlanningDisplaySpecificView;
        mPlanningDisplaySpecificModel = new PlanningDisplaySpecificModelImpl();
    }

    @Override
    public void configureRelatedViewPagerTabLayout() {
        List<Tab> tabs = mPlanningDisplaySpecificModel.makePlanningDisplayTabs();
        mPlanningDisplaySpecificView.bindViewPagerToTabLayout(tabs);
    }

}
