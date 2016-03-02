package link.ebbinghaus.planning.presenter.impl;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDisplayAbstractModel;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.impl.PlanningDisplayAbstractModelImpl;
import link.ebbinghaus.planning.presenter.PlanningDisplayAbstractPresenter;
import link.ebbinghaus.planning.view.fragment.PlanningDisplayAbstractView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplayAbstractPresenterImpl implements PlanningDisplayAbstractPresenter {
    private PlanningDisplayAbstractView mPlanningDisplayAbstractView;
    private PlanningDisplayAbstractModel mPlanningDisplayAbstractModel;

    public PlanningDisplayAbstractPresenterImpl(PlanningDisplayAbstractView mPlanningDisplayAbstractView) {
        this.mPlanningDisplayAbstractView = mPlanningDisplayAbstractView;
        mPlanningDisplayAbstractModel = new PlanningDisplayAbstractModelImpl();
    }

    @Override
    public void configureRelatedViewPagerTabLayout() {
        List<Tab> tabs = mPlanningDisplayAbstractModel.makePlanningDisplayTabs();
        mPlanningDisplayAbstractView.bindViewPagerToTabLayout(tabs);
    }

}
