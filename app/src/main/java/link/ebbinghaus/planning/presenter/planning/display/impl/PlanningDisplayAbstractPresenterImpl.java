package link.ebbinghaus.planning.presenter.planning.display.impl;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDisplayAbstractModel;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.impl.PlanningDisplayAbstractModelImpl;
import link.ebbinghaus.planning.presenter.planning.display.PlanningDisplayAbstractPresenter;
import link.ebbinghaus.planning.view.planning.display.PlanningDisplayAbstractView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplayAbstractPresenterImpl implements PlanningDisplayAbstractPresenter {
    private PlanningDisplayAbstractView mView;
    private PlanningDisplayAbstractModel mPlanningDisplayAbstractModel;

    public PlanningDisplayAbstractPresenterImpl(PlanningDisplayAbstractView planningDisplayAbstractView) {
        this.mView = planningDisplayAbstractView;
        mPlanningDisplayAbstractModel = new PlanningDisplayAbstractModelImpl();
    }

    @Override
    public void configureRelatedViewPagerTabLayout() {
        List<Tab> tabs = mPlanningDisplayAbstractModel.makePlanningDisplayAbstractTabs();
        mView.bindViewPagerToTabLayout(tabs);
    }


}
