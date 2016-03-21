package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import link.ebbinghaus.planning.custom.db.dao.DefaultInputValueDao;
import link.ebbinghaus.planning.model.entity.po.DefaultInputValue;

/**
 * DefaultInputValueDao的包装器类
 */
public class DefaultInputValueDaoAdapter extends BaseDaoAdapter<DefaultInputValue> {
    private DefaultInputValueDao dao;

    public DefaultInputValueDaoAdapter() {
        super(new DefaultInputValueDao());
        dao = (DefaultInputValueDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }
}
