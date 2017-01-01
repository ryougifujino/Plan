package link.ebbinghaus.planning.ui.viewholder.planning.build;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.viewholder.BaseFragmentViewHolder;

/**
 * Created by WINFIELD on 2016/3/13.
 */
public class SpecificViewHolder extends BaseFragmentViewHolder {
    public EditText descriptionEt;
    public ImageView templateIv;
    public TextView eventTypeNormalTv;
    public TextView eventTypeLearningTv;

    public TextView eventSubtypeTv;
    public LinearLayout eventSubtypeLl;
    public TextView strategyTv;
    public LinearLayout strategyLl;
    public TextView expectedFinishDateTv;
    public LinearLayout expectedFinishDateLl;
    public Switch remindSwitch;
    public RelativeLayout remindRl;
    public TextView remindTimeTv;
    public LinearLayout remindTimeLl;
    public Switch sequenceSwitch;
    public RelativeLayout sequenceRl;
    public Switch greekAlphabetSwitch;
    public TextView eventGroupTv;
    public LinearLayout eventGroupLl;

    public SpecificViewHolder(View v) {
        super(v);
        descriptionEt = find(R.id.et_planning_build_description);
        templateIv = find(R.id.iv_planning_build_template);
        eventTypeNormalTv = find(R.id.tv_planning_build_event_type_normal);
        eventTypeLearningTv = find(R.id.tv_planning_build_event_type_learning);

        eventSubtypeTv = find(R.id.tv_planning_build_subtype);
        strategyTv = find(R.id.tv_planning_build_strategy);
        expectedFinishDateTv = find(R.id.tv_planning_build_expected_finish_date);
        remindSwitch = find(R.id.switch_planning_build_is_remind);
        remindTimeTv = find(R.id.tv_planning_build_remind_time);
        sequenceSwitch = find(R.id.switch_planning_build_is_show_event_sequence);
        greekAlphabetSwitch = find(R.id.switch_planning_build_is_greek_alphabet_marked);
        eventGroupTv = find(R.id.tv_planning_build_event_group);

        eventSubtypeLl = find(R.id.ll_planning_build_subtype);
        strategyLl = find(R.id.ll_planning_build_strategy);
        expectedFinishDateLl = find(R.id.ll_planning_build_expected_finish_date);
        remindRl = find(R.id.ll_planning_build_is_remind);
        remindTimeLl = find(R.id.ll_planning_build_remind_time);
        sequenceRl = find(R.id.ll_planning_build_is_show_event_sequence);
        eventGroupLl = find(R.id.ll_planning_build_event_group);

    }

    public void setOnclickListener(View.OnClickListener l){
        eventTypeNormalTv.setOnClickListener(l);
        eventTypeLearningTv.setOnClickListener(l);
        templateIv.setOnClickListener(l);
        
        eventSubtypeLl.setOnClickListener(l);
        strategyLl.setOnClickListener(l);
        expectedFinishDateLl.setOnClickListener(l);
        remindRl.setOnClickListener(l);
        remindTimeLl.setOnClickListener(l);
        sequenceRl.setOnClickListener(l);
        eventGroupLl.setOnClickListener(l);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener l){
        remindSwitch.setOnCheckedChangeListener(l);
        sequenceSwitch.setOnCheckedChangeListener(l);
        greekAlphabetSwitch.setOnCheckedChangeListener(l);
    }
    
    private void controlPanel(int visibility){
        strategyLl.setVisibility(visibility);
        sequenceRl.setVisibility(visibility);
    }
    public void showLearningPanel(){ controlPanel(View.VISIBLE); }
    public void showNormalPanel(){
        controlPanel(View.GONE);
    }

    private void controlRemindTime(int visibility){
        remindTimeLl.setVisibility(visibility);
    }
    public void showRemindTime(){
        controlRemindTime(View.VISIBLE);
    }
    public void hideRemindTime(){
        controlRemindTime(View.GONE);
    }
}
