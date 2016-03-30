package link.ebbinghaus.planning.view.fragment.impl;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.adapter.planning.done.FinishRecyclerViewAdapter;
import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.presenter.PlanningDonePresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningDonePresenterImpl;
import link.ebbinghaus.planning.view.fragment.PlanningDoneView;
import link.ebbinhaus.planning.R;

public class PlanningDoneFragment extends BaseFragment implements PlanningDoneView{

    @Bind(R.id.rv_planning_done) RecyclerView mRecyclerView;
    private FinishRecyclerViewAdapter mFinishRecyclerViewAdapter;
    private PlanningDonePresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_done, container, false);
        ButterKnife.bind(this,v);
        mPresenter = new PlanningDonePresenterImpl(this);
        mPresenter.initPlanningDoneView();

        return v;
    }

    @Override
    public void setRecyclerView(List<Event> events) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mFinishRecyclerViewAdapter = new FinishRecyclerViewAdapter(mActivity,events);
        mRecyclerView.setAdapter(mFinishRecyclerViewAdapter);
    }
}
