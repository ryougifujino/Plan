package link.ebbinghaus.planning.view.planning.display.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.ebbinghaus.adapter.planning.display.abst.AllRecyclerViewAdapter;
import link.ebbinghaus.planning.presenter.planning.display.PlanningDisplayAbstAllPresenter;
import link.ebbinghaus.planning.presenter.planning.display.impl.PlanningDisplayAbstAllPresenterImpl;
import link.ebbinghaus.planning.view.planning.display.PlanningDisplayAbstAllView;
import link.ebbinghaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningDisplayAbstAllFragment extends BaseFragment implements PlanningDisplayAbstAllView {

    @Bind(R.id.rv_planning_display_abst_all) RecyclerView mRecyclerView;
    private PlanningDisplayAbstAllPresenter mPresenter;
    private AllRecyclerViewAdapter mAllRecyclerViewAdapter;
    //标识变量,用于控制当执行了onCreateView后,onResume不再重复执行渲染工作
    private boolean isCallOnCreateView = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_display_abst_all, container, false);
        ButterKnife.bind(this,v);
        mPresenter = new PlanningDisplayAbstAllPresenterImpl(this);
        mPresenter.initAbstAllView();


        return v;
    }

    @Override
    public void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAllRecyclerViewAdapter = new AllRecyclerViewAdapter(mActivity,mPresenter.obtainAllAbstractEvents());
        mRecyclerView.setAdapter(mAllRecyclerViewAdapter);

    }

    @Override
    public void setOnCreateViewFlag() {
        isCallOnCreateView = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isCallOnCreateView){
            mAllRecyclerViewAdapter.refresh(mPresenter.obtainAllAbstractEvents());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isCallOnCreateView = false;
    }
}
