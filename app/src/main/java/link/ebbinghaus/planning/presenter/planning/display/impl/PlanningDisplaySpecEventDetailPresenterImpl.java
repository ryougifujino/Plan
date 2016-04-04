package link.ebbinghaus.planning.presenter.planning.display.impl;

import link.ebbinghaus.planning.model.PlanningDisplaySpecificModel;
import link.ebbinghaus.planning.model.entity.vo.planning.display.SpecEventDetailVo;
import link.ebbinghaus.planning.model.impl.PlanningDisplaySpecificModelImpl;
import link.ebbinghaus.planning.presenter.planning.display.PlanningDisplaySpecEventDetailPresenter;
import link.ebbinghaus.planning.view.planning.display.PlanningDisplaySpecEventDetailView;

/**
 * Created by WINFIELD on 2016/3/24.
 */
public class PlanningDisplaySpecEventDetailPresenterImpl implements PlanningDisplaySpecEventDetailPresenter{
    private PlanningDisplaySpecEventDetailView mView;
    private PlanningDisplaySpecificModel mPlanningDisplaySpecificModel;


    public PlanningDisplaySpecEventDetailPresenterImpl(PlanningDisplaySpecEventDetailView view) {
        this.mView = view;
        mPlanningDisplaySpecificModel = new PlanningDisplaySpecificModelImpl();
    }

    @Override
    public void initSpecEventDetail(SpecEventDetailVo specEventDetail) {
        mView.getIntentData();

        //获取其他数据,然后构成View所需的完整数据
        mPlanningDisplaySpecificModel.findSpecEventDetailTo(specEventDetail);

        mView.selectViewMode();
        mView.registerViewListener();
        mView.fillViewWithData();
    }
}
