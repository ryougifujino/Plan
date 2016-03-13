package link.ebbinghaus.planning.view.activity.impl;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.yurikami.lib.base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.adapter.SimpleFragmentPagerAdapter;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.presenter.PlanningBuildPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningBuildPresenterImpl;
import link.ebbinghaus.planning.view.activity.PlanningBuildView;
import link.ebbinhaus.planning.R;

public class PlanningBuildActivity extends BaseActivity implements PlanningBuildView{

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.tl_planning_build) TabLayout mTabLayout;
    @Bind(R.id.vp_planning_build) ViewPager mViewPager;
    private PlanningBuildPresenter mPlanningBuildPresenter;
    private FragmentPagerAdapter mFragmentPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_build);
        ButterKnife.bind(this);
        mPlanningBuildPresenter = new PlanningBuildPresenterImpl(this);

        mPlanningBuildPresenter.configureRelatedViewPagerTabLayout();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



    @Override
    public void bindViewPagerToTabLayout(List<Tab> tabs) {
        mFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, tabs);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_planning_build,menu);
        return true;
    }
}
