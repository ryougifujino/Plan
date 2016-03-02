package link.ebbinghaus.planning.view.activity.impl;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;

import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.utils.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.presenter.MainPresenter;
import link.ebbinghaus.planning.presenter.impl.MainPresenterImpl;
import link.ebbinghaus.planning.view.activity.MainView;
import link.ebbinhaus.planning.R;

public class MainActivity extends BaseActivity implements MainView,
        NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{
    @Bind(R.id.nv_main_drawer) NavigationView mNVDrawer;
    @Bind(R.id.dl_main_whole) DrawerLayout mDrawerLayout;

    private MainPresenter mMainPresenter;

    private SparseArray<Class> mFragmentMap = new SparseArray<>();
    private int mNowFragmentId;
    private MenuItem mLastMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        mNVDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout.setDrawerListener(this);

        mMainPresenter.cacheMainDrawerFragmentMap(mFragmentMap);

        LogUtil.d("DBConfig", DBConfig.CREATE_TABLE_EVENT);
        LogUtil.d("DBConfig", DBConfig.CREATE_TABLE_LEARNING_EVENT_GROUP);
        LogUtil.d("DBConfig", DBConfig.CREATE_TABLE_DEFAULT_INPUT_VALUE);
        LogUtil.d("DBConfig", DBConfig.CREATE_TABLE_EVENT_GROUP);
        LogUtil.d("DBConfig", DBConfig.CREATE_TABLE_EVENT_SUBTYPE);
        LogUtil.d("DBConfig", DBConfig.CREATE_TABLE_FAST_TEMPLATE);
    }

    private void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenterImpl(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if(mLastMenuItem != null)
            mLastMenuItem.setChecked(false);
        menuItem.setChecked(true);
        mLastMenuItem = menuItem;
        mNowFragmentId = menuItem.getItemId();
        mDrawerLayout.closeDrawer(mNVDrawer);
        return true;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) { }
    @Override
    public void onDrawerStateChanged(int newState) { }
    @Override
    public void onDrawerOpened(View drawerView) { }
    
    @Override
    public void onDrawerClosed(View drawerView) {
        try {
            Fragment f = (Fragment) mFragmentMap.get(mNowFragmentId).newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_content,f).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
