package link.ebbinghaus.planning.presenter.planning.display.impl;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDisplayModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.impl.PlanningDisplayModelImpl;
import link.ebbinghaus.planning.presenter.planning.display.PlanningDisplayEventGroupDetailPresenter;
import link.ebbinghaus.planning.view.planning.display.PlanningDisplayEventGroupDetailView;

/**
 * Created by WINFIELD on 2016/3/29.
 */
public class PlanningDisplayEventGroupDetailPresenterImpl implements PlanningDisplayEventGroupDetailPresenter{
    private PlanningDisplayEventGroupDetailView mView;
    private PlanningDisplayModel mPlanningDisplayModel;

    public PlanningDisplayEventGroupDetailPresenterImpl(PlanningDisplayEventGroupDetailView view) {
        this.mView = view;
        mPlanningDisplayModel = new PlanningDisplayModelImpl();
    }


    @Override
    public List<Event> obtainEventGroupDetailData(boolean eventGroupType, EventGroup eventGroup) {
        if (eventGroup == null || eventGroup.getPkEventGroupId() == null)
            return null;
        return mPlanningDisplayModel.findEventGroupDetail(eventGroupType,eventGroup.getPkEventGroupId());
    }

    @Override
    public void configureRecyclerView() {
        mView.getIntentData();
        mView.setRecyclerViewAdapter();
        mView.setRecyclerViewHeader();
    }
}
