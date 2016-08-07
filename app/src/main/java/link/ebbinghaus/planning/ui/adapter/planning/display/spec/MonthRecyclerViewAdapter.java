package link.ebbinghaus.planning.ui.adapter.planning.display.spec;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yurikami.lib.base.BaseFragment;
import com.yurikami.lib.model.Datetime;
import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.ViewGroupUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.App;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.service.PlanningDisplaySpecificService;
import link.ebbinghaus.planning.core.service.impl.PlanningDisplaySpecificServiceImpl;
import link.ebbinghaus.planning.ui.view.planning.display.activity.PlanningDisplaySpecEventDetailActivity;

/**
 * Created by WINFIELD on 2016/3/2.
 */
public class MonthRecyclerViewAdapter extends RecyclerView.Adapter<MonthRecyclerViewAdapter.ViewHolder>
        implements BaseFragment.OnFragmentStopListener {
    private final int ROW_EVENT_COUNT = 3;

    private Context mContext;
    private LayoutInflater mInflater;
    //某一个月所有的Event
    private List<Event> mSpecMonthEvents;
    private Datetime mDatetime;
    //某一个月按日归类的Event
    private List<Event>[] mBlocks;

    private int mDayInMonth;
    //存储这个月的日和星期的集合
    private List<Datetime> mDayWeekListitems = new ArrayList<>();

    private Map<Integer, LinearLayout> mBlocksCache = new HashMap<>();  //TODO:思考这里是否有问题
    private PlanningDisplaySpecificService mPlanningDisplaySpecificService;



    public MonthRecyclerViewAdapter(Context context, Datetime datetime) {
        this.mContext = context;
        this.mDatetime = datetime;
        mInflater = LayoutInflater.from(mContext);
        mDayInMonth = DateUtils.dayInMonth(mDatetime);
        mPlanningDisplaySpecificService = new PlanningDisplaySpecificServiceImpl();

        mPlanningDisplaySpecificService.makeDayWeekListitems(mDayWeekListitems, mDayInMonth, datetime);
        mSpecMonthEvents = mPlanningDisplaySpecificService.findSpecMonthEvents(mDatetime);
        mBlocks = mPlanningDisplaySpecificService.eventsToBlocks(mSpecMonthEvents, mDayInMonth);

    }

    /**
     * 刷新按月视图
     * @param newDatetime
     */
    public void refresh(Datetime newDatetime){
        mDatetime = newDatetime;
        mDayWeekListitems.clear();
        mBlocksCache.clear();
        mDayInMonth = DateUtils.dayInMonth(mDatetime);
        mPlanningDisplaySpecificService.makeDayWeekListitems(mDayWeekListitems, mDayInMonth, mDatetime);
        List<Event> newSpecMonthEvents = mPlanningDisplaySpecificService.findSpecMonthEvents(mDatetime);
        mBlocks = mPlanningDisplaySpecificService.eventsToBlocks(newSpecMonthEvents, mDayInMonth);
        this.notifyDataSetChanged();
    }
    public void refresh(){
        refresh(mDatetime);
    }

    @Override
    public MonthRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.listitem_planning_display_spec_month, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MonthRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.blockFl.removeAllViews();

        List<Event> blockEvents = mBlocks[position];
        int blockEventCount = blockEvents.size();

        if (blockEventCount != 0) {
            LinearLayout block = mBlocksCache.get(position);
            if (block == null) {
                ViewGroup.LayoutParams lp = ViewGroupUtils.genMatchWrapLP();
                block = ViewGroupUtils.newVerticalLl(mContext, lp);

                //把block里面的所有Event放入block容器中
                LinearLayout row = ViewGroupUtils.newHorizontalLl(mContext, lp);
                for (int i = 1; i <= blockEventCount; i++) {
                    Event event = blockEvents.get(i - 1);
                    TextView eventTv = (TextView) mInflater.inflate(R.layout.textview_planning_display_spec_month_event, row, false);
                    holder.setMonthEventAttrs(eventTv, event);
                    row.addView(eventTv);
                    //每隔LISTITEM_ROW_COUNT个Event,新建一行,并把上一行添加到Row中
                    if ((i % ROW_EVENT_COUNT == 0) || (i == blockEventCount)) {
                        block.addView(row);
                        row = ViewGroupUtils.newHorizontalLl(mContext, lp);
                    }
                }
                mBlocksCache.put(position, block);
            }
            ViewGroupUtils.snipParent(block);
            holder.blockFl.addView(block);
        }

        holder.setDayWeek(mDayWeekListitems.get(position));
        holder.markToday(this.mDatetime.getYear(), this.mDatetime.getMonth(), mDayWeekListitems.get(position).getDay());
        holder.countTv.setText(blockEventCount > 999 ? "999+" : blockEventCount + "");

    }



    @Override
    public int getItemCount() {
        if(mBlocks != null)
            return mBlocks.length;
        return 0;
    }

    @Override
    public void onStop() {
        //Fragment执行到onStop时,清除缓存
        mBlocksCache.clear();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        @Bind(R.id.tv_planning_display_spec_month_day_of_month) TextView dayOfMonthTv;
        @Bind(R.id.tv_planning_display_spec_month_day_of_week) TextView dayOfWeekTv;
        @Bind(R.id.tv_planning_display_spec_month_listitem_count) TextView countTv;
        @Bind(R.id.fl_planning_display_spec_month_block) FrameLayout blockFl;

        private Datetime today = DateUtils.dateOfToday();
        private int blue = ContextCompat.getColor(mContext, R.color.md_blue_300);
        private ColorStateList defaultColor = new TextView(mContext).getTextColors();

        private AlertDialog.Builder quickView = new AlertDialog.Builder(mContext);

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void markToday(int year, int month, int day){
            if (today.getYear() == year && today.getMonth() == month && today.getDay() == day){
                dayOfMonthTv.setTextColor(blue);
                dayOfWeekTv.setTextColor(blue);
            }else {
                dayOfMonthTv.setTextColor(defaultColor);
                dayOfWeekTv.setTextColor(defaultColor);
            }
        }

        public void setDayWeek(Datetime dayWeek){
            Resources res = mContext.getResources();
            dayOfMonthTv.setText(String.format(res.getString(R.string.planning_display_spec_month_listitem_day), dayWeek.getDay()));
            dayOfWeekTv.setText(String.format(res.getString(R.string.planning_display_spec_month_listitem_week), dayWeek.getChnWeek()));
        }

        public void setMonthEventAttrs(TextView eventTv, Event event){
            String realDescription = event.getDescription();
            if (event.getIsShowEventSequence()){
                realDescription = event.getEventSequence() + "$" + realDescription;
            }
            eventTv.setText(realDescription);
            int width = App.getSystemInfo().getWindowWidth() / 4;
            if (event.getEventType() == 2){
                eventTv.setBackgroundResource(R.drawable.planning_display_spec_month_event_normal);
            }
            eventTv.setWidth(width);
            eventTv.setOnClickListener(this);
            eventTv.setOnLongClickListener(this);
            eventTv.setTag(event);
        }

        @Override
        public void onClick(View v) {
            Event event = (Event) v.getTag();
            Intent intent = new Intent(mContext, PlanningDisplaySpecEventDetailActivity.class);
            intent.putExtra(PlanningDisplaySpecEventDetailActivity.INTENT_NAME_EVENT,event);
            mContext.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            Event event = (Event) v.getTag();
            quickView.setMessage(event.getDescription());
            quickView.show();
            return true;
        }
    }
}
