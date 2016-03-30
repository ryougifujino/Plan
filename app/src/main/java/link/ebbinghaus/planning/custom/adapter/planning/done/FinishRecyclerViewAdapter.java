package link.ebbinghaus.planning.custom.adapter.planning.done;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yurikami.lib.entity.Datetime;
import com.yurikami.lib.util.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.constant.entity.EventConstant;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.view.activity.impl.PlanningDisplaySpecEventDetailActivity;
import link.ebbinghaus.planning.view.activity.impl.PlanningDoneFinishActivity;
import link.ebbinhaus.planning.R;

/**
 * Created by WINFIELD on 2016/3/30.
 */
public class FinishRecyclerViewAdapter extends RecyclerView.Adapter<FinishRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Event> mEvents;

    public FinishRecyclerViewAdapter(Context context, List<Event> events) {
        this.mContext = context;
        this.mEvents = events;
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

        private int[] indicatorRes = {R.drawable.planning_done_finished,R.drawable.planning_done_to_finish};
        private int[] color = {mContext.getResources().getColor(R.color.md_light_green_500),mContext.getResources().getColor(R.color.md_light_blue_500)};
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(Event event) {
            if (event.getIsEventFinished()) {
                indicatorIv.setImageResource(indicatorRes[0]);
                indicatorIv.setColorFilter(color[0]);
                Datetime finishedDate = DateUtils.convertTimestamp2Datetime(event.getEventFinishedTime());
                finishedTimeTv.setText(mContext.getString(R.string.planning_done_finished_time, finishedDate.getYear(), finishedDate.getMonth(), finishedDate.getDay()));
                expectedFinishedTimeTv.setTextColor(color[0]);
                detailIv.setVisibility(View.VISIBLE);
                finishTv.setVisibility(View.GONE);
            }else{
                indicatorIv.setImageResource(indicatorRes[1]);
                indicatorIv.setColorFilter(color[1]);
                finishedTimeTv.setText(R.string.planning_done_event_not_finished);
                expectedFinishedTimeTv.setTextColor(color[1]);
                detailIv.setVisibility(View.GONE);
                finishTv.setVisibility(View.VISIBLE);
            }
            Datetime expectedFinishedDate = DateUtils.convertTimestamp2Datetime(event.getEventExpectedFinishedDate());
            expectedFinishedTimeTv.setText(mContext.getString(R.string.planning_done_expected_finished_time, expectedFinishedDate.getYear(), expectedFinishedDate.getMonth(), expectedFinishedDate.getDay()));
            upPartRl.setTag(event);
            upPartRl.setOnClickListener(this);
            eventTypeTv.setText(EventConstant.EVENT_TYPE[event.getEventType() - 1]);
            descriptionTv.setText(event.getDescription());
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
