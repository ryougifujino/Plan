package link.ebbinghaus.planning.ui.presenter.planning.display.impl;

import java.util.List;

import link.ebbinghaus.planning.core.service.PlanningDisplayService;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.model.local.po.EventGroup;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplayServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplayEventGroupDetailPresenter;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplayEventGroupDetailView;

/**
 * Created by WINFIELD on 2016/3/29.
 */
public class PlanningDisplayEventGroupDetailPresenterImpl implements PlanningDisplayEventGroupDetailPresenter {
    private PlanningDisplayEventGroupDetailView mView;
    private PlanningDisplayService mPlanningDisplayService;

    public PlanningDisplayEventGroupDetailPresenterImpl(PlanningDisplayEventGroupDetailView view) {
        this.mView = view;
        mPlanningDisplayService = new PlanningDisplayServiceImpl();
    }


    @Override
    public List<Event> obtainEventGroupDetailData(boolean eventGroupType, EventGroup eventGroup) {
        if (eventGroup == null || eventGroup.getPkEventGroupId() == null)
            return null;
        return mPlanningDisplayService.findEventGroupDetail(eventGroupType,eventGroup.getPkEventGroupId());
    }

    @Override
    public EventGroup obtainEventGroup(long pk) {
        return mPlanningDisplayService.findEventGroup(pk);
    }

    @Override
    public void configureRecyclerView() {
        mView.getIntentData();
        mView.setRecyclerViewAdapter();
        mView.setRecyclerViewHeader();
    }
}
