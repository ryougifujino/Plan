package link.ebbinghaus.planning.presenter.planning.display.impl;

import link.ebbinghaus.planning.presenter.planning.display.PlanningDisplayEventGroupPresenter;
import link.ebbinghaus.planning.view.planning.display.PlanningDisplayEventGroupView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplayEventGroupPresenterImpl implements PlanningDisplayEventGroupPresenter {
    private PlanningDisplayEventGroupView mView;

    public PlanningDisplayEventGroupPresenterImpl(PlanningDisplayEventGroupView view) {
        this.mView = view;
    }

    @Override
    public void initEventGroupView() {
        mView.initRecyclerView();
    }
}
