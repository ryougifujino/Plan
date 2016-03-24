package link.ebbinghaus.planning.view.fragment.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;
import com.yurikami.lib.base.BaseFragment;
import com.yurikami.lib.entity.Datetime;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.adapter.SimpleFragmentPagerAdapter;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.presenter.PlanningDisplayPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningDisplayPresenterImpl;
import link.ebbinghaus.planning.view.activity.impl.PlanningBuildActivity;
import link.ebbinghaus.planning.view.fragment.PlanningDisplayView;
import link.ebbinhaus.planning.R;

public class PlanningDisplayFragment extends BaseFragment implements PlanningDisplayView,
        ViewPager.OnPageChangeListener, View.OnClickListener, CalendarDatePickerDialogFragment.OnDateSetListener {

    private PlanningDisplayPresenter mPresenter;

    @Bind(R.id.abl_planning_display) AppBarLayout mAppBarLayout;
    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.vp_planning_display) ViewPager mViewPager;
    @Bind(R.id.tl_planning_display) TabLayout mTabLayout;
    private LayoutInflater mLayoutInflater;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private int mViewPagerPosition;
    private OnToolbarDateChangeListener mOnToolbarDateChangeListener;
    private TextView mToolbarDateTv;
    private CalendarDatePickerDialogFragment mCalendarDatePicker;
    Datetime mNowDate = Datetime.buildTodayDate();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mLayoutInflater = inflater;
        View v = mLayoutInflater.inflate(R.layout.fragment_planning_display, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new PlanningDisplayPresenterImpl(this);

        mViewPager.addOnPageChangeListener(this);
        mPresenter.configureRelatedViewPagerTabLayout();

        mActivity.setSupportActionBar(mToolbar);
        //设置此,toolbar上的menu才能显示
        setHasOptionsMenu(true);

        mPresenter.preprocessToolbarDate();


        return v;
    }

    @Override
    public void bindViewPagerToTabLayout(List<Tab> tabs) {
        mFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getChildFragmentManager(), mActivity, tabs);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void presetToolbarDate() {
        mToolbarDateTv = (TextView) mLayoutInflater.inflate(R.layout.textview_planning_display_toolbar_date, mToolbar, false);
        mToolbarDateTv.setText(String.format(mActivity.getResources().getString(R.string.planning_display_toolbar_date), mNowDate.getYear(), mNowDate.getMonth(), mNowDate.getDay()));
        mToolbarDateTv.setOnClickListener(this);
        mToolbar.addView(mToolbarDateTv);

        mCalendarDatePicker = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(this)
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setPreselectedDate(mNowDate.getYear(), mNowDate.getMonth() - 1, mNowDate.getDay())
                .setDateRange(new MonthAdapter.CalendarDay(0), null)
                .setThemeLight();
    }

    @Override
    public void setToolbarDate() {
        Fragment son = nowVPFragment(mViewPager.getId(), mViewPagerPosition);
        if (son instanceof PlanningDisplaySpecificFragment) {
            PlanningDisplaySpecificFragment specificFragment = (PlanningDisplaySpecificFragment) son;
            Fragment grandson = specificFragment.nowVPFragment();

            if (grandson instanceof OnToolbarDateChangeListener) {
                mCalendarDatePicker.show(getChildFragmentManager(), this.getTag());
            }
        }
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        mOnToolbarDateChangeListener.onDateChanged(Datetime.buildDate(year, monthOfYear + 1, dayOfMonth));
        mToolbarDateTv.setText(String.format(getString(R.string.planning_display_toolbar_date), year, monthOfYear + 1, dayOfMonth));
        //TODO:可以有一个全局变量的设置控制重新开启是今天还是上一次的日期
        dialog.setPreselectedDate(mNowDate.getYear(), mNowDate.getMonth() - 1, mNowDate.getDay());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
    @Override
    public void onPageSelected(int position) { mViewPagerPosition = position; }
    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 1){
            mAppBarLayout.setExpanded(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_planning_display,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_planning_display_toolbar_date:
                mPresenter.configureToolbarDate();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_planning_display_toolbar_add:
                item.setIntent(new Intent(mActivity, PlanningBuildActivity.class));
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 定义接口,这个接口里有一个回调函数,当Toolbar上的Date改变的时候调用
     */
    public interface OnToolbarDateChangeListener {

        /**
         * 当Toolbar上的Date(TextView)变化的时候调用
         * @param datetime 变化后的Date值(包含年月日)
         */
        void onDateChanged(Datetime datetime);
    }

    /**
     * 注册一个监听器,它监听Toolbar上Date的变化
     * @param l 回调函数的监听器
     */
    public void setOnToolbarDateChangeListener(OnToolbarDateChangeListener l){
        this.mOnToolbarDateChangeListener = l;
    }



}
