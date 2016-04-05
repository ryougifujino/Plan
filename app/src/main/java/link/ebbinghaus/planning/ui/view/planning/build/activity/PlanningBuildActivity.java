package link.ebbinghaus.planning.ui.view.planning.build.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.util.LogUtils;
import com.yurikami.lib.util.MenuTint;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.ui.adapter.SimpleFragmentPagerAdapter;
import link.ebbinghaus.planning.core.model.sys.Tab;
import link.ebbinghaus.planning.core.model.vo.planning.build.InputEventVo;
import link.ebbinghaus.planning.ui.presenter.planning.build.PlanningBuildPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.build.impl.PlanningBuildPresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.build.PlanningBuildView;
import link.ebbinghaus.planning.ui.view.planning.build.fragment.PlanningBuildAbstractFragment;
import link.ebbinghaus.planning.ui.view.planning.build.fragment.PlanningBuildSpecificFragment;
import link.ebbinghaus.planning.R;

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
        mViewPager.addOnPageChangeListener(this);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean obtainSpecificInputEvent(InputEventVo inputEvent) {
        if (mOnBuildMenuItemClickListener == null){
            throw new IllegalStateException("保存按钮监听器没有被注册");
        }
        return mOnBuildMenuItemClickListener.onBuildMenuClick(inputEvent);
    }

    @Override
    public boolean obtainAbstractEvent(InputEventVo inputEventVo) {
        return obtainSpecificInputEvent(inputEventVo);
    }

    @Override
    public void resetSpecForm() {
        if (mOnEventSaveListener == null ){
            throw new IllegalStateException("重置监听器没有被注册");
        }
        mOnEventSaveListener.onEventSavedSuccessfully();
    }

    @Override
    public void resetAbstForm() {
        resetSpecForm();
    }

    @Override
    public void exitPlanningBuildActivity() {
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.planning_build, menu);
        //noinspection deprecation
        MenuTint.colorIcons(this,menu, getResources().getColor(R.color.md_white_1000));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //true:PlanningBuildSpecificFragment false:PlanningBuildAbstractFragment
        boolean flag;
        Fragment son = nowVPFragment(mViewPager.getId(), mViewPagerPosition);
        if( son instanceof PlanningBuildSpecificFragment) {
            flag = true;
        }else if (son instanceof PlanningBuildAbstractFragment){
            flag = false;
        }else {
            return false;
        }
        setOnBuildMenuItemClickListener((OnBuildMenuItemClickListener) son);
        setOnEventSaveListener((OnEventSaveListener) son);

        switch (item.getItemId()){
            case R.id.item_planning_build_save:
                if (flag) {
                    mPresenter.saveSpecificEvent();
                }else {
                    mPresenter.saveAbstractEvent();
                }
                return true;
            case R.id.item_planning_build_done:
                if (flag) {
                    mPresenter.doneSpecificEvent();
                }else {
                    mPresenter.doneAbstractEvent();
                }
                return true;
        }

        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
    @Override
    public void onPageSelected(int position) {
        mViewPagerPosition = position;
        LogUtils.d(TAG, "Page Position is : " + mViewPagerPosition);
    }
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
