package link.ebbinghaus.planning.ui.view.planning.done.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder;
import com.codetroopers.betterpickers.numberpicker.NumberPickerDialogFragment;
import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.util.LogUtils;
import com.yurikami.lib.util.MenuTint;
import com.yurikami.lib.widget.SingleSelectDialog;

import java.math.BigDecimal;
import java.math.BigInteger;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.common.constant.Constant;
import link.ebbinghaus.planning.common.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.common.constant.config.entity.LearningEventGroupConfig;
import link.ebbinghaus.planning.common.constant.model.LearningEventGroupConstant;
import link.ebbinghaus.planning.common.util.CommonUtils;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.model.local.po.LearningEventGroup;
import link.ebbinghaus.planning.ui.presenter.planning.done.PlanningDoneFinishPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.done.impl.PlanningDoneFinishPresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.done.PlanningDoneFinishView;
import link.ebbinghaus.planning.R;

public class PlanningDoneFinishActivity extends BaseActivity implements PlanningDoneFinishView,
        NumberPickerDialogFragment.NumberPickerDialogHandlerV2,
        View.OnClickListener, SingleSelectDialog.OnSelectListener {

    public static final int FLAG_EFFICIENCY = 0;
    public static final int FLAG_UNDERSTANDING_DEGREE = 1;

    public static final String INTENT_NAME_EVENT = Constant.PACKAGE_NAME + "Event";

    @Bind(R.id.rl_planning_done_finish_learning_duration) RelativeLayout learningDurationRl;
    @Bind(R.id.tv_planning_done_finish_learning_duration) TextView learningDurationTv;
    @Bind(R.id.rl_planning_done_finish_efficiency) RelativeLayout efficiencyRl;
    @Bind(R.id.tv_planning_done_finish_efficiency) TextView efficiencyTv;
    @Bind(R.id.rl_planning_done_finish_understanding_degree) RelativeLayout understandingDegreeRl;
    @Bind(R.id.tv_planning_done_finish_understanding_degree) TextView understandingDegreeTv;
    @Bind(R.id.et_planning_done_finish_summary) EditText summaryEt;
    @Bind(R.id.tb_common_head) Toolbar mToolbar;

    private PlanningDoneFinishPresenter mPresenter;
    private NumberPickerBuilder mNumberPicker;
    private SingleSelectDialog mSingleSelectDialog;
    //用于存放页面使用数据模型
    private Event mEvent;
    private LearningEventGroup mLearningEventGroup = new LearningEventGroup();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_done_finish);
        ButterKnife.bind(this);
        mPresenter = new PlanningDoneFinishPresenterImpl(this);
        mPresenter.initPlanningDoneFinish();
    }

    /**
     * 携带参数启动本Activity
     *
     * @param context 启动者上下文
     * @param event   传送的数据
     */
    public static void startAction(Context context, Event event) {
        Intent intent = new Intent(context, PlanningDoneFinishActivity.class);
        intent.putExtra(INTENT_NAME_EVENT, event);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.planning_done_finish, menu);
        MenuTint.colorIcons(this, menu, getResources().getColor(R.color.md_white_1000));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_planning_done_finish && validFinishForm()) {
            LogUtils.d(TAG, "Finish item was selected!");
            mPresenter.finishEvent(mEvent, mLearningEventGroup);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void exitPlanningDoneView() {
        finish();
    }

    @Override
    public Event getIntentData() {
        Intent intent = getIntent();
        mEvent = intent.getParcelableExtra(INTENT_NAME_EVENT);
        return mEvent;
    }

    @Override
    public void setDefaultLearningEventGroupValues() {
        //给学习计划组设置默认值
        mLearningEventGroup.setPkLearningEventGroupId(mEvent.getLearningEventGroupId());
        mLearningEventGroup.setEfficiency(LearningEventGroupConfig.EFFICIENCY_EXCELLENT);
        mLearningEventGroup.setUnderstandingDegree(LearningEventGroupConfig.UNDERSTANDING_DEGREE_TOTALLY);
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setViewByType(boolean type) {
        if (type) {
            //显示完整,即默认,什么也不做
        } else {
            //只显示总结框
            learningDurationRl.setVisibility(View.GONE);
            efficiencyRl.setVisibility(View.GONE);
            understandingDegreeRl.setVisibility(View.GONE);
        }
    }

    @Override
    public void registerListenerByType(boolean type) {
        if (type) {
            learningDurationRl.setOnClickListener(this);
            efficiencyRl.setOnClickListener(this);
            understandingDegreeRl.setOnClickListener(this);
        }
    }


    @Override
    public void initWidget() {
        mNumberPicker = new NumberPickerBuilder()
                .setFragmentManager(getSupportFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment_Light)
                .setMinNumber(new BigDecimal(10))
                .setMaxNumber(new BigDecimal(1440))
                .setPlusMinusVisibility(View.GONE)
                .setDecimalVisibility(View.GONE)
                .addNumberPickerDialogHandler(this);
    }

    @Override
    public void setEfficiencyDialog() {
        mSingleSelectDialog = SingleSelectDialog.newInstance(this,
                LearningEventGroupConstant.EFFICIENCY,
                R.string.planning_done_finish_dialog_efficiency_title,
                FLAG_EFFICIENCY);
        mSingleSelectDialog.setOnSelectListener(this);
    }

    @Override
    public void setUnderstandingDegreeDialog() {
        mSingleSelectDialog = SingleSelectDialog.newInstance(this,
                LearningEventGroupConstant.UNDERSTANDING_DEGREE,
                R.string.planning_done_finish_dialog_understand_degree_title,
                FLAG_UNDERSTANDING_DEGREE);
        mSingleSelectDialog.setOnSelectListener(this);
    }

    @Override
    public boolean validFinishForm() {
        if (mEvent.getEventType() == EventConfig.TYPE_SPEC_LEARNING && mEvent.getEventSequence() == 1) {
            if (mLearningEventGroup.getLearningDuration() == null) {
                CommonUtils.showLongToast(getString(R.string.planning_done_finish_learning_duration_validation_message));
                return false;
            }
        }
        return true;
    }


    @Override
    public void onDialogNumberSet(int reference, BigInteger number, double decimal, boolean isNegative, BigDecimal fullNumber) {
        int minute = number.intValue();
        learningDurationTv.setText(getString(R.string.planning_done_finish_learning_time, minute));
        mLearningEventGroup.setLearningDuration(minute);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_planning_done_finish_learning_duration:
                mNumberPicker.show();
                break;
            case R.id.rl_planning_done_finish_efficiency:
                mPresenter.buildEfficiencyDialog();
                mSingleSelectDialog.show(this);
                break;
            case R.id.rl_planning_done_finish_understanding_degree:
                mPresenter.buildUnderstandingDegreeDialog();
                mSingleSelectDialog.show(this);
                break;
        }
    }

    @Override
    public void onSelected(int index, int flag) {
        switch (flag) {
            case FLAG_EFFICIENCY:
                efficiencyTv.setText(LearningEventGroupConstant.EFFICIENCY[index]);
                mLearningEventGroup.setEfficiency(LearningEventGroupConfig.EFFICIENCY[index]);
                mSingleSelectDialog.dismiss();
                break;
            case FLAG_UNDERSTANDING_DEGREE:
                understandingDegreeTv.setText(LearningEventGroupConstant.UNDERSTANDING_DEGREE[index]);
                mLearningEventGroup.setUnderstandingDegree(LearningEventGroupConfig.UNDERSTANDING_DEGREE[index]);
                mSingleSelectDialog.dismiss();
                break;
        }
    }
}
