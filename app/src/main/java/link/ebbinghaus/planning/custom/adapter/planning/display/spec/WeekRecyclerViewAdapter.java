package link.ebbinghaus.planning.custom.adapter.planning.display.spec;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.entity.Datetime;

import java.util.List;

import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * Created by WINFIELD on 2016/3/2.
 */
public class WeekRecyclerViewAdapter extends RecyclerView.Adapter<WeekRecyclerViewAdapter.ViewHolder>{
    private Context mContext;
    private List<Event> mSpecWeekEvents;
    private Datetime mDatetime;

    public WeekRecyclerViewAdapter(Context context, Datetime datetime) {
        this.mContext = context;
        this.mDatetime = datetime;
    }

    @Override
    public WeekRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(WeekRecyclerViewAdapter.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        if (mSpecWeekEvents != null){
            return mSpecWeekEvents.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
