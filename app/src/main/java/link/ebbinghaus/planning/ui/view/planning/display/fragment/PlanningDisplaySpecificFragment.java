package link.ebbinghaus.planning.ui.view.planning.display.fragment;


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
import link.ebbinghaus.planning.ui.adapter.SimpleFragmentPagerAdapter;
import link.ebbinghaus.planning.core.model.local.sys.Tab;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplaySpecificPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.display.impl.PlanningDisplaySpecificPresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplaySpecificView;
import link.ebbinghaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningDisplaySpecificFragment extends BaseFragment implements PlanningDisplaySpecificView,
        ViewPager.OnPageChangeListener{
    private PlanningDisplaySpecificPresenter mPresenter;

    @Bind(R.id.vp_planning_display_specific) ViewPager mViewPager;
    @Bind(R.id.tl_planning_display_specific) TabLayout mTabLayout;
    private int mViewPagerPosition;
    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_specific, container, false);
        ButterKnife.bind(this,v);
        mPresenter = new PlanningDisplaySpecificPresenterImpl(this);

        mViewPager.addOnPageChangeListener(this);
        mPresenter.configureRelatedViewPagerTabLayout();

        return v;
    }

    @Override
    public void bindViewPagerToTabLayout(List<Tab> tabs) {
        mFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getChildFragmentManager(), mActivity ,tabs);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
    @Override
    public void onPageSelected(int position) { mViewPagerPosition = position; }
    @Override
    public void onPageScrollStateChanged(int state) { }

    /**
     * 获取PlanningDisplaySpecificFragment里ViewPager正在显示的Page里的Fragment的实例
     */
    public Fragment nowVPFragment(){
        return nowVPFragment(mViewPager.getId(), mViewPagerPosition);
    }
}
