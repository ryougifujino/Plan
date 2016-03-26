package link.ebbinghaus.planning.presenter.impl;

import link.ebbinghaus.planning.presenter.PlanningBuildAbstractPresenter;
import link.ebbinghaus.planning.view.fragment.PlanningBuildAbstractView;

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
}
