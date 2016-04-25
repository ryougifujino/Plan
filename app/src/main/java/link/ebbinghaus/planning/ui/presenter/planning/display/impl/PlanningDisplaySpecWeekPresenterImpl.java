package link.ebbinghaus.planning.ui.presenter.planning.display.impl;

import com.yurikami.lib.model.Datetime;

import java.util.List;

import link.ebbinghaus.planning.core.service.PlanningDisplaySpecificService;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplaySpecificServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplaySpecWeekPresenter;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplaySpecWeekView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplaySpecWeekPresenterImpl implements PlanningDisplaySpecWeekPresenter {
    private PlanningDisplaySpecWeekView mView;
    private PlanningDisplaySpecificService mPlanningDisplaySpecificService;

    public PlanningDisplaySpecWeekPresenterImpl(PlanningDisplaySpecWeekView view) {
        this.mView = view;
        mPlanningDisplaySpecificService = new PlanningDisplaySpecificServiceImpl();
    }


    @Override
    public void initWeekView() {
        mView.initRecyclerView();
        mView.registerToolbarDateChangeListener();
        mView.setOnCreateViewFlag();
    }

    @Override
    public List<Event> obtainSpecWeekEvents(Datetime datetime) {
        return mPlanningDisplaySpecificService.findSpecWeekEvents(datetime);
    }

}
