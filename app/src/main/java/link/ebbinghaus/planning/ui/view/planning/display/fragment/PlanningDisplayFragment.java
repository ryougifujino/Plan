package link.ebbinghaus.planning.ui.view.planning.display.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;
import com.yurikami.lib.util.MenuTint;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.core.model.local.sys.Tab;
import link.ebbinghaus.planning.ui.adapter.SimpleFragmentPagerAdapter;
import link.ebbinghaus.planning.ui.presenter.planning.display.PlanningDisplayPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.display.impl.PlanningDisplayPresenterImpl;
import link.ebbinghaus.planning.ui.view.common.activity.CommonSearchEventActivity;
import link.ebbinghaus.planning.ui.view.planning.build.activity.PlanningBuildActivity;
import link.ebbinghaus.planning.ui.view.planning.display.PlanningDisplayView;

public class PlanningDisplayFragment extends BaseFragment implements PlanningDisplayView,
        ViewPager.OnPageChangeListener {

    private PlanningDisplayPresenter mPresenter;

    @Bind(R.id.abl_planning_display) AppBarLayout mAppBarLayout;
    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.vp_planning_display) ViewPager mViewPager;
    @Bind(R.id.tl_planning_display) TabLayout mTabLayout;
    private DrawerLayout mDrawerLayout;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private int mViewPagerPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new PlanningDisplayPresenterImpl(this);

        mViewPager.addOnPageChangeListener(this);
        mPresenter.configureRelatedViewPagerTabLayout();
        configureToolbar();

        return v;
    }

    private void configureToolbar() {
        mActivity.setSupportActionBar(mToolbar);
        if (mActivity.getSupportActionBar() != null){
            mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationIcon(R.drawable.common_navigation_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        mDrawerLayout = (DrawerLayout) mActivity.findViewById(R.id.dl_main_whole);
        mToolbar.setTitle(R.string.planning_display_title);
        //设置此,toolbar上的menu才能显示
        setHasOptionsMenu(true);

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
    public void onPageSelected(int position) { mViewPagerPosition = position; }
    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 1){
            mAppBarLayout.setExpanded(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.planning_display, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //noinspection deprecation
        MenuTint.colorIcons(mActivity, menu, getResources().getColor(R.color.md_white_1000));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_planning_display_toolbar_add:
                item.setIntent(newIntent(PlanningBuildActivity.class));
                return false;
            case R.id.item_planning_display_toolbar_search:
                item.setIntent(newIntent(CommonSearchEventActivity.class));
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

}
