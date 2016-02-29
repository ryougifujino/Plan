package link.ebbinhaus.planning.presenter.impl;

import android.util.SparseArray;

import link.ebbinhaus.planning.model.MainModel;
import link.ebbinhaus.planning.model.impl.MainModelImpl;
import link.ebbinhaus.planning.presenter.MainPresenter;
import link.ebbinhaus.planning.view.MainView;

/**
 * Created by WINFIELD on 2016/2/19.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private MainModel mainModel;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        mainModel = new MainModelImpl();
    }

    @Override
    public void cacheMainDrawerFragmentMap(SparseArray<Class> fm) {
        mainModel.cacheMainDrawerFragmentMap(fm);
    }
}
