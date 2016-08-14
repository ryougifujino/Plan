package link.ebbinghaus.planning.ui.adapter.planning.done;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yurikami.lib.model.Datetime;
import com.yurikami.lib.util.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.app.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.app.constant.model.EventConstant;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.ui.view.planning.display.activity.PlanningDisplaySpecEventDetailActivity;
import link.ebbinghaus.planning.ui.view.planning.done.activity.PlanningDoneFinishActivity;
import link.ebbinghaus.planning.R;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public class FinishRecyclerViewAdapter extends RecyclerView.Adapter<FinishRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Event> mEvents;

    //caches
    private int[] mIndicatorRes = {R.drawable.planning_done_finished,R.drawable.planning_done_to_finish};
    private int[] mColor;

    public FinishRecyclerViewAdapter(Context context, List<Event> events) {
        this.mContext = context;
        this.mEvents = events;

        //init caches
        mColor = new int[]{mContext.getResources().getColor(R.color.md_light_green_500),mContext.getResources().getColor(R.color.md_light_blue_500)};
    }

    /**
     * 刷新列表
     * @param events 新的数据
     */
    public void refresh(List<Event> events){
        mEvents = events;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.listitem_planning_done, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mEvents.get(position));
    }

    @Override
    public int getItemCount() {
        if (mEvents != null){
            return mEvents.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.iv_planning_done_process_indicator) ImageView indicatorIv;
        @Bind(R.id.tv_planning_done_expected_finished_time) TextView expectedFinishedTimeTv;
        @Bind(R.id.iv_planning_done_detail) ImageView detailIv;
        @Bind(R.id.tv_planning_done_finish) TextView finishTv;
        @Bind(R.id.rl_planning_done_up_part) RelativeLayout upPartRl;
        @Bind(R.id.tv_planning_done_event_type) TextView eventTypeTv;
        @Bind(R.id.tv_planning_done_finished_date) TextView finishedTimeTv;
        @Bind(R.id.tv_planning_done_description) TextView descriptionTv;
        @Bind(R.id.tv_planning_done_learning_event_sequence) TextView sequenceTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(Event event) {
            if (event.getIsEventFinished()) {
                indicatorIv.setImageResource(mIndicatorRes[0]);
                indicatorIv.setColorFilter(mColor[0]);
                Datetime finishedDate = DateUtils.convertTimestamp2Datetime(event.getEventFinishedTime());
                finishedTimeTv.setText(mContext.getString(R.string.planning_done_finished_time, finishedDate.getYear(), finishedDate.getMonth(), finishedDate.getDay(),finishedDate.getHour(),finishedDate.getMinute(),finishedDate.getSecond()));
                finishedTimeTv.setTextColor(mColor[0]);
                expectedFinishedTimeTv.setTextColor(mColor[0]);
                detailIv.setVisibility(View.VISIBLE);
                finishTv.setVisibility(View.GONE);
            }else{
                indicatorIv.setImageResource(mIndicatorRes[1]);
                indicatorIv.setColorFilter(mColor[1]);
                finishedTimeTv.setText(R.string.planning_done_event_not_finished);
                finishedTimeTv.setTextColor(mColor[1]);
                expectedFinishedTimeTv.setTextColor(mColor[1]);
                detailIv.setVisibility(View.GONE);
                finishTv.setVisibility(View.VISIBLE);
            }
            Datetime expectedFinishedDate = DateUtils.convertTimestamp2Datetime(event.getEventExpectedFinishedDate());
            expectedFinishedTimeTv.setText(mContext.getString(R.string.planning_done_expected_finished_time, expectedFinishedDate.getYear(), expectedFinishedDate.getMonth(), expectedFinishedDate.getDay()));
            upPartRl.setTag(event);
            upPartRl.setOnClickListener(this);
            eventTypeTv.setText(EventConstant.EVENT_TYPE[event.getEventType() - 1]);
            descriptionTv.setText(event.getDescription());
            if (event.getEventType() == EventConfig.TYPE_SPEC_LEARNING){
                sequenceTv.setVisibility(View.VISIBLE);
                sequenceTv.setText(mContext.getString(R.string.planning_done_learning_event_sequence,event.getEventSequence()));
            }else {
                sequenceTv.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            Event event = (Event) v.getTag();
            if (event.getIsEventFinished()){
                //若计划完成,则跳转到详情页面
                Intent intent = new Intent(mContext, PlanningDisplaySpecEventDetailActivity.class);
                intent.putExtra(PlanningDisplaySpecEventDetailActivity.INTENT_NAME_EVENT,event);
                mContext.startActivity(intent);
            }else {
                PlanningDoneFinishActivity.startAction(mContext,event);
            }
        }

    }


}
