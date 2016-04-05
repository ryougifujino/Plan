package link.ebbinghaus.planning.ui.view.achievement.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
    @Bind(R.id.rv_achievement) RecyclerView mRecyclerView;

    private AchievementPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_achievement, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new AchievementPresenterImpl(this);
        initAchievement();

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initAchievement() {
        mActivity.setSupportActionBar(mToolbar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(new AchievementRecyclerViewAdapter(mActivity,mPresenter.obtainAchievements()));
    }
}
