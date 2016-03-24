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
