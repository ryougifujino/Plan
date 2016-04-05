package link.ebbinghaus.planning.ui.presenter.extension.impl;

import link.ebbinghaus.planning.core.service.ExtensionService;
import link.ebbinghaus.planning.core.service.impl.ExtensionServiceImpl;
import link.ebbinghaus.planning.ui.presenter.extension.ExtensionPresenter;
import link.ebbinghaus.planning.ui.view.extension.ExtensionView;

/**
 * Created by WINFIELD on 2016/4/4.
 */
public class ExtensionPresenterImpl implements ExtensionPresenter {

    private ExtensionView mView;
    private ExtensionService mExtensionService;

    public ExtensionPresenterImpl(ExtensionView view) {
        this.mView = view;
        mExtensionService = new ExtensionServiceImpl();
    }
}
