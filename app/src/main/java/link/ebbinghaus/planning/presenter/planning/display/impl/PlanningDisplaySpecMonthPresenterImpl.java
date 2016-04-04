package link.ebbinghaus.planning.presenter.planning.display.impl;

import link.ebbinghaus.planning.model.PlanningDisplaySpecificModel;
import link.ebbinghaus.planning.model.impl.PlanningDisplaySpecificModelImpl;
import link.ebbinghaus.planning.presenter.planning.display.PlanningDisplaySpecMonthPresenter;
import link.ebbinghaus.planning.view.planning.display.PlanningDisplaySpecMonthView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplaySpecMonthPresenterImpl implements PlanningDisplaySpecMonthPresenter {
    private PlanningDisplaySpecMonthView mView;
    private PlanningDisplaySpecificModel mPlanningDisplaySpecificModel;

    public PlanningDisplaySpecMonthPresenterImpl(PlanningDisplaySpecMonthView view) {
        this.mView = view;
        mPlanningDisplaySpecificModel = new PlanningDisplaySpecificModelImpl();
    }

    @Override
    public void initMonthView() {
        mView.initRecyclerView();
        mView.registerToolbarDateChangeListener();
        mView.setOnCreateViewFlag();
    }

}
