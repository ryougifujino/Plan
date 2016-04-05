package link.ebbinghaus.planning.ui.view.extension.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.util.LogUtils;
import com.yurikami.lib.util.MenuTint;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.adapter.extension.ReadRecyclerViewAdapter;
import link.ebbinghaus.planning.common.callback.DataCallback;
import link.ebbinghaus.planning.common.util.CommonUtils;
import link.ebbinghaus.planning.core.model.vo.extension.douban.book.Result;
import link.ebbinghaus.planning.ui.presenter.extension.ExtensionReadPresenter;
import link.ebbinghaus.planning.ui.presenter.extension.impl.ExtensionReadPresenterImpl;
import link.ebbinghaus.planning.ui.view.extension.ExtensionReadView;

public class ExtensionReadActivity extends BaseActivity implements ExtensionReadView,
        ReadRecyclerViewAdapter.Callback{

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.rv_extension_read) RecyclerView mRecyclerView;
    private ReadRecyclerViewAdapter mReadRecyclerViewAdapter;
    private SearchView mSearchView;
    private String mKey;
    private ExtensionReadPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extension_read);
        ButterKnife.bind(this);
        initExtensionRead();
        mPresenter = new ExtensionReadPresenterImpl(this);

    }

    @Override
    public void initExtensionRead() {
        //Toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitle(R.string.extension_read_title);

        //RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mReadRecyclerViewAdapter = new ReadRecyclerViewAdapter(this,null);
        mRecyclerView.setAdapter(mReadRecyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.extension_read, menu);
        MenuTint.colorIcons(this, menu, getResources().getColor(R.color.md_white_1000));
        setSearchView(menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setSearchView(Menu menu) {
        mSearchView = (SearchView) menu.findItem(R.id.item_extension_read_search_view).getActionView();
        mSearchView.setQueryHint(getString(R.string.extension_read_search_view_hint));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                CommonUtils.showLongToast("submit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mKey = newText;
                mPresenter.obtainBooks(newText, 0,new DataCallback<Result>() {    // TODO: 2016/4/5 流量优化
                    @Override
                    public void onSuccess(final Result result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mReadRecyclerViewAdapter.refresh(result);
                            }
                        });
                    }

                    @Override
                    public void onFailure(String msg) {
                        LogUtils.i(TAG,"Loading books failed : " + msg);
                    }
                });
                return false;
            }
        });
    }

    @Override
    public void requestNewData(int start) {
        mPresenter.obtainBooks(mKey, start,new DataCallback<Result>() {    // TODO: 2016/4/5 流量优化
            @Override
            public void onSuccess(final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mReadRecyclerViewAdapter.appendRefresh(result);
                    }
                });
            }

            @Override
            public void onFailure(String msg) {
                LogUtils.i(TAG,"Loading books failed : " + msg);
            }
        });
    }
}
