package link.ebbinghaus.planning.ui.view.planning.display.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.util.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.constant.Constant;
import link.ebbinghaus.planning.app.util.DensityUtils;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.model.local.po.EventGroup;
import link.ebbinghaus.planning.ui.adapter.planning.display.abst.AllRecyclerViewAdapter;
import link.ebbinghaus.planning.ui.adapter.planning.display.spec.WeekRecyclerViewAdapter;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplayEventGroupDetailPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.display.impl.PlanningDisplayEventGroupDetailPresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplayEventGroupDetailView;
import link.ebbinghaus.planning.ui.viewholder.planning.display.EventGroupDetailViewHolder;
import link.ebbinghaus.planning.ui.widget.SpaceItemDecoration;

public class PlanningDisplayEventGroupDetailActivity extends BaseActivity implements PlanningDisplayEventGroupDetailView {

    public static final String INTENT_NAME_EVENT_GROUP = Constant.PACKAGE_NAME + ".EventGroup";
    public static final String INTENT_NAME_FLAG = Constant.PACKAGE_NAME + ".Flag";

    @Bind(R.id.rv_planning_display_event_group_detail) RecyclerView mRecyclerView;
    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.ctl) CollapsingToolbarLayout mCtl;
    private boolean mFirstAccess = true;
    private EventGroupDetailViewHolder vh;
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
        vh = new EventGroupDetailViewHolder(this);
        mPresenter = new PlanningDisplayEventGroupDetailPresenterImpl(this);
        mPresenter.configureRecyclerView();
        configureToolbar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //更新数据
        if (!mFirstAccess) {
            mEventGroup = mPresenter.obtainEventGroup(mEventGroup.getPkEventGroupId());
            mEvents = mPresenter.obtainEventGroupDetailData(mEventGroupType, mEventGroup);
            setRecyclerViewHeader();
            if (mEventGroupType) {
                ((WeekRecyclerViewAdapter) mAdapter).refresh(mEvents);
            } else {
                ((AllRecyclerViewAdapter) mAdapter).refresh(mEvents);
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirstAccess = false;
    }

    private void configureToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
//        mToolbar.setTitle(R.string.planning_display_event_group_toolbar_title);
        mCtl.setTitle(getString(R.string.planning_display_event_group_detail_create_time, DateUtils.fromTimestamp2Date(mEventGroup.getCreateTime())));
        mCtl.setCollapsedTitleTextColor(getResources().getColor(R.color.md_white_1000));
        mCtl.setExpandedTitleColor(Color.parseColor("#709BBE"));
        mCtl.setExpandedTitleMarginStart(DensityUtils.dp2px(20));
        mCtl.setExpandedTitleMarginBottom(DensityUtils.dp2px(20));
    }

    /**
     * 启动本Activity(带数据)
     *
     * @param context        发送者上下文
     * @param eventGroup     计划组数据
     * @param eventGroupType true:具体计划组 false:模糊计划组
     */
    public static void actionStart(Context context, EventGroup eventGroup, boolean eventGroupType) {
        Intent intent = new Intent(context, PlanningDisplayEventGroupDetailActivity.class);
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
        if (mEventGroupType){
            vh.setDynamicCount(mEventGroup.getLearningEventCount());
            vh.setNormalCount(mEventGroup.getNormalEventCount());

            int finishedCount = 0;
            for (Event event : mEvents) {
                if (event.getIsEventFinished())
                    finishedCount++;
            }
            vh.setProgress(finishedCount,mEvents.size());
        }else {
            vh.switchToAbstract();
            vh.setDynamicCount(mEventGroup.getAbstractEventCount());
        }
    }

    @Override
    public void setRecyclerViewAdapter() {

        mEvents = mPresenter.obtainEventGroupDetailData(mEventGroupType, mEventGroup);

        if (mEventGroupType) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mAdapter = new WeekRecyclerViewAdapter(this, mEvents);
        } else {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            mRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(5)));
            mAdapter = new AllRecyclerViewAdapter(this, mEvents);

        }
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
