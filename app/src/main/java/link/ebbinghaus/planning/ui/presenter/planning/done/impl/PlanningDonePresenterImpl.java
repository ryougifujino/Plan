package link.ebbinghaus.planning.ui.presenter.planning.done.impl;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.service.PlanningDoneService;
import link.ebbinghaus.planning.core.service.impl.PlanningDoneServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.done.PlanningDonePresenter;
import link.ebbinghaus.planning.ui.view.planning.done.PlanningDoneView;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public class PlanningDonePresenterImpl implements PlanningDonePresenter {
    private PlanningDoneView mView;
    private PlanningDoneService mPlanningDoneService;

    public PlanningDonePresenterImpl(PlanningDoneView view) {
        this.mView = view;
        this.mPlanningDoneService = new PlanningDoneServiceImpl();
    }

    @Override
    public List<Event> obtainDoneModuleEvents() {
        return mPlanningDoneService.findLast2DaysSpecEventsFromToday();
    }

    @Override
    public void initPlanningDoneView() {
        mView.initToolbar();
        mView.setRecyclerView(mPlanningDoneService.findLast2DaysSpecEventsFromToday());
    }

    @Override
    public List<Event> filteredUnfinishedEvents() {
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event : mPlanningDoneService.findLast2DaysSpecEventsFromToday()) {
            if (!event.getIsEventFinished()){
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }

    @Override
    public List<Event> filteredFinishedEvents() {
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event : mPlanningDoneService.findLast2DaysSpecEventsFromToday()) {
            if (event.getIsEventFinished()){
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }


}
