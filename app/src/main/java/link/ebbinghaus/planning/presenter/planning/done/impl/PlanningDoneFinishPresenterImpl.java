package link.ebbinghaus.planning.presenter.planning.done.impl;

import link.ebbinghaus.planning.ebbinghaus.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.model.PlanningDoneModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;
import link.ebbinghaus.planning.model.impl.PlanningDoneModelImpl;
import link.ebbinghaus.planning.presenter.planning.done.PlanningDoneFinishPresenter;
import link.ebbinghaus.planning.view.planning.done.PlanningDoneFinishView;

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
        mPlanningDoneModel.finishEvent(event,learningEventGroup);
        mView.exitPlanningDoneView();
    }
}
