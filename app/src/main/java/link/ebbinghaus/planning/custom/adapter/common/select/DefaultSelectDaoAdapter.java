package link.ebbinghaus.planning.custom.adapter.common.select;

import java.util.List;

import link.ebbinghaus.planning.custom.db.decorator.IBaseDaoDecorator;

/**
 * ISelectDaoAdapter的默认实现
 */
public class DefaultSelectDaoAdapter implements ISelectDaoAdapter{
    private IBaseDaoDecorator baseDao;

    public DefaultSelectDaoAdapter(IBaseDaoDecorator baseDao) {
        this.baseDao = baseDao;
    }


    @Override
    public List selectAll() {
        return baseDao.selectAll();
    }

    @Override
    public void deleteByPrimaryKey(Integer pk) {
        baseDao.deleteByPrimaryKey(pk);
    }

    @Override
    public void closeDB() {
        baseDao.closeDB();
    }
}
