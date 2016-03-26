package link.ebbinghaus.planning.custom.adapter.planning.display;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yurikami.lib.entity.Datetime;
import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.LogUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.model.PlanningDisplayModel;
import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.impl.PlanningDisplayModelImpl;
import link.ebbinhaus.planning.R;

/**
 * Created by WINFIELD on 2016/3/2.
 */
public class GroupRecyclerViewAdapter extends RecyclerView.Adapter<GroupRecyclerViewAdapter.ViewHolder>{

    private Context mContext;
    private boolean mFlag;
    private PlanningDisplayModel mPlanningDisplayModel;
    private List<EventGroup> mEventGroups;

    public GroupRecyclerViewAdapter(Context context, boolean flag) {
        this.mContext = context;
        this.mFlag = flag;
        this.mPlanningDisplayModel = new PlanningDisplayModelImpl();

        mEventGroups = mPlanningDisplayModel.findAllEventGroup();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.listitem_planning_display_event_group,parent,false);
        return new ViewHolder(itemView);
    }

    /**
     * 刷新列表
     */
    public void refresh(){
        mEventGroups = mPlanningDisplayModel.findAllEventGroup();
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mEventGroups.get(position));
    }

    @Override
    public int getItemCount() {
        if (mEventGroups != null){
            return mEventGroups.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.rl_planning_display_event_group) RelativeLayout listitemRl;
        @Bind(R.id.tv_planning_display_event_group_create_time) TextView createTimeTv;
        @Bind(R.id.tv_planning_display_event_group_event_count) TextView eventCountTv;
        @Bind(R.id.tv_view_planning_display_event_group_description) TextView descriptionTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(EventGroup eventGroup) {
            try {
                long createTime = eventGroup.getCreateTime();
                Datetime datetime = DateUtils.convertTimestamp2Datetime(createTime);
                int daysBeforeToday = DateUtils.days2TimestampToday(createTime);
                createTimeTv.setText(String.format(mContext.getString(R.string.planning_display_create_time),
                        datetime.getYear(), datetime.getMonth(), datetime.getDay(),
                        datetime.getHour(),datetime.getMinute(),
                        daysBeforeToday < 999 ? daysBeforeToday + "" : "999+"));
                eventCountTv.setText(String.format(mContext.getString(R.string.planning_display_event_group_event_count),
                        mFlag ? eventGroup.getLearningEventCount() + eventGroup.getNormalEventCount()
                                : eventGroup.getAbstractEventCount()));
                descriptionTv.setText(eventGroup.getDescription());
                listitemRl.setOnClickListener(this);
                listitemRl.setTag(eventGroup);
            }catch (Exception e){
                LogUtils.e("Exception", e.getMessage());
            }
        }

        @Override
        public void onClick(View v) {
            EventGroup eventGroup = (EventGroup) v.getTag();
        }
    }
}
