package link.ebbinghaus.planning.presenter.impl;

import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.model.PlanningBuildModel;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.entity.vo.planning.build.InputEventVo;
import link.ebbinghaus.planning.model.impl.PlanningBuildModelImpl;
import link.ebbinghaus.planning.presenter.PlanningBuildPresenter;
import link.ebbinghaus.planning.view.activity.PlanningBuildView;

/**
 * Created by WINFIELD on 2016/3/8.
 */
public class PlanningBuildPresenterImpl implements PlanningBuildPresenter {
    private PlanningBuildView mView;
    private PlanningBuildModel mPlanningBuildModel;

    public PlanningBuildPresenterImpl(PlanningBuildView planningBuildView) {
        this.mView = planningBuildView;
        mPlanningBuildModel = new PlanningBuildModelImpl();
    }

    @Override
    public void configureToolbar() {
        mView.setToolbar();
    }

    @Override
    public void configureRelatedViewPagerTabLayout() {
        List<Tab> tabs = mPlanningBuildModel.makePlanningBuildTabs();
        mView.bindViewPagerToTabLayout(tabs);
    }

    /**
     * 辅助方法,向数据库存获取的具体计划数据(前提是数据合法)
     * @return 存入数据库成功返回true,否则false
     */
    private boolean persistSpecEvent(){
        InputEventVo inputEvent = new InputEventVo();
        boolean isValid = mView.obtainSpecificInputEvent(inputEvent);
        if(isValid) {
            switch (inputEvent.getEventType()) {
                case EventConfig.TYPE_SPEC_LEARNING:
                    mPlanningBuildModel.addLearningEvent(inputEvent);
                    break;
                case EventConfig.TYPE_SPEC_NORMAL:
                    mPlanningBuildModel.addNormalEvent(inputEvent);
                    break;
                default: return false;
            }
        }
        return isValid;
    }

    @Override
    public void saveSpecificEvent() {
        if(persistSpecEvent()) {
            mView.resetSpecForm();
        }
    }

    @Override
    public void doneSpecificEvent() {
        if(persistSpecEvent()) {
            mView.exitPlanningBuildActivity();
        }
    }

    /**
     * 辅助方法,向数据库存获取的模糊计划数据(前提是数据合法)
     * @return 存入数据库成功返回true,否则false
     */
    private boolean persistAbstEvent(){
        InputEventVo inputEventVo = new InputEventVo();
        boolean isValid = mView.obtainAbstractEvent(inputEventVo);
        if (isValid){
            mPlanningBuildModel.addAbstractEvent(inputEventVo);
        }
        return isValid;
    }

    @Override
    public void saveAbstractEvent() {
        if (persistAbstEvent()){
            mView.resetAbstForm();
        }
    }

    @Override
    public void doneAbstractEvent() {
        if (persistAbstEvent()){
            mView.exitPlanningBuildActivity();
        }
    }

}
