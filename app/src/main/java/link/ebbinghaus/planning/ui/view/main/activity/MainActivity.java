package link.ebbinghaus.planning.ui.view.main.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.util.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.constant.config.DBConfig;
import link.ebbinghaus.planning.ui.presenter.main.MainPresenter;
import link.ebbinghaus.planning.ui.presenter.main.impl.MainPresenterImpl;
import link.ebbinghaus.planning.ui.view.main.MainView;

public class MainActivity extends BaseActivity implements MainView,
        NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener,View.OnClickListener{
    @Bind(R.id.nv_main_drawer) NavigationView mNVDrawer;
    @Bind(R.id.dl_main_whole) DrawerLayout mDrawerLayout;
    private ImageView mAvatarIv;

    private MainPresenter mPresenter;

    private SparseArray<Class> mFragmentMap = new SparseArray<>();
    private int mNowFragmentId;
    private MenuItem mLastMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        LogUtils.d("DBConfig", DBConfig.CREATE_TABLE_EVENT);
        LogUtils.d("DBConfig", DBConfig.CREATE_TABLE_GREEK_ALPHABET);
        LogUtils.d("DBConfig", DBConfig.CREATE_TABLE_LEARNING_EVENT_GROUP);
        LogUtils.d("DBConfig", DBConfig.CREATE_TABLE_DEFAULT_INPUT_VALUE);
        LogUtils.d("DBConfig", DBConfig.CREATE_TABLE_EVENT_GROUP);
        LogUtils.d("DBConfig", DBConfig.CREATE_TABLE_EVENT_SUBTYPE);
        LogUtils.d("DBConfig", DBConfig.CREATE_TABLE_FAST_TEMPLATE);
        LogUtils.d("DBConfig", DBConfig.CREATE_TABLE_ACHIEVEMENT);
    }

    private void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenterImpl(this);

        mNVDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout.addDrawerListener(this);
        mAvatarIv = (ImageView) mNVDrawer.getHeaderView(0).findViewById(R.id.iv_main_drawer_avatar);
        mAvatarIv.setOnClickListener(this);

        mPresenter.cacheMainDrawerFragmentMap(mFragmentMap);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //noinspection deprecation
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
        }
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_main_drawer_avatar:
                startActivity(LoginActivity.class);
        }
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
            //FIXME java.lang.NullPointerException
            Fragment f = (Fragment) mFragmentMap.get(mNowFragmentId).newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main_content,f).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
