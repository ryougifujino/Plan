package link.ebbinghaus.planning.ui.presenter.setting;

import link.ebbinghaus.planning.core.model.local.vo.SettingsVo;

/**
 * Created by WINFIELD on 2016/4/5.
 */
public interface SettingsPresenter {

    /**
     * 初始化本页面所显示的数据
     * @param settings
     */
    void initViewData(SettingsVo settings);

    /**
     * 保存本页面的数据
     * @param settings
     */
    void saveViewData(SettingsVo settings);
}
