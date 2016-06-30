package link.ebbinghaus.planning.ui.view.planning.build.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.db.chart.Tools;
import com.db.chart.model.BarSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.BarChartView;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.easing.BounceEase;
import com.yurikami.lib.base.BaseFragment;
import com.yurikami.lib.model.Datetime;
import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.StringUtils;
import com.yurikami.lib.widget.SingleSelectDialog;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.constant.Constant;
import link.ebbinghaus.planning.app.constant.config.entity.FastTemplateConfig;
import link.ebbinghaus.planning.app.constant.config.entity.LearningEventGroupConfig;
import link.ebbinghaus.planning.app.constant.module.PlanningBuildConstant;
import link.ebbinghaus.planning.core.model.local.po.DefaultInputValue;
import link.ebbinghaus.planning.core.model.local.po.EventGroup;
import link.ebbinghaus.planning.core.model.local.po.EventSubtype;
import link.ebbinghaus.planning.core.model.local.po.FastTemplate;
import link.ebbinghaus.planning.core.model.local.vo.planning.build.InputEventVo;
import link.ebbinghaus.planning.ui.presenter.planning.build.PlanningBuildSpecificPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.build.impl.PlanningBuildSpecificPresenterImpl;
import link.ebbinghaus.planning.ui.view.common.activity.CommonSelectActivity;
import link.ebbinghaus.planning.ui.view.planning.build.PlanningBuildSpecificView;
import link.ebbinghaus.planning.ui.view.planning.build.activity.PlanningBuildActivity;
import link.ebbinghaus.planning.ui.viewholder.planning.build.SpecificViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningBuildSpecificFragment extends BaseFragment implements PlanningBuildSpecificView,
        View.OnClickListener, CompoundButton.OnCheckedChangeListener,
        PlanningBuildActivity.OnBuildMenuItemClickListener, PlanningBuildActivity.OnEventSaveListener,
        RadialTimePickerDialogFragment.OnTimeSetListener, CalendarDatePickerDialogFragment.OnDateSetListener,
        SingleSelectDialog.OnSelectListener {

    //requestCode(同时也将会被用作在CommonSelectActivity识别类型的Flag)
    public static final int FLAG_EVENT_SUBTYPE = R.id.tv_planning_build_subtype & CommonSelectActivity.FLAG_MASK;
    public static final int FLAG_FAST_TEMPLATE = R.id.btn_planning_build_fast_template & CommonSelectActivity.FLAG_MASK;
    public static final int FLAG_EVENT_GROUP = R.id.tv_planning_build_event_group & CommonSelectActivity.FLAG_MASK;

    /**
     * Intent的name
     */
    public static final String INTENT_NAME_FAST_TEMPLATE_TYPE = Constant.PACKAGE_NAME + ".FastTemplateType";
    private PlanningBuildSpecificPresenter mPresenter;

    @Bind(R.id.bcv_planning_build_chart) BarChartView mChartBcv;
    private SpecificViewHolder vh;
    private RadialTimePickerDialogFragment mRadialTimePicker;
    private CalendarDatePickerDialogFragment mCalendarDatePicker;
    private SingleSelectDialog mSingleSelectDialog;
    private Snackbar mSnackbar;
    /**
     * 用来记录具体计划输入值的变量
     */
    private InputEventVo mInputEvent = new InputEventVo();
    Datetime mNowDate = Datetime.buildTodayDate();


    /**
     * 控制输入面板显示的计划时普通型还是学习型<br>
     * false: 普通型 true:学习型
     */
    private boolean mPanelShowEventType = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_build_specific, container, false);
        ButterKnife.bind(this, v);
        vh = new SpecificViewHolder(v);
        mPresenter = new PlanningBuildSpecificPresenterImpl(this);
        mPresenter.registerListeners();
        mPresenter.getAndSetDefaultInputValues(mInputEvent);
        mPresenter.initChart(mNowDate);
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FLAG_EVENT_SUBTYPE:
                if (resultCode == Activity.RESULT_OK) {
                    EventSubtype eventSubtype = data.getParcelableExtra(CommonSelectActivity.INTENT_NAME_RESULT);
                    mPresenter.configureEventSubtype(eventSubtype);
                } else {
                    EventSubtype eventSubtype = new EventSubtype();
                    eventSubtype.setEventSubtype(getString(R.string.common_none));
                    mPresenter.configureEventSubtype(eventSubtype);
                }
                break;
            case FLAG_FAST_TEMPLATE:
                if (resultCode == Activity.RESULT_OK) {
                    FastTemplate fastTemplate = data.getParcelableExtra(CommonSelectActivity.INTENT_NAME_RESULT);
                    mPresenter.configureDescription(fastTemplate.getTemplate());
                } else {
                    mPresenter.configureDescription(null);
                }
                break;
            case FLAG_EVENT_GROUP:
                if (resultCode == Activity.RESULT_OK) {
                    EventGroup eventGroup = data.getParcelableExtra(CommonSelectActivity.INTENT_NAME_RESULT);
                    mPresenter.configureEventGroup(eventGroup);
                } else {
                    EventGroup eventGroup = new EventGroup();
                    eventGroup.setDescription(getString(R.string.common_none));
                    mPresenter.configureEventGroup(eventGroup);
                }
                break;
        }
    }

    @Override
    public void setListeners() {
        //批量注册form里面控件的监听器
        vh.setOnclickListener(this);
        vh.setOnCheckedChangeListener(this);

        //注册PlanningBuildActivity里面的监听器
        //!省略,由父Activity在Page切换的时候获取子类实例进行注册
        //直接注册的话会导致监听器覆盖
//        ((PlanningBuildActivity)mActivity).setOnBuildMenuItemClickListener(this);
//        ((PlanningBuildActivity)mActivity).setOnEventSaveListener(this);
    }

    @Override
    public InputEventVo getInputEvent() {
        mInputEvent.setDescription(vh.descriptionEt.getText().toString());
        return mInputEvent;
    }

    @Override
    public void setDefaultInputValue(DefaultInputValue defaultInputValue) {
        // step1. 给mInputValue设置获取到的默认值(在presenter里做了大部分)
        mInputEvent.setEventType(mPanelShowEventType ? FastTemplateConfig.TYPE_SPEC_LEARNING : FastTemplateConfig.TYPE_SPEC_NORMAL);
        // step2. 给控件设置默认值
        vh.eventSubtypeTv.setText(getString(R.string.common_none));
        vh.strategyTv.setText(defaultInputValue.getChnStrategy());
        vh.expectedFinishDateTv.setText(DateUtils.currentChnDate());
        vh.remindSwitch.setChecked(defaultInputValue.getIsRemind());
        vh.remindTimeTv.setText(defaultInputValue.getFormatRemindTime());
        vh.sequenceSwitch.setChecked(defaultInputValue.getIsShowEventSequence());
        vh.greekAlphabetSwitch.setChecked(defaultInputValue.getIsGreekAlphabetMarked());
        vh.eventGroupTv.setText(getString(R.string.common_none));
        // step3. 初始化其他控件
        long rt = defaultInputValue.getRemindTime();
        mRadialTimePicker = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(this)
                .setStartTime(DateUtils.hour(rt), DateUtils.minute(rt))
                .setThemeLight();
        mCalendarDatePicker = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(this)
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setPreselectedDate(mNowDate.getYear(), mNowDate.getMonth() - 1, mNowDate.getDay())
                .setDateRange(new MonthAdapter.CalendarDay(System.currentTimeMillis()), null)
                .setThemeLight();
//        mSingleSelectDialog = SingleSelectDialog.newInstance(LearningEventGroupConfig.DESCRIPTIONS_STRATEGY, getString(R.string.planning_build_spec_event_strategy_select_dialog_title));
        mSingleSelectDialog = SingleSelectDialog.newInstance(LearningEventGroupConfig.DESCRIPTIONS_STRATEGY,getString(R.string.planning_build_spec_event_strategy_select_dialog_title),0);
        mSingleSelectDialog.setOnSelectListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSnackbar = Snackbar.make(getView(), getString(R.string.planning_build_spec_description_validate_info), Snackbar.LENGTH_LONG);
    }

    @Override
    public void setBuildPanel() {
        if (mPanelShowEventType) {
            vh.eventTypeTv.setText(getString(R.string.planning_build_spec_normal_event));
            vh.showNormalPanel();
        } else {
            vh.eventTypeTv.setText(getString(R.string.planning_build_spec_learning_event));
            vh.showLearningPanel();
        }
        mPanelShowEventType = !mPanelShowEventType;
        mInputEvent.setEventType(mPanelShowEventType ? FastTemplateConfig.TYPE_SPEC_LEARNING : FastTemplateConfig.TYPE_SPEC_NORMAL);
        refreshChart();
    }

    @Override
    public void setSubtype(EventSubtype result) {
        vh.eventSubtypeTv.setText(result.getEventSubtype());
        mInputEvent.setEventSubtypeId(result.getPkEventSubtypeId());
    }

    @Override
    public void setFastTemplate(String template) {
        vh.descriptionEt.setText(template);
    }

    @Override
    public void selectStrategy() {
        //选择计划方案(实际上是弹出dialog后,最后再下面的onRadioSelected返回结果)
        //FIXME:快速点击时,这里有可能会崩溃?换成singleSelect之后也会吗
        mSingleSelectDialog.show(getFragmentManager(), this.getTag());
    }

    @Override
    public void onSelected(int index, int flag) {
        mInputEvent.setStrategy(index + 1);
        vh.strategyTv.setText(LearningEventGroupConfig.DESCRIPTIONS_STRATEGY[index]);
        mSingleSelectDialog.dismiss();
        refreshChart();
    }

    //TODO:以后扩展成智能宽度选择型
    @Override
    public void setExpectedFinishDate() {
        //设置计划日期的工作实际在下面的onDateSet做了
        mCalendarDatePicker.show(getFragmentManager(), this.getTag());

    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        mInputEvent.setEventExpectedFinishedDate(DateUtils.newDateTimestamp(year, monthOfYear + 1, dayOfMonth));
        vh.expectedFinishDateTv.setText(StringUtils.splice2ChnDate(year, monthOfYear + 1, dayOfMonth));
        dialog.setPreselectedDate(mNowDate.getYear(), mNowDate.getMonth() - 1, mNowDate.getDay());
        refreshChart();
    }

    @Override
    public void setRemind(boolean isChecked) {
        mInputEvent.setIsRemind(isChecked);
        if (isChecked) {
            vh.showRemindTime();
        } else {
            vh.hideRemindTime();
        }
    }

    @Override
    public void setRemindTime() {
        //设置提醒时间的工作实际在下面的onTimeSet做了
        mRadialTimePicker.show(getFragmentManager(), PlanningBuildSpecificFragment.this.getTag());
    }

    @Override
    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
        String hourMinute = hourOfDay + ":" + minute;   // FIXME: 2016/4/24 末尾为0时只显示一个0
        mInputEvent.setRemindTime(DateUtils.getHourMinuteMilliseconds(hourOfDay, minute));
        vh.remindTimeTv.setText(hourMinute);
        mRadialTimePicker.setStartTime(hourOfDay, minute);
    }

    @Override
    public void setSequence(boolean isChecked) {
        mInputEvent.setIsShowEventSequence(isChecked);
    }

    @Override
    public void setGreekAlphabet(boolean isChecked) {
        mInputEvent.setIsGreekAlphabetMarked(isChecked);
    }

    @Override
    public void setEventGroup(EventGroup eventGroup) {
        vh.eventGroupTv.setText(eventGroup.getDescription());
        mInputEvent.setEventGroupId(eventGroup.getPkEventGroupId());
    }

    @Override
    public void renderChart(String[] labels, float[] values, int maxValue, int step, boolean isRerender) {
        if (isRerender) {
            mChartBcv.reset();
        }
        BarSet dataSet = new BarSet(labels, values);
        dataSet.setColor(Color.parseColor("#eb993b"));
        mChartBcv.addData(dataSet);

        mChartBcv.setBarSpacing(Tools.fromDpToPx(3));

        mChartBcv.setXLabels(AxisController.LabelPosition.OUTSIDE)
                .setYLabels(AxisController.LabelPosition.OUTSIDE)
                .setXAxis(false)
                .setYAxis(false);

        mChartBcv.setAxisBorderValues(0, maxValue,step);

        Animation anim = new Animation()
                .setEasing(new BounceEase());

        mChartBcv.show(anim);
    }

    @Override
    public void refreshChart() {
        mPresenter.updateChart(mInputEvent.getEventExpectedFinishedDate(),
                mPanelShowEventType ? LearningEventGroupConfig.STRATEGY[mInputEvent.getStrategy() - 1] : null);
    }

    //回调函数,在这里面执行form的重置工作
    @Override
    public void onEventSavedSuccessfully() {
        mInputEvent = new InputEventVo();
        mPresenter.getAndSetDefaultInputValues(mInputEvent);
        vh.descriptionEt.setText("");
        mSnackbar.setText(getString(R.string.planning_build_spec_saved_successfully_info));
        mSnackbar.show();
        refreshChart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_planning_build_switch_event_type:
                mPresenter.switchBuildPanel();
                break;
            case R.id.tv_planning_build_subtype:
                jumpToSelectActivityForResult(PlanningBuildConstant.TITLE_SELECT_SUBTYPE, FLAG_EVENT_SUBTYPE);
                break;
            case R.id.btn_planning_build_fast_template:
                startActivityForResult(
                        newIntent(CommonSelectActivity.class)
                                .putExtra(INTENT_NAME_FAST_TEMPLATE_TYPE, mPanelShowEventType ? FastTemplateConfig.TYPE_SPEC_LEARNING : FastTemplateConfig.TYPE_SPEC_NORMAL)
                                .putExtra(CommonSelectActivity.INTENT_NAME_TITLE, PlanningBuildConstant.TITLE_SELECT_FAST_TEMPLATE)
                                .putExtra(CommonSelectActivity.INTENT_NAME_FLAG, FLAG_FAST_TEMPLATE),
                        FLAG_FAST_TEMPLATE);
                break;
            case R.id.tv_planning_build_strategy:
                mPresenter.configureStrategy();
                break;
            case R.id.tv_planning_build_expected_finish_date:
                mPresenter.configureExpectedFinishDate();
                break;
            case R.id.tv_planning_build_remind_time:
                mPresenter.configureRemindTime();
                break;
            case R.id.tv_planning_build_event_group:
                jumpToSelectActivityForResult(PlanningBuildConstant.TITLE_SELECT_EVENT_GROUP, FLAG_EVENT_GROUP);
                break;
        }
    }

    private void jumpToSelectActivityForResult(String title, int requestCode) {
        startActivityForResult(
                newIntent(CommonSelectActivity.class)
                        .putExtra(CommonSelectActivity.INTENT_NAME_TITLE, title)
                        .putExtra(CommonSelectActivity.INTENT_NAME_FLAG, requestCode),
                requestCode);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switch_planning_build_is_remind:
                mPresenter.configureRemind(isChecked);
                break;
            case R.id.switch_planning_build_is_show_event_sequence:
                mPresenter.configureSequence(isChecked);
                break;
            case R.id.switch_planning_build_is_greek_alphabet_marked:
                mPresenter.configureGreekAlphabet(isChecked);
                break;
        }
    }

    @Override
    public boolean onBuildMenuClick(InputEventVo inputEvent) {
        //提取页面数据到event中
        //step 1.验证填写完整性
        if (TextUtils.isEmpty(vh.descriptionEt.getText().toString().trim())) {
            mSnackbar.setText(getString(R.string.planning_build_spec_description_validate_info));
            mSnackbar.show();
            return false;
        }
        //step 2.拷贝填入
        inputEvent.copyFrom(getInputEvent());
        return true;
        /*
        event.setLearningEventGroupId(1L);
        event.setEventGroupId(1L);
        event.setDescription("描述测试");
//        event.setSummary("总结测试");
        event.setEventType(1);
        event.setEventSubtypeId(1L);
        event.setEventSequence(1);
        event.setIsShowEventSequence(false);
        event.setCreateTime(System.currentTimeMillis());
        event.setEventExpectedFinishedDate(DateUtils.convertChnDate2Timestamp("2016年3月15日"));
//        event.setEventFinishedTime();
        event.setIsEventFinished(false);
        event.setIsGreekAlphabetMarked(false);
        event.setIsRemind(false);
        event.setRemindTime(-1);
        event.setEventProcess(1);
         */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
