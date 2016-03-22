package link.ebbinghaus.planning.view.fragment.impl;


import android.content.Intent;
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
import com.yurikami.lib.base.BaseFragment;
import com.yurikami.lib.entity.Datetime;
import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.StringUtils;
import com.yurikami.lib.widget.RadioSelectDialog;

import java.util.Calendar;

import link.ebbinghaus.planning.custom.constant.Constant;
import link.ebbinghaus.planning.custom.constant.entity.EventConstant;
import link.ebbinghaus.planning.custom.constant.entity.FastTemplateConstant;
import link.ebbinghaus.planning.custom.constant.module.PlanningBuildConstant;
import link.ebbinghaus.planning.custom.viewholder.planning.build.SpecificViewHolder;
import link.ebbinghaus.planning.model.entity.po.DefaultInputValue;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;
import link.ebbinghaus.planning.model.entity.vo.InputEventVo;
import link.ebbinghaus.planning.presenter.PlanningBuildSpecificPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningBuildSpecificPresenterImpl;
import link.ebbinghaus.planning.view.activity.impl.CommonSelectActivity;
import link.ebbinghaus.planning.view.activity.impl.PlanningBuildActivity;
import link.ebbinghaus.planning.view.fragment.PlanningBuildSpecificView;
import link.ebbinhaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningBuildSpecificFragment extends BaseFragment implements PlanningBuildSpecificView,
        View.OnClickListener,CompoundButton.OnCheckedChangeListener,
        PlanningBuildActivity.OnBuildMenuItemClickListener,
        RadialTimePickerDialogFragment.OnTimeSetListener,
        CalendarDatePickerDialogFragment.OnDateSetListener, RadioSelectDialog.OnRadioSelectListener {

    //requestCode
    public static final int FLAG_EVENT_SUBTYPE = R.id.tv_planning_build_subtype & CommonSelectActivity.FLAG_MASK;
    public static final int FLAG_FAST_TEMPLATE = R.id.btn_planning_build_fast_template & CommonSelectActivity.FLAG_MASK;
    public static final int FLAG_EVENT_GROUP = R.id.tv_planning_build_event_group & CommonSelectActivity.FLAG_MASK;

    /** Intent的name */
    public static final String INTENT_NAME_FAST_TEMPLATE_TYPE = Constant.PACKAGE_NAME + ".FastTemplateType";

    private PlanningBuildSpecificPresenter mPlanningBuildSpecificPresenter;

    private SpecificViewHolder vh;
    private LayoutInflater mLayoutInflater;
    private RadialTimePickerDialogFragment mRadialTimePicker;
    private CalendarDatePickerDialogFragment mCalendarDatePicker;
    private RadioSelectDialog mRadioSelectDialog;
    private Snackbar mSnackbar;
    /** 用来记录具体计划输入值的变量 */
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
        this.mLayoutInflater = inflater;
        View v = mLayoutInflater.inflate(R.layout.fragment_planning_build_specific, container, false);
        mPlanningBuildSpecificPresenter = new PlanningBuildSpecificPresenterImpl(this);
        vh = new SpecificViewHolder(v);
        vh.setOnclickListener(this);
        vh.setOnCheckedChangeListener(this);

        //注册OnBuildMenuItemClickListener监听器
        ((PlanningBuildActivity)mActivity).setOnBuildMenuItemClickListener(this);

        mPlanningBuildSpecificPresenter.getAndSetDefaultInputValues(mInputEvent);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case FLAG_EVENT_SUBTYPE:
                if (resultCode == mActivity.RESULT_OK) {
                    EventSubtype eventSubtype = data.getParcelableExtra(CommonSelectActivity.INTENT_NAME_RESULT);
                    mPlanningBuildSpecificPresenter.configureEventSubtype(eventSubtype);
                }else {
                    EventSubtype eventSubtype = new EventSubtype();
                    eventSubtype.setEventSubtype(getString(R.string.common_none));
                    mPlanningBuildSpecificPresenter.configureEventSubtype(eventSubtype);
                }
                break;
            case FLAG_FAST_TEMPLATE:
                if (resultCode == mActivity.RESULT_OK) {
                    FastTemplate fastTemplate = data.getParcelableExtra(CommonSelectActivity.INTENT_NAME_RESULT);
                    mPlanningBuildSpecificPresenter.configureDescription(fastTemplate.getTemplate());
                }else {
                    mPlanningBuildSpecificPresenter.configureDescription(null);
                }
                break;
            case FLAG_EVENT_GROUP:
                if (resultCode == mActivity.RESULT_OK) {
                    EventGroup eventGroup = data.getParcelableExtra(CommonSelectActivity.INTENT_NAME_RESULT);
                    mPlanningBuildSpecificPresenter.configureEventGroup(eventGroup);
                }else {
                    EventGroup eventGroup = new EventGroup();
                    eventGroup.setDescription(getString(R.string.common_none));
                    mPlanningBuildSpecificPresenter.configureEventGroup(eventGroup);
                }
                break;
        }
    }

    @Override
    public Event getInputEvent() {
        mInputEvent.setDescription(vh.descriptionEt.getText().toString());
        return mInputEvent;
    }

    @Override
    public void setDefaultInputValue(DefaultInputValue defaultInputValue) {
        // step1. 给mInputValue设置获取到的默认值(在presenter里做了大部分)
        mInputEvent.setEventType(mPanelShowEventType ? FastTemplateConstant.SPEC_LEARNING_TYPE : FastTemplateConstant.SPEC_NORMAL_TYPE);
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
                .setDateRange(new MonthAdapter.CalendarDay(), null)
                .setThemeLight();
        mRadioSelectDialog = RadioSelectDialog.newInstance(EventConstant.STRATEGYS, getString(R.string.planning_build_spec_event_strategy_select_dialog_title));
        mRadioSelectDialog.setOnRadioSelectListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSnackbar = Snackbar.make(getView(), getString(R.string.planning_build_spec_description_validate_info), Snackbar.LENGTH_LONG);
    }

    @Override
    public void setBuildPanel() {
        if(mPanelShowEventType) {
            vh.eventTypeTv.setText(getString(R.string.planning_build_spec_normal_event));
            vh.showNormalPanel();
        }else {
            vh.eventTypeTv.setText(getString(R.string.planning_build_spec_learning_event));
            vh.showLearningPanel();
        }
        mPanelShowEventType = !mPanelShowEventType;
        mInputEvent.setEventType(mPanelShowEventType ? FastTemplateConstant.SPEC_LEARNING_TYPE : FastTemplateConstant.SPEC_NORMAL_TYPE);
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
        mRadioSelectDialog.show(getFragmentManager(),this.getTag());
    }
    @Override
    public void onRadioSelected(int index) {
        mInputEvent.setStrategy(index + 1);
        vh.strategyTv.setText(EventConstant.STRATEGYS[index]);
        mRadioSelectDialog.dismiss();
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
    }

    @Override
    public void setRemind(boolean isChecked) {
        mInputEvent.setIsRemind(isChecked);
        if(isChecked) {
            vh.showRemindTime();
        }else {
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
        String hourMinute = hourOfDay + ":" + minute;
        mInputEvent.setRemindTime(DateUtils.convertHourMinute2Timestamp(hourMinute));
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_planning_build_switch_event_type:
                mPlanningBuildSpecificPresenter.switchBuildPanel();
                break;
            case R.id.tv_planning_build_subtype:
                jumpToSelectActivityForResult(PlanningBuildConstant.TITLE_SELECT_SUBTYPE , FLAG_EVENT_SUBTYPE);
                break;
            case R.id.btn_planning_build_fast_template:
                startActivityForResult(
                        newIntent(CommonSelectActivity.class)
                                .putExtra(INTENT_NAME_FAST_TEMPLATE_TYPE, mPanelShowEventType ? FastTemplateConstant.SPEC_LEARNING_TYPE : FastTemplateConstant.SPEC_NORMAL_TYPE)
                                .putExtra(CommonSelectActivity.INTENT_NAME_TITLE, PlanningBuildConstant.TITLE_SELECT_FAST_TEMPLATE)
                                .putExtra(CommonSelectActivity.INTENT_NAME_FLAG, FLAG_FAST_TEMPLATE),
                        FLAG_FAST_TEMPLATE);
                break;
            case R.id.tv_planning_build_strategy:
                mPlanningBuildSpecificPresenter.configureStrategy();
                break;
            case R.id.tv_planning_build_expected_finish_date:
                mPlanningBuildSpecificPresenter.configureExpectedFinishDate();
                break;
            case R.id.tv_planning_build_remind_time:
                mPlanningBuildSpecificPresenter.configureRemindTime();
                break;
            case R.id.tv_planning_build_event_group:
                jumpToSelectActivityForResult(PlanningBuildConstant.TITLE_SELECT_EVENT_GROUP ,FLAG_EVENT_GROUP);
                break;
        }
    }
    private void jumpToSelectActivityForResult(String title, int requestCode){
        startActivityForResult(
                newIntent(CommonSelectActivity.class)
                        .putExtra(CommonSelectActivity.INTENT_NAME_TITLE, title)
                        .putExtra(CommonSelectActivity.INTENT_NAME_FLAG, requestCode),
                requestCode);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_planning_build_is_remind:
                mPlanningBuildSpecificPresenter.configureRemind(isChecked);
                break;
            case R.id.switch_planning_build_is_show_event_sequence:
                mPlanningBuildSpecificPresenter.configureSequence(isChecked);
                break;
            case R.id.switch_planning_build_is_greek_alphabet_marked:
                mPlanningBuildSpecificPresenter.configureGreekAlphabet(isChecked);
                break;
        }
    }

    @Override
    public boolean onBuildMenuClick(Event event) {
        //提取页面数据到event中
        //step 1.验证填写完整性
        if (TextUtils.isEmpty(vh.descriptionEt.getText().toString().trim())) {
            mSnackbar.show();
            return false;
        }
        //step 2.填入event
        event.copyFrom(getInputEvent());
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

}
