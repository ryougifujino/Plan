package link.ebbinghaus.planning.ui.presenter.planning.display.impl;

import link.ebbinghaus.planning.core.service.PlanningDisplaySpecificService;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplaySpecificServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplaySpecMonthPresenter;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplaySpecMonthView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplaySpecMonthPresenterImpl implements PlanningDisplaySpecMonthPresenter {
    private PlanningDisplaySpecMonthView mView;
    private PlanningDisplaySpecificService mPlanningDisplaySpecificService;

    public PlanningDisplaySpecMonthPresenterImpl(PlanningDisplaySpecMonthView view) {
        this.mView = view;
        mPlanningDisplaySpecificService = new PlanningDisplaySpecificServiceImpl();
    }

    @Override
    public void initMonthView() {
        mView.initRecyclerView();
        mView.registerToolbarDateChangeListener();
        mView.setOnCreateViewFlag();
    }

}
