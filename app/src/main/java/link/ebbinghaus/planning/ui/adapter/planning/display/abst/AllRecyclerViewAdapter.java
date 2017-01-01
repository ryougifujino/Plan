package link.ebbinghaus.planning.ui.adapter.planning.display.abst;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yurikami.lib.model.Datetime;
import com.yurikami.lib.util.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.service.PlanningDisplayAbstractService;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplayAbstractServiceImpl;

/**
 * Created by WINFIELD on 2016/3/2.
 */
public class AllRecyclerViewAdapter extends RecyclerView.Adapter<AllRecyclerViewAdapter.ViewHolder>{

    private Context mContext;
    private List<Event> mEvents;

    public AllRecyclerViewAdapter(Context context, List<Event> events) {
        this.mContext = context;
        mEvents = events;
    }

    /**
     * 刷新列表
     * @param events 新数据
     */
    public void refresh(List<Event> events){
        mEvents = events;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.listitem_planning_display_abst_all,parent,false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_planning_display_abst_all_create_time) TextView createTimeTv;
//        @Bind(R.id.tv_planning_display_abst_all_week) TextView weekTv;
        @Bind(R.id.tv_planning_display_abst_all_description) TextView descriptionTv;
        @Bind(R.id.img_planning_display_abst_all_more) ImageView moreImg;
        private PopupMenu mPopupMenu;
        private PlanningDisplayAbstractService mPlanningDisplayAbstractService;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mPlanningDisplayAbstractService = new PlanningDisplayAbstractServiceImpl();
            mPopupMenu = new PopupMenu(mContext,moreImg);
            mPopupMenu.inflate(R.menu.planning_display_abst_all_more);
            mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int pos = ViewHolder.this.getLayoutPosition();
                    switch (item.getItemId()){
                        case R.id.item_planning_display_abst_all_more_copy:
                            ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                            cm.setPrimaryClip(ClipData.newPlainText("text",mEvents.get(pos).getDescription()));
                            break;
                        case R.id.item_planning_display_abst_all_more_delete:
                            Event e = mEvents.get(pos);
                            mPlanningDisplayAbstractService.removeAbstEvent(e.getPkEventId(),e.getEventGroupId());
                            mEvents.remove(pos);
                            AllRecyclerViewAdapter.this.notifyItemRemoved(pos);
                            break;
                    }
                    return false;
                }
            });
        }

        public void setData(Event event) {
            //TODO:try cache
            long createTime = event.getCreateTime();
            Datetime datetime = DateUtils.convertTimestamp2Datetime(createTime);

            int daysBeforeToday = -DateUtils.daysTimestamp2Today(createTime);
//            String beforeDayStr;
//            if (daysBeforeToday == 0){
//                beforeDayStr = mContext.getString(R.string.planning_display_today);
//            }else if (daysBeforeToday < 999){
//                beforeDayStr = daysBeforeToday + mContext.getString(R.string.planning_display_before_day);
//            }else {
//                beforeDayStr = "999+";
//            }

            createTimeTv.setText(String.format(mContext.getString(R.string.planning_display_create_time),
                    datetime.getYear(), datetime.getMonth(), datetime.getDay(),
                    datetime.getHour(),datetime.getMinute()));
//            weekTv.setText(mContext.getString(ConstRes.WEEK[datetime.getWeek() - 1]));
            descriptionTv.setText(event.getDescription());

            moreImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPopupMenu.show();
                }
            });

        }
    }
}
