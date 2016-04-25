package link.ebbinghaus.planning.ui.view.extension.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.net.NetCallback;
import com.yurikami.lib.util.LogUtils;
import com.yurikami.lib.util.MenuTint;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.core.model.local.vo.extension.douban.book.Result;
import link.ebbinghaus.planning.ui.adapter.extension.ReadRecyclerViewAdapter;
import link.ebbinghaus.planning.ui.presenter.extension.ExtensionReadPresenter;
import link.ebbinghaus.planning.ui.presenter.extension.impl.ExtensionReadPresenterImpl;
import link.ebbinghaus.planning.ui.view.extension.ExtensionReadView;
import okhttp3.Call;

public class ExtensionReadActivity extends BaseActivity implements ExtensionReadView,
        ReadRecyclerViewAdapter.Callback,NetCallback{

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.rv_extension_read) RecyclerView mRecyclerView;
    private ReadRecyclerViewAdapter mReadRecyclerViewAdapter;
    private SearchView mSearchView;
    //关键字
    private String mKey;
    private ExtensionReadPresenter mPresenter;

    private Call mRefreshCall;
    private Call mAppendCall;

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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {  // TODO: 2016/4/20 延迟加载优化
                mKey = newText;
                if (mRefreshCall != null){
                    mRefreshCall.cancel();
                }
                mRefreshCall = mPresenter.obtainBooks(newText, 0, ExtensionReadActivity.this); // TODO: 2016/4/20 流量优化
                return false;
            }
        });
    }

    @Override
    public void requestNewData(int start) {
        mAppendCall = mPresenter.obtainBooks(mKey, start,this);
    }

    @Override
    public void onSuccess(Object result, Call call) {
        LogUtils.d(TAG,"execute");
        if (call == mRefreshCall) {
            mReadRecyclerViewAdapter.refresh((Result) result);
        }else if (call == mAppendCall){
            mReadRecyclerViewAdapter.appendRefresh((Result) result);
        }
        mRefreshCall = null;
        mAppendCall = null;
    }

    @Override
    public void onFailure(String errorMsg, Call call) {

    }
}
