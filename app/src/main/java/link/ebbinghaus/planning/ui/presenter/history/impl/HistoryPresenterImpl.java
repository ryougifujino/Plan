package link.ebbinghaus.planning.ui.presenter.history.impl;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.service.HistoryService;
import link.ebbinghaus.planning.core.service.impl.HistoryServiceImpl;
import link.ebbinghaus.planning.ui.presenter.history.HistoryPresenter;
import link.ebbinghaus.planning.ui.view.history.HistoryView;

/**
 * Created by WINFIELD on 2016/4/5.
 */
public class HistoryPresenterImpl implements HistoryPresenter {

    private HistoryView mView;
    private HistoryService mHistoryService;

    public HistoryPresenterImpl(HistoryView view) {
        this.mView = view;
        mHistoryService = new HistoryServiceImpl();
    }


    @Override
    public List<Event> obtainSpecDoneEvents() {
        return mHistoryService.findAllDoneSpecEvents();
    }

    @Override
    public List<Event> obtainSpecExpiredEvents() {
        return mHistoryService.findAllExpiredSpecEvents();
    }
}
