package link.ebbinghaus.planning.ui.adapter.planning.display.spec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yurikami.lib.model.Datetime;
import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.LogUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.constant.model.EventConstant;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.ui.view.planning.display.activity.PlanningDisplaySpecEventDetailActivity;

/**
 * Created by WINFIELD on 2016/3/2.
 */
public class WeekRecyclerViewAdapter extends RecyclerView.Adapter<WeekRecyclerViewAdapter.ViewHolder>{

    private Context mContext;
    private List<Event> mSpecWeekEvents;

    public WeekRecyclerViewAdapter(Context context, List<Event> specWeekEvents) {
        this.mContext = context;
        mSpecWeekEvents = specWeekEvents;
    }

    /**
     * 刷新按月视图
     * @param specWeekEvents 新数据
     */
    public void refresh(List<Event> specWeekEvents) {
        mSpecWeekEvents = specWeekEvents;
        this.notifyDataSetChanged();
    }

    @Override
    public WeekRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.listitem_planning_display_spec_week, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeekRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.setData(mSpecWeekEvents.get(position));
    }


    @Override
    public int getItemCount() {
        if (mSpecWeekEvents != null){
            return mSpecWeekEvents.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.ll_planning_display_spec_week) LinearLayout listitemLl;
        @Bind(R.id.iv_planning_display_spec_week_week) ImageView weekIv;
//        @Bind(R.id.tv_planning_display_spec_week_week) TextView weekTv;
        @Bind(R.id.tv_planning_display_spec_week_event_type) TextView eventTypeTv;
        @Bind(R.id.tv_planning_display_spec_week_date) TextView dateTv;
//        @Bind(R.id.tv_planning_display_spec_week_process) TextView processTv;
        @Bind(R.id.tv_planning_display_spec_week_description) TextView descriptionTv;
        @Bind(R.id.tv_planning_display_spec_week_detail) TextView detailTv;
        @Bind(R.id.tv_planning_display_spec_week_hide) TextView hideTv;

        private int[] weekImgRes = {R.mipmap.mon,R.mipmap.tue,
                R.mipmap.wed,R.mipmap.thu,
                R.mipmap.fri,R.mipmap.sat,R.mipmap.sun};
        private int[] eventTypeRes = {R.string.planning_display_spec_week_listitem_learning,R.string.planning_display_spec_week_listitem_normal};
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Event event) {
            try {
                Datetime planDate = DateUtils.convertTimestamp2Datetime(event.getEventExpectedFinishedDate());
                int week = planDate.getWeek();
                int weekIndex = (week == -1) ? 0 : (week - 1);
                weekIv.setImageResource(weekImgRes[weekIndex]);
//                weekTv.setText(mContext.getString(ConstRes.WEEK[weekIndex]));
                eventTypeTv.setText(eventTypeRes[event.getEventType() - 1]);
                dateTv.setText(String.format(mContext.getString(R.string.planning_display_spec_week_listitem_date), planDate.getMonth(), planDate.getDay(),
                        mContext.getString(event.getEventType() == 1
                        ? EventConstant.PROCESS_LEARNING[event.getEventProcess() - 1]
                        : EventConstant.PROCESS_NORMAL[event.getEventProcess() - 1])));
//                processTv.setText(event.getEventType() == 1
//                        ? EventConstant.PROCESS_LEARNING[event.getEventProcess() - 1]
//                        : EventConstant.PROCESS_NORMAL[event.getEventProcess() - 1]);
                descriptionTv.setText(event.getDescription());
                listitemLl.setOnClickListener(this);
                listitemLl.setTag(event);
                detailTv.setOnClickListener(this);
                detailTv.setTag(event);
                hideTv.setOnClickListener(this);
                hideTv.setTag(event);
            }catch (Exception e){
                LogUtils.e("Exception", e.getMessage());
            }
        }

        @Override
        public void onClick(View v) {
            Event event = (Event) v.getTag();
            switch (v.getId()){
                case R.id.tv_planning_display_spec_week_detail:
                case R.id.ll_planning_display_spec_week:
                    Intent intent = new Intent(mContext, PlanningDisplaySpecEventDetailActivity.class);
                    intent.putExtra(PlanningDisplaySpecEventDetailActivity.INTENT_NAME_EVENT,event);
                    mContext.startActivity(intent);
                    break;
                case R.id.tv_planning_display_spec_week_hide:
                    int index = mSpecWeekEvents.indexOf(event);
                    mSpecWeekEvents.remove(index);
                    WeekRecyclerViewAdapter.this.notifyItemRemoved(index);
                    break;
            }

        }
    }
}
