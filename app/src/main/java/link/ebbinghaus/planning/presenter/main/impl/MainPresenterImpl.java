package link.ebbinghaus.planning.presenter.main.impl;

import android.util.SparseArray;

import link.ebbinghaus.planning.model.MainModel;
import link.ebbinghaus.planning.model.impl.MainModelImpl;
import link.ebbinghaus.planning.presenter.main.MainPresenter;
import link.ebbinghaus.planning.view.main.MainView;

/**
 * Created by WINFIELD on 2016/2/19.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mView;
    private MainModel mainModel;

    public MainPresenterImpl(MainView mainView) {
        this.mView = mainView;
        mainModel = new MainModelImpl();
    }

    @Override
    public void cacheMainDrawerFragmentMap(SparseArray<Class> fm) {
        mainModel.cacheMainDrawerFragmentMap(fm);
    }
}
