package link.ebbinghaus.planning.presenter.impl;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDisplayModel;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.impl.PlanningDisplayModelImpl;
import link.ebbinghaus.planning.presenter.PlanningDisplayPresenter;
import link.ebbinghaus.planning.view.fragment.PlanningDisplayView;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class PlanningDisplayPresenterImpl implements PlanningDisplayPresenter {
    private PlanningDisplayView mView;
    private PlanningDisplayModel mPlanningDisplayModel;

    public PlanningDisplayPresenterImpl(PlanningDisplayView planningDisplayView) {
        this.mView = planningDisplayView;
        mPlanningDisplayModel = new PlanningDisplayModelImpl();
    }


    @Override
    public void configureRelatedViewPagerTabLayout() {
        List<Tab> tabs = mPlanningDisplayModel.makePlanningDisplayTabs();
        mView.bindViewPagerToTabLayout(tabs);
    }

    @Override
    public void preprocessToolbarDate() { mView.presetToolbarDate();}

    @Override
    public void configureToolbarDate() {
        mView.setToolbarDate();
    }

}
