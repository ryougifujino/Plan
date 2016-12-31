package link.ebbinghaus.planning.ui.view.planning.done.fragment;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;
import com.yurikami.lib.util.LogUtils;
import com.yurikami.lib.util.MenuTint;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.ui.adapter.planning.done.FinishRecyclerViewAdapter;
import link.ebbinghaus.planning.ui.presenter.planning.done.PlanningDonePresenter;
import link.ebbinghaus.planning.ui.presenter.planning.done.impl.PlanningDonePresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.done.PlanningDoneView;

public class PlanningDoneFragment extends BaseFragment implements PlanningDoneView {

    @Bind(R.id.rv_planning_done) RecyclerView mRecyclerView;
    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

    private static final int SHOW_ONLY_UNFINISHED = 763;
    private static final int SHOW_ONLY_FINISHED = 713;
    private static final int SHOW_ALL = 669;
    /**
     * 用于存储列表显示类型
     */
    private int mDisplayType = SHOW_ALL;
    private FinishRecyclerViewAdapter mFinishRecyclerViewAdapter;
    private PlanningDonePresenter mPresenter;

    //标识变量,用于控制当执行了onCreateView后,onResume不再重复执行渲染工作
    private boolean isCallOnCreateView = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_done, container, false);
        ButterKnife.bind(this, v);
        mPresenter = new PlanningDonePresenterImpl(this);
        mPresenter.initPlanningDoneView();

        isCallOnCreateView = true;
        return v;
    }

    @Override
    public void initToolbar() {
        mActivity.setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.planning_done_toolbar_title);
        if (mActivity.getSupportActionBar() != null) {
            mActivity.getSupportActionBar().setDisplayUseLogoEnabled(true);
        }
        mToolbar.setNavigationIcon(R.drawable.common_navigation_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        mDrawerLayout = (DrawerLayout) mActivity.findViewById(R.id.dl_main_whole);
        setHasOptionsMenu(true);
    }

    @Override
    public void setRecyclerView(List<Event> events) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mFinishRecyclerViewAdapter = new FinishRecyclerViewAdapter(mActivity, events);
        mRecyclerView.setAdapter(mFinishRecyclerViewAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isCallOnCreateView) {
            switch (mDisplayType){
                case SHOW_ONLY_UNFINISHED:
                    mFinishRecyclerViewAdapter.refresh(mPresenter.filteredUnfinishedEvents());
                    break;
                case SHOW_ONLY_FINISHED:
                    mFinishRecyclerViewAdapter.refresh(mPresenter.filteredFinishedEvents());
                    break;
                case SHOW_ALL:
                    mFinishRecyclerViewAdapter.refresh(mPresenter.obtainDoneModuleEvents());
                    break;
            }
            LogUtils.d(TAG, "inner onResume if(!isCallOnCreateView){}");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isCallOnCreateView = false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.planning_done,menu);
        MenuTint.colorIcons(mActivity,menu,getResources().getColor(R.color.md_white_1000));
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sub_item_planning_done_filter_unfinished:
                mFinishRecyclerViewAdapter.refresh(mPresenter.filteredUnfinishedEvents());
                mDisplayType = SHOW_ONLY_UNFINISHED;
                return true;
            case R.id.sub_item_planning_done_filter_finished:
                mFinishRecyclerViewAdapter.refresh(mPresenter.filteredFinishedEvents());
                mDisplayType = SHOW_ONLY_FINISHED;
                return true;
            case R.id.sub_item_planning_done_filter_all:
                mFinishRecyclerViewAdapter.refresh(mPresenter.obtainDoneModuleEvents());
                mDisplayType = SHOW_ALL;
                return true;
            case R.id.item_planning_done_help:
                showMessageDialog(R.string.planning_done_help_title,
                        R.string.planning_done_help_content,R.string.common_got_it);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // TODO: 2016/3/31 check all 参考ButterKnife官网 http://jakewharton.github.io/butterknife/
        ButterKnife.unbind(this);
    }
}
