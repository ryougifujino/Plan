package link.ebbinghaus.planning.view.planning.display.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.util.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.ebbinghaus.adapter.planning.display.abst.AllRecyclerViewAdapter;
import link.ebbinghaus.planning.ebbinghaus.adapter.planning.display.spec.WeekRecyclerViewAdapter;
import link.ebbinghaus.planning.ebbinghaus.constant.Constant;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.presenter.planning.display.PlanningDisplayEventGroupDetailPresenter;
import link.ebbinghaus.planning.presenter.planning.display.impl.PlanningDisplayEventGroupDetailPresenterImpl;
import link.ebbinghaus.planning.view.planning.display.PlanningDisplayEventGroupDetailView;
import link.ebbinghaus.planning.R;

public class PlanningDisplayEventGroupDetailActivity extends BaseActivity implements PlanningDisplayEventGroupDetailView {

    public static final String INTENT_NAME_EVENT_GROUP = Constant.PACKAGE_NAME + ".EventGroup";
    public static final String INTENT_NAME_FLAG = Constant.PACKAGE_NAME + ".Flag";

    @Bind(R.id.rv_planning_display_event_group_detail) RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private PlanningDisplayEventGroupDetailPresenter mPresenter;
    //存放数据
    private boolean mEventGroupType;
    private EventGroup mEventGroup;
    private List<Event> mEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_display_event_group_detail);
        ButterKnife.bind(this);
        mPresenter = new PlanningDisplayEventGroupDetailPresenterImpl(this);
        mPresenter.configureRecyclerView();

    }

    /**
     * 启动本Activity(带数据)
     * @param context 发送者上下文
     * @param eventGroup 计划组数据
     * @param eventGroupType true:具体计划组 false:模糊计划组
     */
    public static void actionStart(Context context,EventGroup eventGroup,boolean eventGroupType){
        Intent intent = new Intent(context,PlanningDisplayEventGroupDetailActivity.class);
        intent.putExtra(INTENT_NAME_EVENT_GROUP, eventGroup);
        intent.putExtra(INTENT_NAME_FLAG, eventGroupType);
        context.startActivity(intent);
    }

    @Override
    public void getIntentData() {
        Intent intent = getIntent();
        mEventGroupType = intent.getBooleanExtra(INTENT_NAME_FLAG, true);
        mEventGroup = intent.getParcelableExtra(INTENT_NAME_EVENT_GROUP);
    }

    @Override
    public void setRecyclerViewHeader() {
        RecyclerViewHeader header;
        if (mEventGroupType){
            header = RecyclerViewHeader.fromXml(this,R.layout.listheader_planning_display_spec_event_group);
            setSpecificHeader(header);
        }else {
            header = RecyclerViewHeader.fromXml(this,R.layout.listheader_planning_display_abst_event_group);
            setAbstractHeader(header);
        }
        header.attachTo(mRecyclerView);
    }

    @Override
    public void setRecyclerViewAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mEvents = mPresenter.obtainEventGroupDetailData(mEventGroupType,mEventGroup);

        if (mEventGroupType){
            mAdapter = new WeekRecyclerViewAdapter(this,mEvents);

        }else {
            mAdapter = new AllRecyclerViewAdapter(this,mEvents);

        }
        mRecyclerView.setAdapter(mAdapter);
    }

    //TODO:删除后刷新列表

    @SuppressLint("SetTextI18n")    //TODO:收纳入ViewHolder
    public void setSpecificHeader(RecyclerViewHeader view) {
        TextView createTimeTv = (TextView) view.findViewById(R.id.tv_planning_display_event_spec_group_create_time);
        TextView learningCountTv = (TextView) view.findViewById(R.id.tv_planning_display_event_spec_group_learning_count);
        TextView normalCountTv = (TextView) view.findViewById(R.id.tv_planning_display_event_spec_group_normal_count);
        TextView progressTv = (TextView) view.findViewById(R.id.tv_planning_display_event_spec_group_progress);
        createTimeTv.setText(getString(R.string.planning_display_event_group_detail_create_time, DateUtils.formatTimestamp2Datetime(mEventGroup.getCreateTime())));
        learningCountTv.setText(mEventGroup.getLearningEventCount() + "");
        normalCountTv.setText(mEventGroup.getNormalEventCount() + "");
        int finishedCount = 0;
        for (Event event : mEvents) {
            if (event.getIsEventFinished())
                finishedCount ++;
        }
        progressTv.setText(finishedCount + "/" + mEvents.size());
    }

    @SuppressLint("SetTextI18n")    //TODO:收纳入ViewHolder
    public void setAbstractHeader(RecyclerViewHeader view) {
        TextView createTime = (TextView) view.findViewById(R.id.tv_planning_display_event_abst_group_create_time);
        TextView eventCount = (TextView) view.findViewById(R.id.tv_planning_display_event_abst_group_event_count);
        createTime.setText(getString(R.string.planning_display_event_group_detail_create_time, DateUtils.formatTimestamp2Datetime(mEventGroup.getCreateTime())));
        eventCount.setText(mEventGroup.getAbstractEventCount() + "");
    }
}
