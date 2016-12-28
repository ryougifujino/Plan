package link.ebbinghaus.planning.ui.viewholder.planning.display;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.Utils;

import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.App;
import link.ebbinghaus.planning.app.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.app.constant.model.EventConstant;
import link.ebbinghaus.planning.app.constant.model.LearningEventGroupConstant;
import link.ebbinghaus.planning.core.model.local.vo.planning.display.SpecEventDetailVo;
import link.ebbinghaus.planning.ui.viewholder.BaseActivityViewHolder;

/**
 * Created by WINFIELD on 2016/3/28.
 */
public class SpecEventDetailViewHolder extends BaseActivityViewHolder {

    //top
    public TextView descriptionTv;

    //top bar
    public ImageView topEventTypeIv;
    public TextView topProcessTv;
    public TextView topCountdownTv;
    public TextView topSequenceTv;
    public Button topToFinishBtn;

    //list
    public TextView eventSubtypeTv;
    public TextView eventGroupTv;
    public Switch showSequenceSwitch;
    public TextView strategyTv;
    public TextView createTimeTv;
    public TextView expectedFinishDateTv;
    public TextView finishedTimeTv;
    public TextView remindTimeTv;
    public TextView progressTv;
    public TextView knowledgeQuantityTv;
    public TextView workloadTv;
    public TextView summaryTv;

    public RelativeLayout subtypeRl;
    public RelativeLayout eventGroupRl;
    public RelativeLayout showSequenceRl;
    public RelativeLayout strategyRl;
    public RelativeLayout expectedFinishedDateRl;
    public RelativeLayout remindTimeRl;
    public RelativeLayout progressRl;
    public RelativeLayout quantityRl;
    public RelativeLayout summaryRl;

    //widget
    private CountDownTimer countDownTimer;

    public SpecEventDetailViewHolder(Activity activity) {
        super(activity);

        //1.top
        descriptionTv = find(R.id.tv_planning_display_event_detail_description);

        //2.top bar
        topEventTypeIv = find(R.id.iv_planning_display_event_detail_top_event_type);
        topProcessTv = find(R.id.tv_planning_display_event_detail_top_process);
        topCountdownTv = find(R.id.tv_planning_display_event_detail_top_countdown);
        topSequenceTv = find(R.id.tv_planning_display_event_detail_top_sequence);
        topToFinishBtn = find(R.id.btn_tv_planning_display_event_detail_top_to_finish);

        //3.list
        eventSubtypeTv = find(R.id.tv_planning_display_event_detail_event_subtype);
        eventGroupTv = find(R.id.tv_planning_display_event_detail_event_group);
        showSequenceSwitch = find(R.id.switch_planning_display_event_detail_is_show_sequence);
        strategyTv = find(R.id.tv_planning_display_event_detail_strategy);
        createTimeTv = find(R.id.tv_planning_display_event_detail_create_time);
        expectedFinishDateTv = find(R.id.tv_planning_display_event_detail_expected_date);
        finishedTimeTv = find(R.id.tv_planning_display_event_detail_finished_time);
        remindTimeTv = find(R.id.tv_planning_display_event_detail_remind_time);
        progressTv = find(R.id.tv_planning_display_event_detail_progress);
        knowledgeQuantityTv = find(R.id.tv_planning_display_event_detail_knowledge_quantity);
        workloadTv = find(R.id.tv_planning_display_event_detail_workload);
        summaryTv = find(R.id.tv_planning_display_event_detail_summary);

        subtypeRl = find(R.id.rl_planning_display_spec_event_detail_subtype);
        eventGroupRl = find(R.id.rl_planning_display_spec_event_detail_event_group);
        showSequenceRl = find(R.id.rl_planning_display_spec_event_detail_show_sequence);
        strategyRl = find(R.id.rl_planning_display_spec_event_detail_strategy);
        expectedFinishedDateRl = find(R.id.rl_planning_display_event_detail_expected_date);
        remindTimeRl = find(R.id.rl_planning_display_spec_event_detail_remind_time);
        progressRl = find(R.id.rl_planning_display_spec_event_detail_progress);
        quantityRl = find(R.id.rl_planning_display_spec_event_detail_quantity);
        summaryRl = find(R.id.rl_planning_display_spec_event_detail_summary);

        //custom view
        //for compatibility
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            topToFinishBtn.setTextColor(Color.BLACK);
        }

    }

    /**
     * 显示学习计划模式(即全部控件,默认显示全部控件,所以不用调用此方法,因此为空方法)
     */
    public void learningMode(){
    }

    /**
     * 显示普通计划模式(将会屏蔽掉一些控件)
     */
    public void normalMode(){

        topSequenceTv.setVisibility(View.GONE);
        showSequenceRl.setVisibility(View.GONE);
        strategyRl.setVisibility(View.GONE);
        progressRl.setVisibility(View.GONE);
        quantityRl.setVisibility(View.GONE);

    }

    /** 批量注册点击事件 */
    public void setOnClickListeners(View.OnClickListener l) {
        topToFinishBtn.setOnClickListener(l);
        subtypeRl.setOnClickListener(l);
        eventGroupRl.setOnClickListener(l);
        showSequenceRl.setOnClickListener(l);
        strategyRl.setOnClickListener(l);
        expectedFinishedDateRl.setOnClickListener(l);
        remindTimeRl.setOnClickListener(l);
        summaryRl.setOnClickListener(l);
    }

    /** 根据数据来填充控件并控制控件的样式 */
    public void setDataAndConfigViews(SpecEventDetailVo vo){
        boolean isFinishable = vo.event.isFinishable();

        //1.top
        descriptionTv.setText(vo.event.getDescription() != null ? vo.event.getDescription() : getString(R.string.common_none));

        //2.top bar
        topEventTypeIv.setImageResource(vo.event.getEventType() == EventConfig.TYPE_SPEC_LEARNING ? R.drawable.planning_display_spec_event_detail_top_learning : R.drawable.planning_display_spec_event_detail_top_normal);

        topCountdownTv.setVisibility(isFinishable ? View.VISIBLE : View.GONE);
        topProcessTv.setVisibility(isFinishable ? View.GONE : View.VISIBLE);
        if(!isFinishable && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switch (vo.event.getEventProcess()){
                case EventConfig.PROCESS_FINISHED:
                    configProcessStyle(R.color.md_white_1000,R.color.md_green_300);
                    break;
                case EventConfig.PROCESS_UNFINISHED:
                    configProcessStyle(R.color.md_grey_600, R.color.md_white_1000);
                    break;
                case EventConfig.PROCESS_EXPIRED:
                    configProcessStyle();
                    break;
            }
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                configProcessStyle(R.color.md_grey_600, R.color.md_white_1000);
            }else configProcessStyle();
            if (countDownTimer != null) countDownTimer.cancel();
            countDownTimer = new CountDownTimer(vo.event.getRemainingTime(), 1000) {
                public void onTick(long millisUntilFinished) {
                    topCountdownTv.setText(DateUtils.convertMillisecond2HourHMS(millisUntilFinished));
                }
                public void onFinish() {
                    topCountdownTv.setText(R.string.common_zero_hour_minute_second);
                }
            }.start();
        }
        topProcessTv.setText( vo.event.getEventType() == EventConfig.TYPE_SPEC_LEARNING ? EventConstant.PROCESS_LEARNING[vo.event.getEventProcess() - 1] : EventConstant.PROCESS_NORMAL[vo.event.getEventProcess() - 1]);
        topToFinishBtn.setVisibility(isFinishable ? View.VISIBLE : View.GONE);

        //3.list
        eventSubtypeTv.setText(vo.eventSubtype != null ? vo.eventSubtype.getEventSubtype() : getString(R.string.common_none));
        eventGroupTv.setText(vo.eventGroup != null ? vo.eventGroup.getDescription() : getString(R.string.common_none));
        //学习计划组
        if (vo.learningEventGroup != null){
            strategyTv.setText(LearningEventGroupConstant.STRATEGY[vo.learningEventGroup.getStrategy() - 1]);
            progressTv.setText(String.format("%d/%d", vo.learningEventGroup.getLearningEventFinishedCount(), vo.learningEventGroup.getLearningEventTotal()));
            knowledgeQuantityTv.setText(String.format("%d", vo.learningEventGroup.getKnowledgeQuantity()));
            workloadTv.setText(String.format(App.getContext().getString(R.string.learning_event_group_workload), vo.learningEventGroup.getLearningDuration()));
            topSequenceTv.setText(String.format("%d", vo.event.getEventSequence()));
        }else {
            //控件会被遮住,所以不用设置默认值
        }
        //计划
        expectedFinishDateTv.setText(DateUtils.formatTimestamp2Date(vo.event.getEventExpectedFinishedDate()));
        createTimeTv.setText(DateUtils.formatTimestamp2Datetime(vo.event.getCreateTime()));
        finishedTimeTv.setText(vo.event.getIsEventFinished() ? DateUtils.formatTimestamp2Datetime(vo.event.getEventFinishedTime()) : getString(R.string.planning_display_spec_event_detail_unfinished));
        if (vo.event.getIsRemind()){
            remindTimeTv.setText(DateUtils.formatChinaTimestamp2HourMinute(vo.event.getRemindTime()));
        }else {
            remindTimeTv.setText(R.string.planning_display_spec_event_detail_no_prompt);
        }
        showSequenceSwitch.setChecked(Utils.equals(true, vo.event.getIsShowEventSequence()));
        summaryTv.setText(vo.event.getSummary());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void configProcessStyle(int textColor, int backgroundColor){
        topProcessTv.setTextColor(getColor(textColor));
        topProcessTv.setBackgroundTintList(ColorStateList.valueOf(getColor(backgroundColor)));
        topCountdownTv.setTextColor(getColor(textColor));
        topCountdownTv.setBackgroundTintList(ColorStateList.valueOf(getColor(backgroundColor)));
    }
    /** default */
    private void configProcessStyle(){
        configProcessStyle(R.color.md_grey_300, R.color.md_grey_500);
    }
}
