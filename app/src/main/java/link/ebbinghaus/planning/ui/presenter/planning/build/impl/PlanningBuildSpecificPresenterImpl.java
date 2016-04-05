package link.ebbinghaus.planning.ui.presenter.planning.build.impl;

import com.yurikami.lib.util.DateUtils;

import link.ebbinghaus.planning.core.service.PlanningBuildService;
import link.ebbinghaus.planning.core.model.po.DefaultInputValue;
import link.ebbinghaus.planning.core.model.po.EventGroup;
import link.ebbinghaus.planning.core.model.po.EventSubtype;
import link.ebbinghaus.planning.core.model.vo.planning.build.InputEventVo;
import link.ebbinghaus.planning.core.service.impl.PlanningBuildServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.build.PlanningBuildSpecificPresenter;
import link.ebbinghaus.planning.ui.view.planning.build.PlanningBuildSpecificView;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class PlanningBuildSpecificPresenterImpl implements PlanningBuildSpecificPresenter {
    private PlanningBuildSpecificView mView;
    private PlanningBuildService mPlanningBuildService;

    public PlanningBuildSpecificPresenterImpl(PlanningBuildSpecificView planningBuildSpecificView) {
        this.mView = planningBuildSpecificView;
        this.mPlanningBuildService = new PlanningBuildServiceImpl();
    }

    @Override
    public void registerListeners() {
        mView.setListeners();
    }

    @Override
    public void getAndSetDefaultInputValues(InputEventVo inputEvent) {
        DefaultInputValue value = mPlanningBuildService.findDefaultInputValue();
        mView.setDefaultInputValue(value);
        inputEvent.setMaxWidth(value.getMaxWidth());
        inputEvent.setIsGreekAlphabetMarked(value.getIsGreekAlphabetMarked());
        inputEvent.setIsRemind(value.getIsRemind());
        inputEvent.setRemindTime(value.getRemindTime());
        inputEvent.setStrategy(value.getStrategy());
        inputEvent.setIsShowEventSequence(value.getIsShowEventSequence());
        //默认值,不在默认值数据表里,手动设置的
        inputEvent.setEventExpectedFinishedDate(DateUtils.currentDateTimestamp());
    }

    @Override
    public void switchBuildPanel() {
        mView.setBuildPanel();
    }

    @Override
    public void configureEventSubtype(EventSubtype result) {
        mView.setSubtype(result);
    }

    @Override
    public void configureDescription(String template) {
        if(template != null) {
            mView.setFastTemplate(template);
        }
    }

    @Override
    public void configureStrategy() {
        mView.selectStrategy();
    }

    @Override
    public void configureExpectedFinishDate() {
        mView.setExpectedFinishDate();
    }

    @Override
    public void configureRemind(boolean isChecked) {
        mView.setRemind(isChecked);
    }

    @Override
    public void configureRemindTime() {
        mView.setRemindTime();
    }

    @Override
    public void configureSequence(boolean isChecked) {
        mView.setSequence(isChecked);
    }

    @Override
    public void configureGreekAlphabet(boolean isChecked) {
        mView.setGreekAlphabet(isChecked);
    }

    @Override
    public void configureEventGroup(EventGroup eventGroup) {
        mView.setEventGroup(eventGroup);
    }

}
