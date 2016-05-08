package link.ebbinghaus.planning.ui.view.setting.fragment;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder;
import com.codetroopers.betterpickers.numberpicker.NumberPickerDialogFragment;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.yurikami.lib.base.BaseFragment;
import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.widget.RadioSelectDialog;

import java.math.BigDecimal;
import java.math.BigInteger;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.constant.config.entity.LearningEventGroupConfig;
import link.ebbinghaus.planning.app.constant.model.LearningEventGroupConstant;
import link.ebbinghaus.planning.core.model.local.vo.SettingsVo;
import link.ebbinghaus.planning.ui.presenter.setting.SettingsPresenter;
import link.ebbinghaus.planning.ui.presenter.setting.impl.SettingsPresenterImpl;
import link.ebbinghaus.planning.ui.view.setting.SettingsView;

public class SettingsFragment extends BaseFragment implements SettingsView,
        CompoundButton.OnCheckedChangeListener,View.OnClickListener,
        RadialTimePickerDialogFragment.OnTimeSetListener,
        RadioSelectDialog.OnRadioSelectListener,
        NumberPickerDialogFragment.NumberPickerDialogHandlerV2{

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    //计划制定
    @Bind(R.id.tv_settings_planning_build_strategy) TextView planningBuildStrategyTv;
    @Bind(R.id.tv_settings_planning_build_max_width) TextView planningBuildMaxWidthTv;
    @Bind(R.id.switch_settings_planning_build_is_remind) Switch planningBuildIsRemindSwitch;
    @Bind(R.id.tv_settings_planning_build_remind_time) TextView planningBuildRemindTimeTv;
    @Bind(R.id.switch_settings_planning_build_is_show_event_sequence) Switch planningBuildIsShowEventSequenceSwitch;
    @Bind(R.id.switch_settings_planning_build_is_greek_alphabet_marked) Switch planningBuildIsGreekAlphabetMarkedSwitch;
    private RadialTimePickerDialogFragment mRadialTimePicker;
    private NumberPickerBuilder mNumberPicker;
    private RadioSelectDialog mRadioSelectDialog;

    private SettingsPresenter mPresenter;

    private SettingsVo settings = new SettingsVo();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, v);
        init();

        return v;
    }

    private void init() {
        mPresenter = new SettingsPresenterImpl(this);

        mActivity.setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.settings_title);

        mPresenter.initViewData(settings);
        planningBuildStrategyTv.setOnClickListener(this);
        planningBuildMaxWidthTv.setOnClickListener(this);
        planningBuildIsRemindSwitch.setOnCheckedChangeListener(this);
        planningBuildRemindTimeTv.setOnClickListener(this);
        planningBuildIsShowEventSequenceSwitch.setOnCheckedChangeListener(this);
        planningBuildIsGreekAlphabetMarkedSwitch.setOnCheckedChangeListener(this);
        long rt = settings.defaultInputValue.getRemindTime();
        mRadialTimePicker = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(this)
                .setStartTime(DateUtils.hour(rt), DateUtils.minute(rt))
                .setThemeLight();
        mRadioSelectDialog = RadioSelectDialog.newInstance(LearningEventGroupConfig.DESCRIPTIONS_STRATEGY, getString(R.string.planning_build_spec_event_strategy_select_dialog_title));
        mRadioSelectDialog.setOnRadioSelectListener(this);
        mNumberPicker = new NumberPickerBuilder()
                .setFragmentManager(getFragmentManager())
                .setStyleResId(R.style.BetterPickersDialogFragment_Light)
                .setMinNumber(new BigDecimal(1))
                .setMaxNumber(new BigDecimal(100))
                .setPlusMinusVisibility(View.GONE)
                .setDecimalVisibility(View.GONE)
                .addNumberPickerDialogHandler(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setInitData() {
        planningBuildStrategyTv.setText(LearningEventGroupConstant.STRATEGY[settings.defaultInputValue.getStrategy() - 1]);
        planningBuildMaxWidthTv.setText(settings.defaultInputValue.getMaxWidth() + "");
        planningBuildIsRemindSwitch.setChecked(settings.defaultInputValue.getIsRemind());
        planningBuildRemindTimeTv.setText(DateUtils.formatChinaTimestamp2HourMinute(settings.defaultInputValue.getRemindTime()));
        planningBuildIsShowEventSequenceSwitch.setChecked(settings.defaultInputValue.getIsShowEventSequence());
        planningBuildIsGreekAlphabetMarkedSwitch.setChecked(settings.defaultInputValue.getIsGreekAlphabetMarked());
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.saveViewData(settings);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_settings_planning_build_is_remind:
                settings.defaultInputValue.setIsRemind(isChecked);
                break;
            case R.id.switch_settings_planning_build_is_show_event_sequence:
                settings.defaultInputValue.setIsShowEventSequence(isChecked);
                break;
            case R.id.switch_settings_planning_build_is_greek_alphabet_marked:
                settings.defaultInputValue.setIsGreekAlphabetMarked(isChecked);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_settings_planning_build_strategy:
                mRadioSelectDialog.show(getFragmentManager(),getTag()); // FIXME: 2016/4/24 快速点击可能会崩溃
                break;
            case R.id.tv_settings_planning_build_max_width:
                mNumberPicker.show();
                break;
            case R.id.tv_settings_planning_build_remind_time:
                mRadialTimePicker.show(getFragmentManager(),getTag());
                break;
        }
    }

    @Override
    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
        String hourMinute = hourOfDay + ":" + minute;   // FIXME: 2016/4/24 末尾为0时只显示一个0
        settings.defaultInputValue.setRemindTime(DateUtils.getHourMinuteMilliseconds(hourOfDay,minute));
        planningBuildRemindTimeTv.setText(hourMinute);
        mRadialTimePicker.setStartTime(hourOfDay, minute);
    }


    @Override
    public void onRadioSelected(int index) {
        settings.defaultInputValue.setStrategy(index + 1);
        planningBuildStrategyTv.setText(LearningEventGroupConfig.DESCRIPTIONS_STRATEGY[index]);
        mRadioSelectDialog.dismiss();
    }

    @Override
    public void onDialogNumberSet(int reference, BigInteger number, double decimal, boolean isNegative, BigDecimal fullNumber) {
        settings.defaultInputValue.setMaxWidth(number.intValue());
        planningBuildMaxWidthTv.setText(number.intValue() + "");
    }
}
