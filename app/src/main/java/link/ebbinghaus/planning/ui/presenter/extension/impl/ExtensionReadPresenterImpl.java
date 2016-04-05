package link.ebbinghaus.planning.ui.presenter.extension.impl;

import link.ebbinghaus.planning.common.callback.DataCallback;
import link.ebbinghaus.planning.core.service.ExtensionService;
import link.ebbinghaus.planning.core.model.vo.extension.douban.book.Result;
import link.ebbinghaus.planning.core.service.impl.ExtensionServiceImpl;
import link.ebbinghaus.planning.ui.presenter.extension.ExtensionReadPresenter;
import link.ebbinghaus.planning.ui.view.extension.ExtensionReadView;

/**
 * Created by WINFIELD on 2016/4/4.
 */
public class ExtensionReadPresenterImpl implements ExtensionReadPresenter {
    private ExtensionReadView mView;
    private ExtensionService mExtensionService;

    public ExtensionReadPresenterImpl(ExtensionReadView view) {
        this.mView = view;
        mExtensionService = new ExtensionServiceImpl();
    }

    @Override
    public void obtainBooks(String key,int start, DataCallback<Result> callback) {
        mExtensionService.findBooks(key,start,callback);
    }
}
