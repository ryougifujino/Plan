package link.ebbinghaus.planning.view.activity.impl;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yurikami.lib.base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.adapter.SimpleFragmentPagerAdapter;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.model.entity.vo.InputEventVo;
import link.ebbinghaus.planning.presenter.PlanningBuildPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningBuildPresenterImpl;
import link.ebbinghaus.planning.view.activity.PlanningBuildView;
import link.ebbinhaus.planning.R;

public class PlanningBuildActivity extends BaseActivity implements PlanningBuildView,
        ViewPager.OnPageChangeListener{

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.tl_planning_build) TabLayout mTabLayout;
    @Bind(R.id.vp_planning_build) ViewPager mViewPager;
    private OnBuildMenuItemClickListener mOnBuildMenuItemClickListener;
    private PlanningBuildPresenter mPresenter;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private int mViewPagerPosition;
    private OnEventSaveListener mOnEventSaveListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_build);
        ButterKnife.bind(this);
        mPresenter = new PlanningBuildPresenterImpl(this);
        mPresenter.configureToolbar();

        mPresenter.configureRelatedViewPagerTabLayout();


    }


    @Override
    public void setToolbar() {
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
    public boolean obtainSpecificInputEvent(InputEventVo inputEvent) {
        Fragment son = nowVPFragment(mViewPager.getId(), mViewPagerPosition);
        if( son instanceof OnBuildMenuItemClickListener) {
            return mOnBuildMenuItemClickListener.onBuildMenuClick(inputEvent);
        }
        return false;
    }

    @Override
    public void obtainAbstractEvent() {

    }

    @Override
    public void resetSpecForm() {
        mOnEventSaveListener.onEventSavedSuccessfully();
    }

    @Override
    public void exitPlanningBuildActivity() {
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_planning_build,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_planning_build_save:
                mPresenter.saveSpecificEvent();
                return true;
            case R.id.item_planning_build_done:
                mPresenter.doneSpecificEvent();
                return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
    @Override
    public void onPageSelected(int position) { mViewPagerPosition = position; }
    @Override
    public void onPageScrollStateChanged(int state) { }

    /**
     * 制定计划Toolbar上的save和done按钮的监听器
     */
    public interface OnBuildMenuItemClickListener{

        /**
         * 当制定计划Toolbar上的save和done按钮点击时调用此方法
         * 目的是为了从ViewPager中正在显示的Fragment里面提取相应的数据到形参event中
         * @param inputEvent 用于保存提取的数据
         * @return 如果event通过验证的话返回true,否则false
         */
        boolean onBuildMenuClick(InputEventVo inputEvent);
    }

    public void setOnBuildMenuItemClickListener(OnBuildMenuItemClickListener l){
        this.mOnBuildMenuItemClickListener = l;
    }


    /**
     * Event保存入数据库的监听器
     */
    public interface OnEventSaveListener{

        /**
         * 当Event顺利地被保存入数据库时调用,<br>
         * 这里用来重置输入form
         */
        void onEventSavedSuccessfully();
    }

    public void setOnEventSaveListener(OnEventSaveListener l){
        mOnEventSaveListener = l;
    }

}
