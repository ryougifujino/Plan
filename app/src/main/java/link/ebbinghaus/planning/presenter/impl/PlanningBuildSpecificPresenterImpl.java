package link.ebbinghaus.planning.presenter.impl;

import link.ebbinghaus.planning.model.PlanningBuildModel;
import link.ebbinghaus.planning.model.impl.PlanningBuildModelImpl;
import link.ebbinghaus.planning.presenter.PlanningBuildSpecificPresenter;
import link.ebbinghaus.planning.view.fragment.PlanningBuildSpecificView;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class PlanningBuildSpecificPresenterImpl implements PlanningBuildSpecificPresenter {
    private PlanningBuildSpecificView mPlanningBuildSpecificView;
    private PlanningBuildModel mPlanningBuildModel;

    public PlanningBuildSpecificPresenterImpl(PlanningBuildSpecificView planningBuildSpecificView) {
        this.mPlanningBuildSpecificView = planningBuildSpecificView;
        this.mPlanningBuildModel = new PlanningBuildModelImpl();
    }

    @Override
    public void switchBuildPanel() {
        mPlanningBuildSpecificView.setBuildPanel();
    }

    @Override
    public void configureSubtype() {

    }

    @Override
    public void configureStrategy() {

    }

    @Override
    public void configureExpectedFinishDate() {

    }

    @Override
    public void configureRemind(boolean isChecked) {
        mPlanningBuildSpecificView.setRemind(isChecked);
    }

    @Override
    public void configureRemindTime() {

    }

    @Override
    public void configureSequence(boolean isChecked) {

    }

    @Override
    public void configureGreekAlphabet(boolean isChecked) {

    }

    @Override
    public void configureEventGroup() {

    }
}
