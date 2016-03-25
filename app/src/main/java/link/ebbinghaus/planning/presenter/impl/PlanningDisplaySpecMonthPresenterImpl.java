package link.ebbinghaus.planning.presenter.impl;

import com.yurikami.lib.entity.Datetime;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDisplaySpecificModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.impl.PlanningDisplaySpecificModelImpl;
import link.ebbinghaus.planning.presenter.PlanningDisplaySpecMonthPresenter;
import link.ebbinghaus.planning.view.fragment.PlanningDisplaySpecMonthView;

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
    public void initMonthView(Datetime datetime) {
        List<Event> events = mPlanningDisplaySpecificModel.findSpecEvents(datetime);
        mView.setRecyclerView(events);
    }

    @Override
    public void renderMonthView(Datetime datetime) {
        List<Event> events = mPlanningDisplaySpecificModel.findSpecEvents(datetime);
        mView.refreshRecyclerView(datetime,events);
    }

}
