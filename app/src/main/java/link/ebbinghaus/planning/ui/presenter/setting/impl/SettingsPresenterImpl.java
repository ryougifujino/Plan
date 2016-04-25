package link.ebbinghaus.planning.ui.presenter.setting.impl;

import link.ebbinghaus.planning.core.model.local.vo.SettingsVo;
import link.ebbinghaus.planning.core.service.SettingsService;
import link.ebbinghaus.planning.core.service.impl.SettingsServiceImpl;
import link.ebbinghaus.planning.ui.presenter.setting.SettingsPresenter;
import link.ebbinghaus.planning.ui.view.setting.SettingsView;

/**
 * Created by WINFIELD on 2016/4/5.
 */
public class SettingsPresenterImpl implements SettingsPresenter {
    private SettingsView mView;
    private SettingsService mSettingsService;

    public SettingsPresenterImpl(SettingsView view) {
        this.mView = view;
        mSettingsService = new SettingsServiceImpl();
    }

    @Override
    public void initViewData(SettingsVo settings) {
        mSettingsService.findDefaultInputValue(settings);
        mView.setInitData();
    }

    @Override
    public void saveViewData(SettingsVo settings) {
        mSettingsService.updateDefaultInputValue(settings);
    }
}
