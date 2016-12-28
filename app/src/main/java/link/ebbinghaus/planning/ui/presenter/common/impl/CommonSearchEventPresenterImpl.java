package link.ebbinghaus.planning.ui.presenter.common.impl;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.service.PlanningDisplayAbstractService;
import link.ebbinghaus.planning.core.service.PlanningDisplaySpecificService;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplayAbstractServiceImpl;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplaySpecificServiceImpl;
import link.ebbinghaus.planning.ui.presenter.common.CommonSearchEventPresenter;
import link.ebbinghaus.planning.ui.view.common.CommonSearchEventView;

/**
 * Created by WINFIELD on 2016/10/18.
 */

public class CommonSearchEventPresenterImpl implements CommonSearchEventPresenter {

    private CommonSearchEventView mView;
    private PlanningDisplaySpecificService mPlanningDisplaySpecificService;
    private PlanningDisplayAbstractService mPlanningDisplayAbstractService;

    public CommonSearchEventPresenterImpl(CommonSearchEventView view) {
        this.mView = view;
        mPlanningDisplaySpecificService = new PlanningDisplaySpecificServiceImpl();
        mPlanningDisplayAbstractService = new PlanningDisplayAbstractServiceImpl();
    }


    @Override
    public List<Event> searchEvents(String key, boolean specific) {
        return specific ?
                mPlanningDisplaySpecificService.findSpecEventsByDescription(key) :
                mPlanningDisplayAbstractService.findAbstEventsByDescription(key);
    }
}
