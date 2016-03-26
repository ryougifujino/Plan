package link.ebbinghaus.planning.presenter.impl;

import link.ebbinghaus.planning.presenter.PlanningDisplayEventGroupPresenter;
import link.ebbinghaus.planning.view.fragment.PlanningDisplayEventGroupView;

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
