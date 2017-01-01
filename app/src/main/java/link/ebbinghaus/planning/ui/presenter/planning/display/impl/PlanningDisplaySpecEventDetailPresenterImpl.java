package link.ebbinghaus.planning.ui.presenter.planning.display.impl;

import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.service.PlanningDisplaySpecificService;
import link.ebbinghaus.planning.core.model.local.vo.planning.display.SpecEventDetailVo;
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

    @Override
    public void refreshSpecEventDetail(SpecEventDetailVo specEventDetail) {
        //根据id抓取最新的数据
        mPlanningDisplaySpecificService.findSpecEventDetailTo(specEventDetail);
        //重新设置数据某些控件样式
        mView.fillViewWithData();
    }

    @Override
    public void deleteThisEventAndProcessRelated(Event event) {
        mPlanningDisplaySpecificService.removeSpecEventAndProcessRelated(event);
        mView.exitThisView();
    }

    @Override
    public void updateIsShowEventSequence(Event event) {
        mPlanningDisplaySpecificService.updateEvent(event);
    }
}
