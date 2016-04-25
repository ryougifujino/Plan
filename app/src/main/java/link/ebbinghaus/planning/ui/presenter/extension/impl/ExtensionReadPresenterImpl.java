package link.ebbinghaus.planning.ui.presenter.extension.impl;

import com.yurikami.lib.net.NetCallback;
import link.ebbinghaus.planning.core.service.ExtensionService;
import link.ebbinghaus.planning.core.service.impl.ExtensionServiceImpl;
import link.ebbinghaus.planning.ui.presenter.extension.ExtensionReadPresenter;
import link.ebbinghaus.planning.ui.view.extension.ExtensionReadView;
import okhttp3.Call;

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
    public Call obtainBooks(String key, int start, NetCallback callback) {
        return mExtensionService.findBooks(key,start,callback);
    }
}
