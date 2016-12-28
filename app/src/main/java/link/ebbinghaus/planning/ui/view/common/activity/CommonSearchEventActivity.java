package link.ebbinghaus.planning.ui.view.common.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.util.MenuTint;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.ui.adapter.planning.display.spec.WeekRecyclerViewAdapter;
import link.ebbinghaus.planning.ui.presenter.common.CommonSearchEventPresenter;
import link.ebbinghaus.planning.ui.presenter.common.impl.CommonSearchEventPresenterImpl;
import link.ebbinghaus.planning.ui.view.common.CommonSearchEventView;

public class CommonSearchEventActivity extends BaseActivity implements CommonSearchEventView {

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.rv_common_result) RecyclerView mResultRv;
    @Bind(R.id.et_common_search) EditText mEditText;
    private CommonSearchEventPresenter mPresenter;
    private WeekRecyclerViewAdapter mWeekRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_search_event);
        ButterKnife.bind(this);
        mPresenter = new CommonSearchEventPresenterImpl(this);
        configureToolbar();

        mResultRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mWeekRecyclerViewAdapter = new WeekRecyclerViewAdapter(mActivity,new ArrayList<Event>());
        mResultRv.setAdapter(mWeekRecyclerViewAdapter);
        mEditText.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (!TextUtils.isEmpty(text.trim()))
                    mWeekRecyclerViewAdapter.refresh(mPresenter.searchEvents(text,true));
            }
        });
    }

    private void configureToolbar() {
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_search_event, menu);
        //noinspection deprecation
        MenuTint.colorIcons(this,menu, getResources().getColor(R.color.md_white_1000));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_common_search_filter:

                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }
}
