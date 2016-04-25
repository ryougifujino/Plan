package link.ebbinghaus.planning.core.service.impl;

import java.util.List;

import link.ebbinghaus.planning.core.db.decorator.impl.DefaultInputValueDaoDecorator;
import link.ebbinghaus.planning.core.model.local.po.DefaultInputValue;
import link.ebbinghaus.planning.core.model.local.vo.SettingsVo;
import link.ebbinghaus.planning.core.service.SettingsService;

/**
 * Created by WINFIELD on 2016/4/5.
 */
public class SettingsServiceImpl implements SettingsService {

    @Override
    public void findDefaultInputValue(SettingsVo settings) {
        if (settings == null)
            return;
        DefaultInputValueDaoDecorator dao = new DefaultInputValueDaoDecorator();
        List<DefaultInputValue> defaultInputValues = dao.selectAll();
        if (defaultInputValues.size() > 0){
            settings.defaultInputValue = defaultInputValues.get(0);
        }
        dao.closeDB();
    }

    @Override
    public void updateDefaultInputValue(SettingsVo settings) {
        if (settings == null)
            return;
        DefaultInputValueDaoDecorator dao = new DefaultInputValueDaoDecorator();
        dao.updateByPrimaryKey(settings.defaultInputValue);
        dao.closeDB();
    }
}
