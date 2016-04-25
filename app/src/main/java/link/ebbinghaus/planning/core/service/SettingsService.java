package link.ebbinghaus.planning.core.service;

import link.ebbinghaus.planning.core.model.local.vo.SettingsVo;

/**
 * Created by WINFIELD on 2016/4/5.
 */
public interface SettingsService {

    /**
     * 查找默认值
     * @param settings 存放默认值的对象
     */
    void findDefaultInputValue(SettingsVo settings);

    /**
     * 保存默认值
     * @param settings 存放默认值的对象
     */
    void updateDefaultInputValue(SettingsVo settings);
}
