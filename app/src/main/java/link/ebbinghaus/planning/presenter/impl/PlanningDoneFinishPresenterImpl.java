package link.ebbinghaus.planning.presenter.impl;

import link.ebbinghaus.planning.custom.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.model.PlanningDoneModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.impl.PlanningDoneModelImpl;
import link.ebbinghaus.planning.presenter.PlanningDoneFinishPresenter;
import link.ebbinghaus.planning.view.activity.PlanningDoneFinishView;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public class PlanningDoneFinishPresenterImpl implements PlanningDoneFinishPresenter {
    private PlanningDoneFinishView mView;
    private PlanningDoneModel mPlanningDoneModel;

    public PlanningDoneFinishPresenterImpl(PlanningDoneFinishView view) {
        this.mView = view;
        this.mPlanningDoneModel = new PlanningDoneModelImpl();
    }

    @Override
    public void initPlanningDoneFinish() {
        Event event = mView.getIntentData();
        boolean type;
        if (event.getEventType() == EventConfig.TYPE_SPEC_LEARNING && event.getEventSequence() == 1){
            type = true;
        }else{
            type = false;
        }
        mView.setViewByType(type);
        mView.registerListenerByType(type);
        mView.initWidget();
    }
}
