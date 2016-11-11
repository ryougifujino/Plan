package link.ebbinghaus.planning.ui.view.common.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.util.MenuTint;

import butterknife.Bind;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.presenter.common.CommonSearchEventPresenter;
import link.ebbinghaus.planning.ui.presenter.common.impl.CommonSearchEventPresenterImpl;
import link.ebbinghaus.planning.ui.view.common.CommonSearchEventView;

public class CommonSearchEventActivity extends BaseActivity implements CommonSearchEventView {

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.rv_common_result) RecyclerView mResultRv;
    private CommonSearchEventPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_search_event);
        mPresenter = new CommonSearchEventPresenterImpl(this);
        configureToolbar();
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
