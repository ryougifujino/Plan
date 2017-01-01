package link.ebbinghaus.planning.ui.adapter.history;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yurikami.lib.model.Datetime;
import com.yurikami.lib.util.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.App;
import link.ebbinghaus.planning.app.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.ui.view.planning.display.activity.PlanningDisplaySpecEventDetailActivity;

/**
 * Created by WINFIELD on 2017/1/1.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Event> mEvents;

    public static final int GREEN_RES_ID = App.getContext().getResources().getColor(R.color.md_green_300);

    public HistoryRecyclerViewAdapter(Context context, List<Event> events) {
        this.mContext = context;
        this.mEvents = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.listitem_history_event, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mEvents.get(position));
    }

    @Override
    public int getItemCount() {
        if (mEvents != null) {
            return mEvents.size();
        }
        return 0;
    }

    /**
     * 刷新列表
     * @param events 新的数据
     */
    public void refresh(List<Event> events){
        mEvents = events;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.tv_history_expected_date) TextView expectedDateTv;
        @Bind(R.id.v_history_divider) View divider;
        @Bind(R.id.tv_history_description) TextView descriptionTv;
        @Bind(R.id.ll_history_event) LinearLayout eventLl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(Event event) {
            Datetime d = DateUtils.convertTimestamp2Datetime(event.getEventExpectedFinishedDate());
            expectedDateTv.setText(String.format(mContext.getString(R.string.common_date),
                    d.getYear(), d.getMonth(), d.getDay()));
            descriptionTv.setText(event.getDescription());
            if (event.getEventProcess() == EventConfig.PROCESS_FINISHED)
                divider.setBackgroundColor(GREEN_RES_ID);
            eventLl.setOnClickListener(this);
            eventLl.setTag(event);
        }

        @Override
        public void onClick(View v) {
            Event event = (Event) v.getTag();
            Intent intent = new Intent(mContext, PlanningDisplaySpecEventDetailActivity.class);
            intent.putExtra(PlanningDisplaySpecEventDetailActivity.INTENT_NAME_EVENT,event);
            mContext.startActivity(intent);
        }
    }
}
