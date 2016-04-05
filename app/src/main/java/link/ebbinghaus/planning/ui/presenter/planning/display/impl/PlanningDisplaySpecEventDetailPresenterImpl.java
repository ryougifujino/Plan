package link.ebbinghaus.planning.ui.presenter.planning.display.impl;

import link.ebbinghaus.planning.core.service.PlanningDisplaySpecificService;
import link.ebbinghaus.planning.core.model.vo.planning.display.SpecEventDetailVo;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplaySpecificServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplaySpecEventDetailPresenter;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplaySpecEventDetailView;

/**
 * Created by WINFIELD on 2016/3/24.
 */
public class PlanningDisplaySpecEventDetailPresenterImpl implements PlanningDisplaySpecEventDetailPresenter{
    private PlanningDisplaySpecEventDetailView mView;
    private PlanningDisplaySpecificService mPlanningDisplaySpecificService;


    public PlanningDisplaySpecEventDetailPresenterImpl(PlanningDisplaySpecEventDetailView view) {
        this.mView = view;
        mPlanningDisplaySpecificService = new PlanningDisplaySpecificServiceImpl();
    }

    @Override
    public void initSpecEventDetail(SpecEventDetailVo specEventDetail) {
        mView.getIntentData();

        //获取其他数据,然后构成View所需的完整数据
        mPlanningDisplaySpecificService.findSpecEventDetailTo(specEventDetail);

        mView.selectViewMode();
        mView.registerViewListener();
        mView.fillViewWithData();
    }
}
