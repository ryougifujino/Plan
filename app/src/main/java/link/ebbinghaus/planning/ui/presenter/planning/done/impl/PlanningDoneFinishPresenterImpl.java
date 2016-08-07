package link.ebbinghaus.planning.ui.presenter.planning.done.impl;

import link.ebbinghaus.planning.app.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.core.service.PlanningDoneService;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.model.local.po.LearningEventGroup;
import link.ebbinghaus.planning.core.service.impl.PlanningDoneServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.done.PlanningDoneFinishPresenter;
import link.ebbinghaus.planning.ui.view.planning.done.PlanningDoneFinishView;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public class PlanningDoneFinishPresenterImpl implements PlanningDoneFinishPresenter {
    private PlanningDoneFinishView mView;
    private PlanningDoneService mPlanningDoneService;

    public PlanningDoneFinishPresenterImpl(PlanningDoneFinishView view) {
        this.mView = view;
        this.mPlanningDoneService = new PlanningDoneServiceImpl();
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
        mView.setDefaultLearningEventGroupValues();
        mView.initToolbar();
        mView.setViewByType(type);
        mView.registerListenerByType(type);
        mView.initWidget();
    }

    @Override
    public void buildEfficiencyDialog() {
        mView.setEfficiencyDialog();
    }

    @Override
    public void buildUnderstandingDegreeDialog() {
        mView.setUnderstandingDegreeDialog();
    }

    @Override
    public void finishEvent(Event event, LearningEventGroup learningEventGroup) {
        mView.collectInfo();
        mPlanningDoneService.finishEvent(event,learningEventGroup);
        mView.exitPlanningDoneView();
    }
}
