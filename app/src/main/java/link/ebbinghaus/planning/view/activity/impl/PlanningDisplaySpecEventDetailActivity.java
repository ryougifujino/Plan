package link.ebbinghaus.planning.view.activity.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.yurikami.lib.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.constant.Constant;
import link.ebbinghaus.planning.custom.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.custom.util.CommonUtils;
import link.ebbinghaus.planning.custom.viewholder.planning.display.SpecEventDetailViewHolder;
import link.ebbinghaus.planning.model.entity.vo.planning.display.SpecEventDetailVo;
import link.ebbinghaus.planning.presenter.PlanningDisplaySpecEventDetailPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningDisplaySpecEventDetailPresenterImpl;
import link.ebbinghaus.planning.view.activity.PlanningDisplaySpecEventDetailView;
import link.ebbinhaus.planning.R;

/**
 * 具体计划详情的显示页面
 */
public class PlanningDisplaySpecEventDetailActivity extends BaseActivity implements PlanningDisplaySpecEventDetailView,
        CompoundButton.OnCheckedChangeListener, View.OnClickListener{
    public static final String INTENT_NAME_EVENT = Constant.PACKAGE_NAME + "Event";

    @Bind(R.id.tv_planning_display_event_detail_delete) TextView deleteTv;
    private PlanningDisplaySpecEventDetailPresenter mPresenter;
    //model and view
    private SpecEventDetailVo vo = new SpecEventDetailVo();
    private SpecEventDetailViewHolder vh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_display_spec_event_detail);
        ButterKnife.bind(this);
        vh = new SpecEventDetailViewHolder(this);
        mPresenter = new PlanningDisplaySpecEventDetailPresenterImpl(this);
        mPresenter.initSpecEventDetail(vo);
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
        if (v.getId() == R.id.tv_planning_display_event_detail_delete){
            CommonUtils.showLongToast("删除逻辑");
        }
    }
}
