package link.ebbinghaus.planning.view.activity.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder;
import com.codetroopers.betterpickers.numberpicker.NumberPickerDialogFragment;
import com.yurikami.lib.base.BaseActivity;

import java.math.BigDecimal;
import java.math.BigInteger;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.constant.Constant;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;
import link.ebbinghaus.planning.presenter.PlanningDoneFinishPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningDoneFinishPresenterImpl;
import link.ebbinghaus.planning.view.activity.PlanningDoneFinishView;
import link.ebbinhaus.planning.R;

public class PlanningDoneFinishActivity extends BaseActivity implements PlanningDoneFinishView,
        NumberPickerDialogFragment.NumberPickerDialogHandlerV2,View.OnClickListener {

    public static final String INTENT_NAME_EVENT = Constant.PACKAGE_NAME + "Event";

    @Bind(R.id.rl_planning_done_finish_learning_duration) RelativeLayout learningDurationRl;
    @Bind(R.id.tv_planning_done_finish_learning_duration) TextView learningDurationTv;
    @Bind(R.id.rl_planning_done_finish_efficiency) RelativeLayout efficiencyRl;
    @Bind(R.id.tv_planning_done_finish_efficiency) TextView efficiencyTv;
    @Bind(R.id.rl_planning_done_finish_understanding_degree) RelativeLayout understandingDegreeRl;
    @Bind(R.id.tv_planning_done_finish_understanding_degree) TextView understandingDegreeTv;
    @Bind(R.id.et_planning_done_finish_summary) EditText summaryEt;

    private PlanningDoneFinishPresenter mPresenter;
    private NumberPickerBuilder mNumberPicker;
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
     * @param context 启动者上下文
     * @param event 传送的数据
     */
    public static void startAction(Context context,Event event){
        Intent intent = new Intent(context,PlanningDoneFinishActivity.class);
        intent.putExtra(INTENT_NAME_EVENT, event);
        context.startActivity(intent);
    }

    @Override
    public Event getIntentData() {
        Intent intent = getIntent();
        mEvent = intent.getParcelableExtra(INTENT_NAME_EVENT);
        mLearningEventGroup.setPkLearningEventGroupId(mEvent.getLearningEventGroupId());
        return mEvent;
    }

    @Override
    public void setViewByType(boolean type) {
        if (type){
            //显示完整,即默认,什么也不做
        }else {
            //只显示总结框
            learningDurationRl.setVisibility(View.GONE);
            efficiencyRl.setVisibility(View.GONE);
            understandingDegreeRl.setVisibility(View.GONE);
        }
    }

    @Override
    public void registerListenerByType(boolean type) {
        if (type){
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
    public void onDialogNumberSet(int reference, BigInteger number, double decimal, boolean isNegative, BigDecimal fullNumber) {
        int minute = number.intValue();
        learningDurationTv.setText(getString(R.string.planning_done_finish_learning_time, minute));
        mLearningEventGroup.setWorkload(minute);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_planning_done_finish_learning_duration:
                mNumberPicker.show();
                break;
            case R.id.rl_planning_done_finish_efficiency:
                break;
            case R.id.rl_planning_done_finish_understanding_degree:
                break;
        }
    }
}
