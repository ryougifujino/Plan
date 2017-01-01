package link.ebbinghaus.planning.ui.view.planning.display.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.yurikami.lib.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.constant.Constant;
import link.ebbinghaus.planning.app.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.core.model.local.vo.planning.display.SpecEventDetailVo;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplaySpecEventDetailPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.display.impl.PlanningDisplaySpecEventDetailPresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplaySpecEventDetailView;
import link.ebbinghaus.planning.ui.view.planning.done.activity.PlanningDoneFinishActivity;
import link.ebbinghaus.planning.ui.viewholder.planning.display.SpecEventDetailViewHolder;

/**
 * 具体计划详情的显示页面
 */
public class PlanningDisplaySpecEventDetailActivity extends BaseActivity implements PlanningDisplaySpecEventDetailView,
        CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public static final String INTENT_NAME_EVENT = Constant.PACKAGE_NAME + "Event";

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    private PlanningDisplaySpecEventDetailPresenter mPresenter;
    //service and view
    private SpecEventDetailVo vo = new SpecEventDetailVo();
    private SpecEventDetailViewHolder vh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_display_spec_event_detail);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        vh = new SpecEventDetailViewHolder(this);
        mPresenter = new PlanningDisplaySpecEventDetailPresenterImpl(this);
        mPresenter.initSpecEventDetail(vo);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setTitle(R.string.planning_display_spec_event_detail_title);

    }

    /** 刷新此界面 */
    private void refresh(){
        mPresenter.refreshSpecEventDetail(vo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void getIntentData() {
        Intent intent = getIntent();
        vo.event = intent.getParcelableExtra(INTENT_NAME_EVENT);
    }

    @Override
    public void selectViewMode() {
        if (vo.event.getEventType() == EventConfig.TYPE_SPEC_LEARNING){
            vh.learningMode();
        }else if (vo.event.getEventType() == EventConfig.TYPE_SPEC_NORMAL){
            vh.normalMode();
        }else {
            throw new IllegalArgumentException("传递给具体计划详情页面的计划类型不正确");
        }
    }

    @Override
    public void registerViewListener() {
        vh.showSequenceSwitch.setOnCheckedChangeListener(this);
        vh.setOnClickListeners(this);   //TODO: implements these listener
    }

    @Override
    public void fillViewWithData() {
        vh.setDataAndConfigViews(vo);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_planning_display_event_detail_is_show_sequence:
                vh.showSequenceSwitch.setChecked(isChecked);
                vo.event.setIsShowEventSequence(isChecked);
                mPresenter.updateIsShowEventSequence(vo.event);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tv_planning_display_event_detail_top_to_finish:
                if (vo.event.isFinishable()) {
                    PlanningDoneFinishActivity.startAction(this, vo.event);
                }else {
                    vh.topToFinishBtn.setVisibility(View.GONE);
                    refresh();
                }
                break;
           case R.id.rl_planning_display_spec_event_detail_subtype:

                break;
            case R.id.rl_planning_display_spec_event_detail_event_group:

                break;
            case R.id.rl_planning_display_spec_event_detail_show_sequence:
                vh.showSequenceSwitch.toggle();
                break;
            case R.id.rl_planning_display_spec_event_detail_strategy:

                break;
            case R.id.rl_planning_display_event_detail_expected_date:

                break;
            case R.id.rl_planning_display_spec_event_detail_remind_time:

                break;
            case R.id.rl_planning_display_spec_event_detail_summary:

                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.planning_display_spec_event_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.item_planning_display_spec_event_detail:
                mPresenter.deleteThisEventAndProcessRelated(vo.event);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void exitThisView() {
        finish();
    }



}
