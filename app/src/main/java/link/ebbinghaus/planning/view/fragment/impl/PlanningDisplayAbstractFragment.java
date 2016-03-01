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
import link.ebbinghaus.planning.presenter.PlanningDisplayAbstractPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningDisplayAbstractPresenterImpl;
import link.ebbinghaus.planning.view.fragment.PlanningDisplayAbstractView;
import link.ebbinhaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningDisplayAbstractFragment extends BaseFragment implements PlanningDisplayAbstractView{
    private PlanningDisplayAbstractPresenter mPlanningDisplayAbstractPresenter;

    @Bind(R.id.vp_planning_abstract) ViewPager mViewPager;
    @Bind(R.id.tl_planning_abstract) TabLayout mTabLayout;
    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_abstract, container, false);
        ButterKnife.bind(this,v);
        mPlanningDisplayAbstractPresenter = new PlanningDisplayAbstractPresenterImpl(this);

        mPlanningDisplayAbstractPresenter.configureRelatedViewPagerTabLayout();

        return v;
    }

    @Override
    public void bindViewPagerToTabLayout(List<Tab> tabs) {
        mFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getChildFragmentManager(), getActivity() ,tabs);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
