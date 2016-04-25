package link.ebbinghaus.planning.core.db.decorator.impl;

import link.ebbinghaus.planning.core.db.dao.DefaultInputValueDao;
import link.ebbinghaus.planning.core.model.local.po.DefaultInputValue;

/**
 * DefaultInputValueDao的包装器类
 */
public class DefaultInputValueDaoDecorator extends BaseDaoDecorator<DefaultInputValue> {
    private DefaultInputValueDao dao;

    public DefaultInputValueDaoDecorator() {
        super(new DefaultInputValueDao());
        dao = (DefaultInputValueDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }
}
