package link.ebbinghaus.planning.custom.adapter.planning.display.spec;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.model.entity.Event;
import link.ebbinhaus.planning.R;

/**
 * Created by WINFIELD on 2016/3/2.
 */
public class MonthRecyclerViewAdapter extends RecyclerView.Adapter<MonthRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<Event> mEvents;

    public MonthRecyclerViewAdapter(Context mContext, List<Event> mEvents) {
        this.mContext = mContext;
        this.mEvents = mEvents;
    }

    @Override
    public MonthRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.listitem_planning_display_spec_month,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MonthRecyclerViewAdapter.ViewHolder holder, int position) {
        Event event = mEvents.get(position);
        holder.mTextView.setText(event.getDescription());
    }

    @Override
    public int getItemCount() {
        if(mEvents != null)
            return mEvents.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_planning_display_spec_month_listitem) TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
