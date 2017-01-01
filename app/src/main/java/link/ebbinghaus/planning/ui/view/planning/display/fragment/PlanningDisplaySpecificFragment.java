package link.ebbinghaus.planning.ui.view.planning.display.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;
import com.yurikami.lib.base.BaseFragment;
import com.yurikami.lib.model.Datetime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.core.model.local.sys.Tab;
import link.ebbinghaus.planning.ui.adapter.SimpleFragmentPagerAdapter;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplaySpecificPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.display.impl.PlanningDisplaySpecificPresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplaySpecificView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningDisplaySpecificFragment extends BaseFragment implements PlanningDisplaySpecificView,
        ViewPager.OnPageChangeListener, View.OnClickListener, CalendarDatePickerDialogFragment.OnDateSetListener, View.OnLongClickListener {

    private PlanningDisplaySpecificPresenter mPresenter;

    @Bind(R.id.vp_planning_display_specific) ViewPager mViewPager;
    @Bind(R.id.tl_planning_display_specific) TabLayout mTabLayout;
    @Bind(R.id.tv_planning_display_specific_date_selector) TextView mDateSelectorTv;
    @Bind(R.id.iv_planning_display_specific_previous_month) ImageView previousMonthIv;
    @Bind(R.id.iv_planning_display_specific_next_month) ImageView nextMonthIv;
    private Datetime mNowDate = Datetime.buildTodayDate();
    private int mViewPagerPosition;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private List<OnDateSelectorChangeListener> mOnDateSelectorChangeListeners = new ArrayList<>();
    private CalendarDatePickerDialogFragment mCalendarDatePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_specific, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new PlanningDisplaySpecificPresenterImpl(this);

        mViewPager.addOnPageChangeListener(this);
        mPresenter.configureRelatedViewPagerTabLayout();

        configureDateSelector();
        mCalendarDatePicker = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(this)
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setPreselectedDate(mNowDate.getYear(), mNowDate.getMonth() - 1, mNowDate.getDay())
                .setDateRange(new MonthAdapter.CalendarDay(0), null)
                .setThemeLight();
        previousMonthIv.setOnClickListener(this);
        nextMonthIv.setOnClickListener(this);

        return v;
    }

    private void configureDateSelector() {
        mDateSelectorTv.setText(String.format(mActivity.getResources().getString(
                R.string.common_date),
                mNowDate.getYear(), mNowDate.getMonth(), mNowDate.getDay()));
        mDateSelectorTv.setOnClickListener(this);
        mDateSelectorTv.setOnLongClickListener(this);
    }

    public void setToolbarDate() {
        if (nowVPFragment() instanceof OnDateSelectorChangeListener) {
            mCalendarDatePicker.show(getChildFragmentManager(), this.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.tv_planning_display_specific_date_selector:
                onDateSet(Datetime.buildTodayDate());
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_planning_display_specific_date_selector:
                setToolbarDate();
                break;
            case R.id.iv_planning_display_specific_previous_month:
                onDateSet(mViewPagerPosition == 0 ?
                        mNowDate.setMonth(mNowDate.getMonth() - 1) :
                        mNowDate.setDay(mNowDate.getDay() - 7));
                break;
            case R.id.iv_planning_display_specific_next_month:
                onDateSet(mViewPagerPosition == 0 ?
                        mNowDate.setMonth(mNowDate.getMonth() + 1) :
                        mNowDate.setDay(mNowDate.getDay() + 7));
                break;
        }
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog,
                          int year, int monthOfYear, int dayOfMonth) {
        mNowDate.setYear(year).setMonth(monthOfYear + 1).setDay(dayOfMonth);
        for (OnDateSelectorChangeListener listener : mOnDateSelectorChangeListeners) {
            if (listener != null) {
                listener.onDateChanged(mNowDate);
            }
        }
        mDateSelectorTv.setText(String.format(getString(R.string.common_date),
                year, monthOfYear + 1, dayOfMonth));
        //TODO:可以有一个全局变量的设置控制重新开启是今天还是上一次的日期
        dialog.setPreselectedDate(mNowDate.getYear(), mNowDate.getMonth() - 1, mNowDate.getDay());
    }

    private void onDateSet(Datetime datetime){
        if (datetime.getMonth() > 12 || datetime.getMonth() < 1
                || datetime.getDay() > 28 || datetime.getDay() < 1)
            datetime.valid();
        onDateSet(mCalendarDatePicker,
                datetime.getYear(),datetime.getMonth() - 1,datetime.getDay());
    }

    @Override
    public void bindViewPagerToTabLayout(List<Tab> tabs) {
        mFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getChildFragmentManager(), mActivity, tabs);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
    @Override
    public void onPageSelected(int position) {
        mViewPagerPosition = position;
        int visibility = position == 2 ? View.GONE : View.VISIBLE;
        mDateSelectorTv.setVisibility(visibility);
        nextMonthIv.setVisibility(visibility);
        previousMonthIv.setVisibility(visibility);
    }
    @Override
    public void onPageScrollStateChanged(int state) { }

    /**
     * 获取PlanningDisplaySpecificFragment里ViewPager正在显示的Page里的Fragment的实例
     */
    public Fragment nowVPFragment() {
        return nowVPFragment(mViewPager.getId(), mViewPagerPosition);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 定义接口,这个接口里有一个回调函数,当DateSelector改变的时候调用
     */
    public interface OnDateSelectorChangeListener {

        /**
         * 当DateSelector(TextView)变化的时候调用
         *
         * @param datetime 变化后的Date值(包含年月日)
         */
        void onDateChanged(Datetime datetime);
    }

    /**
     * 注册一个监听器,它监听DateSelector的变化
     *
     * @param l 回调函数的监听器
     */
    public void addOnDateSelectorChangeListener(OnDateSelectorChangeListener l) {
        this.mOnDateSelectorChangeListeners.add(l);
    }

}
