package link.ebbinghaus.planning.presenter.impl;

import android.util.SparseArray;

import link.ebbinghaus.planning.model.MainModel;
import link.ebbinghaus.planning.model.impl.MainModelImpl;
import link.ebbinghaus.planning.presenter.MainPresenter;
import link.ebbinghaus.planning.view.activity.MainView;

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
