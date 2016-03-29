package link.ebbinghaus.planning.presenter.impl;

import java.util.List;

import link.ebbinghaus.planning.model.PlanningDisplayAbstractModel;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.impl.PlanningDisplayAbstractModelImpl;
import link.ebbinghaus.planning.presenter.PlanningDisplayAbstAllPresenter;
import link.ebbinghaus.planning.view.fragment.PlanningDisplayAbstAllView;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplayAbstAllPresenterImpl implements PlanningDisplayAbstAllPresenter {
    private PlanningDisplayAbstAllView mView;
    private PlanningDisplayAbstractModel mPlanningDisplayAbstractModel;

    public PlanningDisplayAbstAllPresenterImpl(PlanningDisplayAbstAllView view) {
        this.mView = view;
        mPlanningDisplayAbstractModel = new PlanningDisplayAbstractModelImpl();
    }

    @Override
    public void initAbstAllView() {
        mView.initRecyclerView();
        mView.setOnCreateViewFlag();
    }

    @Override
    public List<Event> obtainAllAbstractEvents() {
        return mPlanningDisplayAbstractModel.findAllAbstEvent();
    }


}
