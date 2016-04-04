package link.ebbinghaus.planning.presenter.planning.done.impl;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.model.PlanningDoneModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.impl.PlanningDoneModelImpl;
import link.ebbinghaus.planning.presenter.planning.done.PlanningDonePresenter;
import link.ebbinghaus.planning.view.planning.done.PlanningDoneView;

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
        mView.initToolbar();
        mView.setRecyclerView(mPlanningDoneModel.findLast2DaysSpecEventsFromToday());
    }

    @Override
    public List<Event> filteredUnfinishedEvents() {
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event : mPlanningDoneModel.findLast2DaysSpecEventsFromToday()) {
            if (!event.getIsEventFinished()){
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }

    @Override
    public List<Event> filteredFinishedEvents() {
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event : mPlanningDoneModel.findLast2DaysSpecEventsFromToday()) {
            if (event.getIsEventFinished()){
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }


}
