package link.ebbinghaus.planning.ui.presenter.main.impl;

import android.util.SparseArray;

import link.ebbinghaus.planning.core.service.MainService;
import link.ebbinghaus.planning.core.service.impl.MainServiceImpl;
import link.ebbinghaus.planning.ui.presenter.main.MainPresenter;
import link.ebbinghaus.planning.ui.view.main.MainView;

/**
 * Created by WINFIELD on 2016/2/19.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mView;
    private MainService mainService;

    public MainPresenterImpl(MainView mainView) {
        this.mView = mainView;
        mainService = new MainServiceImpl();
    }

    @Override
    public void cacheMainDrawerFragmentMap(SparseArray<Class> fm) {
        mainService.cacheMainDrawerFragmentMap(fm);
    }
}
