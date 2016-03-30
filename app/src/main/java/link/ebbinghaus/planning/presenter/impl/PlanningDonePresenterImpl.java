package link.ebbinghaus.planning.presenter.impl;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDoneModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.impl.PlanningDoneModelImpl;
import link.ebbinghaus.planning.presenter.PlanningDonePresenter;
import link.ebbinghaus.planning.view.fragment.PlanningDoneView;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public class PlanningDonePresenterImpl implements PlanningDonePresenter {
    private PlanningDoneView mView;
    private PlanningDoneModel mPlanningDoneModel;

    public PlanningDonePresenterImpl(PlanningDoneView view) {
        this.mView = view;
        this.mPlanningDoneModel = new PlanningDoneModelImpl();
    }

    @Override
    public List<Event> obtainDoneModuleEvents() {
        return mPlanningDoneModel.findLast2DaysSpecEventsFromToday();
    }

    @Override
    public void initPlanningDoneView() {
        mView.setRecyclerView(mPlanningDoneModel.findLast2DaysSpecEventsFromToday());
    }
}
