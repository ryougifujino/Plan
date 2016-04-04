package link.ebbinghaus.planning.view.extension.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.base.BaseFragment;

import link.ebbinghaus.planning.presenter.extension.ExtensionPresenter;
import link.ebbinghaus.planning.presenter.extension.impl.ExtensionPresenterImpl;
import link.ebbinghaus.planning.view.extension.ExtensionView;
import link.ebbinghaus.planning.R;

public class ExtensionFragment extends BaseFragment implements ExtensionView{

    private ExtensionPresenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_extension, container, false);
        mPresenter = new ExtensionPresenterImpl(this);

        return v;


    }

}
