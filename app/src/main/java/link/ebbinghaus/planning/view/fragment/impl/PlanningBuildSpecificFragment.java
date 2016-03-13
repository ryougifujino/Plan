package link.ebbinghaus.planning.view.fragment.impl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.yurikami.lib.base.BaseFragment;

import link.ebbinghaus.planning.custom.viewholder.planning.build.SpecificViewHolder;
import link.ebbinghaus.planning.presenter.PlanningBuildSpecificPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningBuildSpecificPresenterImpl;
import link.ebbinghaus.planning.view.fragment.PlanningBuildSpecificView;
import link.ebbinhaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningBuildSpecificFragment extends BaseFragment implements PlanningBuildSpecificView,
        View.OnClickListener,CompoundButton.OnCheckedChangeListener {

    private PlanningBuildSpecificPresenter mPlanningBuildSpecificPresenter;

    private SpecificViewHolder vh;
    private LayoutInflater mLayoutInflater;

    /**
     * 控制输入面板显示的计划时普通型还是学习型
     * false: 普通型 true:学习型
     */
    private boolean mPanelShowEventType = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mLayoutInflater = inflater;
        View v = mLayoutInflater.inflate(R.layout.fragment_planning_build_specific, container, false);
        mPlanningBuildSpecificPresenter = new PlanningBuildSpecificPresenterImpl(this);
        vh = new SpecificViewHolder(v);

        vh.setOnclickListener(this);
        vh.setOnCheckedChangeListener(this);


        return v;
    }

    @Override
    public void setBuildPanel() {
        if(mPanelShowEventType) {
            vh.eventTypeTv.setText(getString(R.string.planning_build_spec_normal_event));
            vh.showNormalPanel();
        }else {
            vh.eventTypeTv.setText(getString(R.string.planning_build_spec_learning_event));
            vh.showLearningPanel();
        }
        mPanelShowEventType = !mPanelShowEventType;
    }

    @Override
    public void setStrategy() {

    }

    @Override
    public void setExpectedFinishDate() {

    }

    @Override
    public void setRemind(boolean isChecked) {
        if(isChecked) {
            vh.showRemindTime();
        }else {
            vh.hideRemindTime();
        }
    }

    @Override
    public void setRemindTime() {

    }

    @Override
    public void setSequence(boolean isChecked) {

    }

    @Override
    public void setGreekAlphabet(boolean isChecked) {

    }

    @Override
    public void setEventGroup() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_planning_build_switch_event_type:
                mPlanningBuildSpecificPresenter.switchBuildPanel();
                break;
            case R.id.tv_planning_build_strategy:
                mPlanningBuildSpecificPresenter.configureStrategy();
                break;
            case R.id.tv_planning_build_expected_finish_date:
                mPlanningBuildSpecificPresenter.configureExpectedFinishDate();
                break;
            case R.id.tv_planning_build_remind_time:
                mPlanningBuildSpecificPresenter.configureRemindTime();
                break;
            case R.id.tv_planning_build_event_group:
                mPlanningBuildSpecificPresenter.configureEventGroup();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_planning_build_is_remind:
                mPlanningBuildSpecificPresenter.configureRemind(isChecked);
                break;
            case R.id.switch_planning_build_is_show_event_sequence:
                mPlanningBuildSpecificPresenter.configureSequence(isChecked);
                break;
            case R.id.switch_planning_build_is_greek_alphabet_marked:
                mPlanningBuildSpecificPresenter.configureGreekAlphabet(isChecked);
                break;
        }
    }
}
