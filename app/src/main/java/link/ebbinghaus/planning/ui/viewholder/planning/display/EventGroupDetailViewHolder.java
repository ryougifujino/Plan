package link.ebbinghaus.planning.ui.viewholder.planning.display;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.viewholder.BaseActivityViewHolder;

/**
 * Created by WINFIELD on 2016/8/7.
 */
public class EventGroupDetailViewHolder extends BaseActivityViewHolder {

    public TextView dynamicCountLabel;
    public TextView dynamicCountTv;
    public TextView normalCountLabel;
    public TextView normalCountTv;
    public TextView progressLabel;
    public TextView progressTv;

    public EventGroupDetailViewHolder(Activity activity) {
        super(activity);

        dynamicCountLabel = find(R.id.label_planning_display_event_spec_group_dynamic_count);
        dynamicCountTv = find(R.id.tv_planning_display_event_spec_group_dynamic_count);
        normalCountLabel = find(R.id.label_planning_display_event_spec_group_normal_count);
        normalCountTv = find(R.id.tv_planning_display_event_spec_group_normal_count);
        progressLabel = find(R.id.label_planning_display_event_spec_group_progress);
        progressTv = find(R.id.tv_planning_display_event_spec_group_progress);

    }

    public void switchToAbstract(){
        dynamicCountLabel.setText("模糊计划");
        normalCountLabel.setVisibility(View.GONE);
        normalCountTv.setVisibility(View.GONE);
        progressLabel.setVisibility(View.GONE);
        progressTv.setVisibility(View.GONE);
    }

    public void setDynamicCount(int count){
        dynamicCountTv.setText(String.valueOf(count));
    }

    public void setNormalCount(int count){
        normalCountTv.setText(String.valueOf(count));
    }

    @SuppressLint("SetTextI18n")
    public void setProgress(int numerator, int denominator){
        progressTv.setText(numerator + "/" + denominator);
    }
}
