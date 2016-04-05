package link.ebbinghaus.planning.ui.view.common.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.util.MenuTint;
import com.yurikami.lib.widget.SingleInputDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.ui.adapter.common.select.DefaultSelectDaoAdapter;
import link.ebbinghaus.planning.ui.adapter.common.select.FastTemplateSelectDaoAdapter;
import link.ebbinghaus.planning.ui.adapter.common.select.SelectEventGroupRVAdapter;
import link.ebbinghaus.planning.ui.adapter.common.select.SelectEventSubtypeRVAdapter;
import link.ebbinghaus.planning.ui.adapter.common.select.SelectFastTemplateRVAdapter;
import link.ebbinghaus.planning.ui.adapter.common.select.SelectRecycleViewAdapter;
import link.ebbinghaus.planning.common.constant.Constant;
import link.ebbinghaus.planning.core.db.decorator.impl.EventGroupDaoDecorator;
import link.ebbinghaus.planning.core.db.decorator.impl.EventSubtypeDaoDecorator;
import link.ebbinghaus.planning.ui.viewholder.common.select.DeleteToolbarViewHolder;
import link.ebbinghaus.planning.ui.presenter.common.CommonSelectPresenter;
import link.ebbinghaus.planning.ui.presenter.common.impl.CommonSelectPresenterImpl;
import link.ebbinghaus.planning.ui.view.planning.build.fragment.PlanningBuildSpecificFragment;
import link.ebbinghaus.planning.ui.view.common.CommonSelectView;
import link.ebbinghaus.planning.R;

/**
 * 要想添加这个类SelectActivity新的使用者,只需要更改chooseRecyclerViewAdapter方法,<br>
 * 通过发送唯一的Flag来确定使用哪个适配器(SelectRecycleViewAdapter的子类)<br>
 * !Flag的值为发送者控件的 id 与上 FLAG_MASK 掩码
 */
public class CommonSelectActivity extends BaseActivity implements CommonSelectView,
        SelectRecycleViewAdapter.OnItemClickListener{

    /** 获取本Activity标题所需要用到的Intent Name */
    public static final String INTENT_NAME_TITLE = Constant.PACKAGE_NAME + ".SelectTitle";
    /**
     * 获取发送者所定义的requestCode所需要用到的Intent Name<br>
     * !requestCode应用了调用startActivityForResult的控件的Id来确保唯一性
     */
    public static final String INTENT_NAME_FLAG = Constant.PACKAGE_NAME + ".Flag";
    /** requestCode的掩码,用Id直接作为requestCode会太长,因此要与上掩码*/
    public static final int FLAG_MASK = 0xFFFF;
    /** 发送者接收返回信息时所用到的Intent Name */
    public static final String INTENT_NAME_RESULT = Constant.PACKAGE_NAME + ".Result";

    private CommonSelectPresenter mPresenter;
    private SelectRecycleViewAdapter mAdapter;
    private SingleInputDialog mAddDialog;
    private Intent mIntent;
    private int mFlag;

    @Bind(R.id.rv_common_select) RecyclerView mRecyclerView;
    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    private DeleteToolbarViewHolder mDeleteToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_select);
        ButterKnife.bind(this);
        mDeleteToolbar = new DeleteToolbarViewHolder(this);
        mPresenter = new CommonSelectPresenterImpl(this);
        mPresenter.configureToolbar();
        mPresenter.getAndSetSenderData();
        mPresenter.configureRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_select, menu);
        //noinspection deprecation
        MenuTint.colorIcons(this,menu,getResources().getColor(R.color.md_white_1000));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_common_select_toolbar_add:
                //弹出dialog
                showDialog();
                return true;
        }
        return false;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initToolbarAddDialog() {
        mAddDialog = SingleInputDialog.newInstance(getString(R.string.common_select_add_dialog_title));
    }
    private void showDialog() {
        mAddDialog.show(getSupportFragmentManager(), getClass().getName());
    }

    @Override
    public void setSenderData() {
        mIntent = getIntent();
        mToolbar.setTitle(mIntent.getStringExtra(INTENT_NAME_TITLE));
        mFlag = mIntent.getIntExtra(INTENT_NAME_FLAG, -1);
    }

    /**
     * 添加新的使用者,只需要改变这个方法
     */
    @Override
    public void chooseRecyclerViewAdapter() {
        switch (mFlag){
            case PlanningBuildSpecificFragment.FLAG_EVENT_SUBTYPE:
                mAdapter = new SelectEventSubtypeRVAdapter(this, new DefaultSelectDaoAdapter<>(EventSubtypeDaoDecorator.class),mDeleteToolbar);
                break;
            case PlanningBuildSpecificFragment.FLAG_FAST_TEMPLATE:
                //单独获取FastTemplate类型(普通、学习、模糊)
                int fastTemplateType = mIntent.getIntExtra(PlanningBuildSpecificFragment.INTENT_NAME_FAST_TEMPLATE_TYPE, -1);
                mAdapter = new SelectFastTemplateRVAdapter(this, new FastTemplateSelectDaoAdapter(fastTemplateType),mDeleteToolbar);
                break;
            case PlanningBuildSpecificFragment.FLAG_EVENT_GROUP:
                mAdapter = new SelectEventGroupRVAdapter(this, new DefaultSelectDaoAdapter<>(EventGroupDaoDecorator.class),mDeleteToolbar);
                break;
            default:
                throw new IllegalArgumentException("发送者传递的requestCode(Flag)不正确");
        }
    }

    @Override
    public void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setOnCreateButtonClickListener() {
        mAddDialog.setOnDialogConfirmListener(mAdapter);
    }

    @Override
    public void onItemClick(Parcelable result) {
        setResult(RESULT_OK, new Intent().putExtra(INTENT_NAME_RESULT, result));
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED, new Intent());
        finish();
    }

}
