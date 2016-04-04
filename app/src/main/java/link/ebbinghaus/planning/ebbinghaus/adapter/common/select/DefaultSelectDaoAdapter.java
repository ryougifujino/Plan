package link.ebbinghaus.planning.ebbinghaus.adapter.common.select;

import java.util.List;

import link.ebbinghaus.planning.ebbinghaus.db.decorator.IBaseDaoDecorator;

/**
 * ISelectDaoAdapter的默认实现
 */
@SuppressWarnings({"TryWithIdenticalCatches", "ConstantConditions"})
public class DefaultSelectDaoAdapter<T> implements ISelectDaoAdapter<T>{
    private Class<? extends IBaseDaoDecorator<T>> daoClass;

    public DefaultSelectDaoAdapter(Class<? extends IBaseDaoDecorator<T>> daoClass) {
        this.daoClass = daoClass;
    }


    @Override
    public List<T> selectAll() {
        IBaseDaoDecorator<T> dao = null;
        try {
            dao = daoClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        List<T> ts = dao.selectAll();
        dao.closeDB();
        return ts;
    }

    @Override
    public void deleteByPrimaryKey(Long pk) {
        IBaseDaoDecorator<T> dao = null;
        try {
            dao = daoClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        dao.deleteByPrimaryKey(pk); //TODO:删除计划组和子类型的时候,要把相应的event置null
        dao.closeDB();
    }

    @Override
    public void insert(T t) {
        IBaseDaoDecorator<T> dao = null;
        try {
            dao = daoClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        dao.insert(t);
        dao.closeDB();
    }

}
