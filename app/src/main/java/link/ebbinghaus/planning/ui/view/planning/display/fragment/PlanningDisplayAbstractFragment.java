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
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplayAbstractPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.display.impl.PlanningDisplayAbstractPresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplayAbstractView;
import link.ebbinghaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningDisplayAbstractFragment extends BaseFragment implements PlanningDisplayAbstractView{
    private PlanningDisplayAbstractPresenter mPresenter;

    @Bind(R.id.vp_planning_display_abstract) ViewPager mViewPager;
    @Bind(R.id.tl_planning_display_abstract) TabLayout mTabLayout;
    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_abstract, container, false);
        ButterKnife.bind(this,v);
        mPresenter = new PlanningDisplayAbstractPresenterImpl(this);

        mPresenter.configureRelatedViewPagerTabLayout();

        return v;
    }

    @Override
    public void bindViewPagerToTabLayout(List<Tab> tabs) {
        mFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getChildFragmentManager(), mActivity ,tabs);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
