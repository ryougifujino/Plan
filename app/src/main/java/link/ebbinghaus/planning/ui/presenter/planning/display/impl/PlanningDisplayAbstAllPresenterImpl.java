package link.ebbinghaus.planning.ui.presenter.planning.display.impl;

import java.util.List;

import link.ebbinghaus.planning.core.service.PlanningDisplayAbstractService;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplayAbstractServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplayAbstAllPresenter;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplayAbstAllView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplayAbstAllPresenterImpl implements PlanningDisplayAbstAllPresenter {
    private PlanningDisplayAbstAllView mView;
    private PlanningDisplayAbstractService mPlanningDisplayAbstractService;

    public PlanningDisplayAbstAllPresenterImpl(PlanningDisplayAbstAllView view) {
        this.mView = view;
        mPlanningDisplayAbstractService = new PlanningDisplayAbstractServiceImpl();
    }

    @Override
    public void initAbstAllView() {
        mView.initRecyclerView();
        mView.setOnCreateViewFlag();
    }

    @Override
    public List<Event> obtainAllAbstractEvents() {
        return mPlanningDisplayAbstractService.findAllAbstEvent();
    }


}
