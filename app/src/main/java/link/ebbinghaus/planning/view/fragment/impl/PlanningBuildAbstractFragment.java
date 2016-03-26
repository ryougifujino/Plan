package link.ebbinghaus.planning.view.fragment.impl;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yurikami.lib.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.constant.Constant;
import link.ebbinghaus.planning.custom.constant.config.entity.FastTemplateConfig;
import link.ebbinghaus.planning.custom.constant.module.PlanningBuildConstant;
import link.ebbinghaus.planning.model.entity.vo.InputEventVo;
import link.ebbinghaus.planning.presenter.PlanningBuildAbstractPresenter;
import link.ebbinghaus.planning.presenter.impl.PlanningBuildAbstractPresenterImpl;
import link.ebbinghaus.planning.view.activity.impl.CommonSelectActivity;
import link.ebbinghaus.planning.view.activity.impl.PlanningBuildActivity;
import link.ebbinghaus.planning.view.fragment.PlanningBuildAbstractView;
import link.ebbinhaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningBuildAbstractFragment extends BaseFragment implements PlanningBuildAbstractView,
        View.OnClickListener,
        PlanningBuildActivity.OnBuildMenuItemClickListener,PlanningBuildActivity.OnEventSaveListener{

    //requestCode
    public static final int FLAG_EVENT_GROUP = R.id.tv_planning_build_event_group & CommonSelectActivity.FLAG_MASK;
    public static final int FLAG_FAST_TEMPLATE = R.id.btn_planning_build_fast_template & CommonSelectActivity.FLAG_MASK;
    /** Intent的name */
    public static final String INTENT_NAME_FAST_TEMPLATE_TYPE = Constant.PACKAGE_NAME + ".FastTemplateType";

    @Bind(R.id.et_planning_build_description) EditText descriptionEt;
    @Bind(R.id.btn_planning_build_fast_template) Button fastTemplateBtn;
    @Bind(R.id.tv_planning_build_event_group) TextView eventGroupTv;

    private PlanningBuildAbstractPresenter mPresenter;
    private Snackbar mSnackbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_build_abstract, container, false);
        ButterKnife.bind(this,v);
        mPresenter = new PlanningBuildAbstractPresenterImpl(this);
        mPresenter.registerListeners();

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FLAG_EVENT_GROUP){

        }
    }

    @Override
    public void setListeners() {
        fastTemplateBtn.setOnClickListener(this);
        eventGroupTv.setOnClickListener(this);

        //注册PlanningBuildActivity里面的监听器
        //!省略,由父Activity在Page切换的时候获取子类实例进行注册
        //直接注册的话会导致监听器覆盖
//        ((PlanningBuildActivity)mActivity).setOnBuildMenuItemClickListener(this);
//        ((PlanningBuildActivity)mActivity).setOnEventSaveListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_planning_build_fast_template:
                startActivityForResult(
                        newIntent(CommonSelectActivity.class)
                                .putExtra(INTENT_NAME_FAST_TEMPLATE_TYPE, FastTemplateConfig.TYPE_ABSTRACT)
                                .putExtra(CommonSelectActivity.INTENT_NAME_TITLE, PlanningBuildConstant.TITLE_SELECT_FAST_TEMPLATE)
                                .putExtra(CommonSelectActivity.INTENT_NAME_FLAG, FLAG_FAST_TEMPLATE),
                        FLAG_FAST_TEMPLATE);
                break;
            case R.id.tv_planning_build_event_group:
                startActivityForResult(
                        newIntent(CommonSelectActivity.class)
                                .putExtra(CommonSelectActivity.INTENT_NAME_TITLE, PlanningBuildConstant.TITLE_SELECT_EVENT_GROUP)
                                .putExtra(CommonSelectActivity.INTENT_NAME_FLAG, FLAG_EVENT_GROUP),
                        FLAG_EVENT_GROUP);
                break;
        }
    }

    @Override
    public boolean onBuildMenuClick(InputEventVo inputEvent) {
        return false;
    }

    @Override
    public void onEventSavedSuccessfully() {

    }


}
