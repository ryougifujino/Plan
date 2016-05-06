package link.ebbinghaus.planning.ui.view.planning.build.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yurikami.lib.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.app.constant.Constant;
import link.ebbinghaus.planning.app.constant.config.entity.FastTemplateConfig;
import link.ebbinghaus.planning.app.constant.module.PlanningBuildConstant;
import link.ebbinghaus.planning.core.model.local.po.EventGroup;
import link.ebbinghaus.planning.core.model.local.po.FastTemplate;
import link.ebbinghaus.planning.core.model.local.vo.planning.build.InputEventVo;
import link.ebbinghaus.planning.ui.presenter.planning.build.PlanningBuildAbstractPresenter;
import link.ebbinghaus.planning.ui.presenter.planning.build.impl.PlanningBuildAbstractPresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.build.activity.PlanningBuildActivity;
import link.ebbinghaus.planning.ui.view.common.activity.CommonSelectActivity;
import link.ebbinghaus.planning.ui.view.planning.build.PlanningBuildAbstractView;
import link.ebbinghaus.planning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningBuildAbstractFragment extends BaseFragment implements PlanningBuildAbstractView,
        View.OnClickListener,
        PlanningBuildActivity.OnBuildMenuItemClickListener,PlanningBuildActivity.OnEventSaveListener{

    //requestCode(将会被用作在CommonSelectActivity识别类型的Flag)
    //在这里使用PlanningBuildSpecificFragment的requestCode,
    //代表在CommonSelectActivity的chooseRecyclerViewAdapter方法中走同一个分支
    public static final int FLAG_EVENT_GROUP = PlanningBuildSpecificFragment.FLAG_EVENT_GROUP;
    public static final int FLAG_FAST_TEMPLATE = PlanningBuildSpecificFragment.FLAG_FAST_TEMPLATE;
    /** Intent的name */
    public static final String INTENT_NAME_FAST_TEMPLATE_TYPE = Constant.PACKAGE_NAME + ".FastTemplateType";

    @Bind(R.id.et_planning_build_description) EditText descriptionEt;
    @Bind(R.id.btn_planning_build_fast_template) Button fastTemplateBtn;
    @Bind(R.id.tv_planning_build_event_group) TextView eventGroupTv;

    private PlanningBuildAbstractPresenter mPresenter;
    private Snackbar mSnackbar;
    /** 用来记录具体计划输入值的变量 */
    private InputEventVo mInputEvent = new InputEventVo();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_planning_build_abstract, container, false);
        ButterKnife.bind(this,v);
        mPresenter = new PlanningBuildAbstractPresenterImpl(this);
        mPresenter.registerListeners();
        mPresenter.setDefaultValues();

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case FLAG_FAST_TEMPLATE:
                if (resultCode == Activity.RESULT_OK){
                    FastTemplate fastTemplate = data.getParcelableExtra(CommonSelectActivity.INTENT_NAME_RESULT);
                    mPresenter.configureDescription(fastTemplate.getTemplate());
                }else {
                    mPresenter.configureDescription(null);
                }
                break;
            case FLAG_EVENT_GROUP:
                if (resultCode == Activity.RESULT_OK){
                    EventGroup eventGroup = data.getParcelableExtra(CommonSelectActivity.INTENT_NAME_RESULT);
                    mPresenter.configureEventGroup(eventGroup);
                }else {
                    EventGroup eventGroup = new EventGroup();
                    eventGroup.setDescription(getString(R.string.common_none));
                    mPresenter.configureEventGroup(eventGroup);
                }
                break;
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
    public InputEventVo getInputEvent() {
        mInputEvent.setDescription(descriptionEt.getText().toString());
        return mInputEvent;
    }

    @Override
    public void setDefaultEventGroupText(){
        eventGroupTv.setText(getString(R.string.common_none));
    }

    @Override
    public void setFastTemplate(String template) {
        descriptionEt.setText(template);
    }

    @Override
    public void setEventGroup(EventGroup eventGroup) {
        eventGroupTv.setText(eventGroup.getDescription());
        mInputEvent.setEventGroupId(eventGroup.getPkEventGroupId());
    }

    //回调函数,在这里面执行form的重置工作
    @Override
    public void onEventSavedSuccessfully() {
        mInputEvent = new InputEventVo();
        descriptionEt.setText("");
        eventGroupTv.setText(getString(R.string.common_none));
        mSnackbar.setText(getString(R.string.planning_build_abst_saved_successfully_info));
        mSnackbar.show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSnackbar = Snackbar.make(getView(), getString(R.string.planning_build_spec_description_validate_info), Snackbar.LENGTH_LONG);
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
        //提取页面数据到event中
        //step 1.验证填写完整性
        if (TextUtils.isEmpty(descriptionEt.getText().toString().trim())) {
            mSnackbar.setText(getString(R.string.planning_build_spec_description_validate_info));
            mSnackbar.show();
            return false;
        }
        //step 2.填入
        inputEvent.copyFrom(getInputEvent());
        return true;
    }


}
