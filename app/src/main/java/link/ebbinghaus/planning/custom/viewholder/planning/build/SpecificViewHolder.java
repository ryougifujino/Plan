package link.ebbinghaus.planning.custom.viewholder.planning.build;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import link.ebbinghaus.planning.custom.viewholder.BaseFragmentViewHolder;
import link.ebbinhaus.planning.R;

/**
 * Created by WINFIELD on 2016/3/13.
 */
public class SpecificViewHolder extends BaseFragmentViewHolder{
    public TextView eventTypeTv;
    public TextView eventSubtypeTv;
    public Button eventTypeBtn;
    public EditText descriptionEt;
    public Button fastTemplateBtn;
    public TextView strategyLabel;
    public TextView strategyTv;
    public TextView expectedFinishDateTv;
    public Switch remindSwitch;
    public TextView remindTimeLabel;
    public TextView remindTimeTv;
    public TextView sequenceLabel;
    public Switch sequenceSwitch;
    public Switch greekAlphabetSwitch;
    public TextView eventGroupTv;

    public SpecificViewHolder(View v) {
        super(v);
        eventTypeTv = find(R.id.tv_planning_build_event_type);
        eventSubtypeTv = find(R.id.tv_planning_build_subtype);
        eventTypeBtn = find(R.id.btn_planning_build_switch_event_type);
        descriptionEt = find(R.id.et_planning_build_description);
        fastTemplateBtn = find(R.id.btn_planning_build_fast_template);
        strategyLabel = find(R.id.label_planning_build_strategy);
        strategyTv = find(R.id.tv_planning_build_strategy);
        expectedFinishDateTv = find(R.id.tv_planning_build_expected_finish_date);
        remindSwitch = find(R.id.switch_planning_build_is_remind);
        remindTimeLabel = find(R.id.label_planning_build_remind_time);
        remindTimeTv = find(R.id.tv_planning_build_remind_time);
        sequenceLabel = find(R.id.label_planning_build_is_show_event_sequence);
        sequenceSwitch = find(R.id.switch_planning_build_is_show_event_sequence);
        greekAlphabetSwitch = find(R.id.switch_planning_build_is_greek_alphabet_marked);
        eventGroupTv = find(R.id.tv_planning_build_event_group);
    }

    public void setOnclickListener(View.OnClickListener l){
        eventTypeBtn.setOnClickListener(l);
        eventSubtypeTv.setOnClickListener(l);
        fastTemplateBtn.setOnClickListener(l);
        strategyTv.setOnClickListener(l);
        expectedFinishDateTv.setOnClickListener(l);
        remindTimeTv.setOnClickListener(l);
        eventGroupTv.setOnClickListener(l);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener l){
        remindSwitch.setOnCheckedChangeListener(l);
        sequenceSwitch.setOnCheckedChangeListener(l);
        greekAlphabetSwitch.setOnCheckedChangeListener(l);
    }
    
    private void controlPanel(int visibility){
        strategyLabel.setVisibility(visibility);
        strategyTv.setVisibility(visibility);
        sequenceLabel.setVisibility(visibility);
        sequenceSwitch.setVisibility(visibility);
    }
    public void showLearningPanel(){ controlPanel(View.VISIBLE); }
    public void showNormalPanel(){
        controlPanel(View.GONE);
    }

    private void controlRemindTime(int visibility){
        remindTimeLabel.setVisibility(visibility);
        remindTimeTv.setVisibility(visibility);
    }
    public void showRemindTime(){
        controlRemindTime(View.VISIBLE);
    }
    public void hideRemindTime(){
        controlRemindTime(View.GONE);
    }
}
