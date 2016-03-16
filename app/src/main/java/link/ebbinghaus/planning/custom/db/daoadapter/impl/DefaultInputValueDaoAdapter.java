package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import link.ebbinghaus.planning.custom.db.dao.DefaultInputValueDao;
import link.ebbinghaus.planning.custom.db.daoadapter.BaseDaoAdapter;
import link.ebbinghaus.planning.model.entity.po.DefaultInputValue;

/**
 * DefaultInputValueDao的包装器类
 */
public class DefaultInputValueDaoAdapter implements BaseDaoAdapter<DefaultInputValue> {
    private DefaultInputValueDao dao;

    public DefaultInputValueDaoAdapter() {
        dao = new DefaultInputValueDao();
    }

    @Override
    public void add(DefaultInputValue defaultInputValue) {
        dao.add(defaultInputValue);
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        dao.removeByPrimaryKey(pk);
    }

    @Override
    public void modifyByPrimaryKey(DefaultInputValue defaultInputValue) {
        dao.modifyByPrimaryKey(defaultInputValue);
    }

    @Override
    public DefaultInputValue findByPrimaryKey(Integer pk) {
        return dao.findByPrimaryKey(pk);
    }

    @Override
    public void closeDB() {
        dao.closeDB();
    }
}
