package link.ebbinghaus.planning.ui.presenter.planning.build.impl;

import link.ebbinghaus.planning.core.model.po.EventGroup;
import link.ebbinghaus.planning.ui.presenter.planning.build.PlanningBuildAbstractPresenter;
import link.ebbinghaus.planning.ui.view.planning.build.PlanningBuildAbstractView;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class PlanningBuildAbstractPresenterImpl implements PlanningBuildAbstractPresenter {

    private PlanningBuildAbstractView mView;

    public PlanningBuildAbstractPresenterImpl(PlanningBuildAbstractView view) {
        this.mView = view;
    }

    @Override
    public void registerListeners() {
        mView.setListeners();
    }

    @Override
    public void configureDescription(String template) {
        if (template != null) {
            mView.setFastTemplate(template);
        }
    }

    @Override
    public void configureEventGroup(EventGroup eventGroup) {
        mView.setEventGroup(eventGroup);
    }

    @Override
    public void setDefaultValues() {
        mView.setDefaultEventGroupText();
    }
}
