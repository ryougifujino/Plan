package link.ebbinghaus.planning.presenter.impl;

import com.yurikami.lib.entity.Datetime;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDisplaySpecificModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.impl.PlanningDisplaySpecificModelImpl;
import link.ebbinghaus.planning.presenter.PlanningDisplaySpecWeekPresenter;
import link.ebbinghaus.planning.view.fragment.PlanningDisplaySpecWeekView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplaySpecWeekPresenterImpl implements PlanningDisplaySpecWeekPresenter {
    private PlanningDisplaySpecWeekView mView;
    private PlanningDisplaySpecificModel mPlanningDisplaySpecificModel;

    public PlanningDisplaySpecWeekPresenterImpl(PlanningDisplaySpecWeekView view) {
        this.mView = view;
        mPlanningDisplaySpecificModel = new PlanningDisplaySpecificModelImpl();
    }

    @Override
    public List<Event> obtainSpecWeekEvents(Datetime datetime) {
        return null;
    }

    @Override
    public void renderWeekView() {

    }

    @Override
    public void refreshWeekView() {

    }

}
