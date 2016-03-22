package link.ebbinghaus.planning.custom.adapter.common.select;

import java.util.List;

import link.ebbinghaus.planning.custom.db.decorator.IBaseDaoDecorator;

/**
 * ISelectDaoAdapter的默认实现
 */
public class DefaultSelectDaoAdapter<T> implements ISelectDaoAdapter<T>{
    private IBaseDaoDecorator<T> baseDao;

    public DefaultSelectDaoAdapter(IBaseDaoDecorator baseDao) {
        this.baseDao = baseDao;
    }


    @Override
    public List<T> selectAll() {
        return baseDao.selectAll();
    }

    @Override
    public void deleteByPrimaryKey(Long pk) {
        baseDao.deleteByPrimaryKey(pk);
    }

    @Override
    public void insert(T t) {
        baseDao.insert(t);
    }

    @Override
    public void closeDB() {
        baseDao.closeDB();
    }
}
