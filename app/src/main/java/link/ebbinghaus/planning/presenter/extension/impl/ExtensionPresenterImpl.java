package link.ebbinghaus.planning.presenter.extension.impl;

import link.ebbinghaus.planning.model.ExtensionModel;
import link.ebbinghaus.planning.model.impl.ExtensionModelImpl;
import link.ebbinghaus.planning.presenter.extension.ExtensionPresenter;
import link.ebbinghaus.planning.view.extension.ExtensionView;

/**
 * Created by WINFIELD on 2016/4/4.
 */
public class ExtensionPresenterImpl implements ExtensionPresenter {

    private ExtensionView mView;
    private ExtensionModel mExtensionModel;

    public ExtensionPresenterImpl(ExtensionView view) {
        this.mView = view;
        mExtensionModel = new ExtensionModelImpl();
    }
}
