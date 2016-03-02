package link.ebbinghaus.planning.view.fragment.impl;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.adapter.SimpleFragmentPagerAdapter;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.presenter.PlanningDisplayPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningDisplayPresenterImpl;
import link.ebbinghaus.planning.view.fragment.PlanningDisplayView;
import link.ebbinhaus.planning.R;

public class PlanningDisplayFragment extends BaseFragment implements PlanningDisplayView, ViewPager.OnPageChangeListener{
    private PlanningDisplayPresenter mPlanningDisplayPresenter;

    @Bind(R.id.abl_planning_display) AppBarLayout mAppBarLayout;
    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.vp_planning_display) ViewPager mViewPager;
    @Bind(R.id.tl_planning_display) TabLayout mTabLayout;
    private FragmentPagerAdapter mFragmentPagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display, container, false);
        ButterKnife.bind(this, v);
        mActivity.setSupportActionBar(mToolbar);
        mPlanningDisplayPresenter = new PlanningDisplayPresenterImpl(this);

        mViewPager.addOnPageChangeListener(this);
        mPlanningDisplayPresenter.configureRelatedViewPagerTabLayout();

        return v;
    }


    @Override
    public void bindViewPagerToTabLayout(List<Tab> tabs) {
        mFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getChildFragmentManager(), mActivity, tabs);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
    @Override
    public void onPageSelected(int position) { }
    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 1){
            mAppBarLayout.setExpanded(true);
        }
    }
}
