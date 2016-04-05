package link.ebbinghaus.planning.ui.view.extension.fragment;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yurikami.lib.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.presenter.extension.ExtensionPresenter;
import link.ebbinghaus.planning.ui.presenter.extension.impl.ExtensionPresenterImpl;
import link.ebbinghaus.planning.ui.view.extension.ExtensionView;
import link.ebbinghaus.planning.ui.view.extension.activity.ExtensionReadActivity;

public class ExtensionFragment extends BaseFragment implements ExtensionView,View.OnClickListener{

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.ll_extension_read) LinearLayout readLl;
    private ExtensionPresenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_extension, container, false);
        ButterKnife.bind(this,v);
        mPresenter = new ExtensionPresenterImpl(this);
        initExtension();

        return v;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initExtension() {
        mActivity.setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.extension_title);
        readLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_extension_read:
                startActivity(newIntent(ExtensionReadActivity.class));
                break;
        }
    }
}
