package link.ebbinghaus.planning.ui.view.achievement.fragment;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.ui.adapter.achievement.AchievementRecyclerViewAdapter;
import link.ebbinghaus.planning.ui.presenter.achievement.AchievementPresenter;
import link.ebbinghaus.planning.ui.presenter.achievement.impl.AchievementPresenterImpl;
import link.ebbinghaus.planning.ui.view.achievement.AchievementView;
import link.ebbinghaus.planning.R;

public class AchievementFragment extends BaseFragment implements AchievementView{


    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    @Bind(R.id.rv_achievement) RecyclerView mRecyclerView;

    private AchievementPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_achievement, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new AchievementPresenterImpl(this);
        configureToolbar();
        initAchievement();

        return v;
    }

    private void configureToolbar() {
        mActivity.setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.achievement_title);
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
    public void initAchievement() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(new AchievementRecyclerViewAdapter(mActivity,mPresenter.obtainAchievements()));
    }
}
