package link.ebbinghaus.planning.presenter.planning.display.impl;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDisplayModel;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.impl.PlanningDisplayModelImpl;
import link.ebbinghaus.planning.presenter.planning.display.PlanningDisplayPresenter;
import link.ebbinghaus.planning.view.planning.display.PlanningDisplayView;

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
