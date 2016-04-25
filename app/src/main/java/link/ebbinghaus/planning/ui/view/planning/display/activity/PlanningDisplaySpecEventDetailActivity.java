package link.ebbinghaus.planning.ui.view.planning.display.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.yurikami.lib.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.common.constant.Constant;
import link.ebbinghaus.planning.common.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.core.model.local.vo.planning.display.SpecEventDetailVo;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplaySpecEventDetailPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.display.impl.PlanningDisplaySpecEventDetailPresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplaySpecEventDetailView;
import link.ebbinghaus.planning.ui.viewholder.planning.display.SpecEventDetailViewHolder;

/**
 * 具体计划详情的显示页面
 */
public class PlanningDisplaySpecEventDetailActivity extends BaseActivity implements PlanningDisplaySpecEventDetailView,
        CompoundButton.OnCheckedChangeListener, View.OnClickListener{
    public static final String INTENT_NAME_EVENT = Constant.PACKAGE_NAME + "Event";

    @Bind(R.id.tv_planning_display_event_detail_delete) TextView deleteTv;
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
        mToolbar.setTitle(R.string.planning_display_spec_event_detail_title);

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
        vh.greekAlphabetSwitch.setOnCheckedChangeListener(this);
        deleteTv.setOnClickListener(this);
    }

    @Override
    public void fillViewWithData() {
        vh.setData(vo);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_planning_display_event_detail_is_show_sequence:
                vh.showSequenceSwitch.setChecked(isChecked);
                vo.event.setIsShowEventSequence(isChecked);
                break;
            case R.id.switch_planning_display_event_detail_is_greek_alphabet_marked:
                vh.greekAlphabetSwitch.setChecked(isChecked);
                vo.event.setIsGreekAlphabetMarked(isChecked);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_planning_display_event_detail_delete:
                mPresenter.deleteThisEventAndProcessRelated(vo.event);
                break;
        }
    }



    @Override
    public void exitThisView() {
        finish();
    }
}
