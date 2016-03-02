package link.ebbinghaus.planning.view.fragment.impl;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.adapter.SimpleFragmentPagerAdapter;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.presenter.PlanningDisplaySpecificPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningDisplaySpecificPresenterImpl;
import link.ebbinghaus.planning.view.fragment.PlanningDisplaySpecificView;
import link.ebbinhaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningDisplaySpecificFragment extends BaseFragment implements PlanningDisplaySpecificView{
    private PlanningDisplaySpecificPresenter mPlanningDisplaySpecificPresenter;

    @Bind(R.id.vp_planning_display_specific) ViewPager mViewPager;
    @Bind(R.id.tl_planning_display_specific) TabLayout mTabLayout;
    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_specific, container, false);
        ButterKnife.bind(this,v);
        mPlanningDisplaySpecificPresenter = new PlanningDisplaySpecificPresenterImpl(this);

        mPlanningDisplaySpecificPresenter.configureRelatedViewPagerTabLayout();
        return v;
    }

    @Override
    public void bindViewPagerToTabLayout(List<Tab> tabs) {
        mFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getChildFragmentManager(), mActivity ,tabs);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

}
