package link.ebbinghaus.planning.ui.view.extension.fragment;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yurikami.lib.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.presenter.extension.ExtensionPresenter;
import link.ebbinghaus.planning.ui.presenter.extension.impl.ExtensionPresenterImpl;
import link.ebbinghaus.planning.ui.view.extension.ExtensionView;
import link.ebbinghaus.planning.ui.view.extension.activity.ExtensionReadActivity;
import link.ebbinghaus.planning.ui.view.extension.activity.ExtensionTimeSaverActivity;

public class ExtensionFragment extends BaseFragment implements ExtensionView,View.OnClickListener{

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    @Bind(R.id.ll_extension_read) LinearLayout readLl;
    @Bind(R.id.ll_extension_fragment_time_saver) LinearLayout timeSaverLl;
    private ExtensionPresenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_extension, container, false);
        ButterKnife.bind(this,v);
        mPresenter = new ExtensionPresenterImpl(this);
        configureToolbar();
        initExtension();

        return v;


    }

    private void configureToolbar() {
        mActivity.setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.extension_title);
        mToolbar.setNavigationIcon(R.drawable.common_navigation_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        mDrawerLayout = (DrawerLayout) mActivity.findViewById(R.id.dl_main_whole);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initExtension() {
        readLl.setOnClickListener(this);
        timeSaverLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_extension_read:
                startActivity(ExtensionReadActivity.class);
                break;
            case R.id.ll_extension_fragment_time_saver:
                startActivity(ExtensionTimeSaverActivity.class);
                break;
        }
    }
}
