package link.ebbinghaus.planning.custom.db.decorator.impl;

import java.util.List;

import link.ebbinghaus.planning.custom.db.dao.BaseDao;
import link.ebbinghaus.planning.custom.db.decorator.IBaseDaoDecorator;

/**
 * DaoDecorator的基类
 * @param <T>
 */
public class BaseDaoDecorator<T> implements IBaseDaoDecorator<T> {
    protected BaseDao<T> dao;

    public BaseDaoDecorator(BaseDao<T> dao){
        this.dao = dao;
    }

    @Override
    public long insert(T t) {
        long rowId = -1L;
        dao.beginTransaction();
        try {
            rowId = dao.insert(t);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
        return rowId;
    }

    @Override
    public void deleteByPrimaryKey(Long pk) {
        dao.beginTransaction();
        try {
            dao.deleteByPrimaryKey(pk);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public void updateByPrimaryKey(T t) {
        dao.beginTransaction();
        try {
            dao.updateByPrimaryKey(t);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public T selectByPrimaryKey(Long pk) {
        return dao.selectByPrimaryKey(pk);
    }

    @Override
    public void insertSome(List<T> ts) {
        dao.beginTransaction();
        try {
            dao.insertSome(ts);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public void deleteSomeByPrimaryKeys(List<Long> pks) {
        dao.beginTransaction();
        try {
            dao.deleteSomeByPrimaryKeys(pks);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public void updateSomeByPrimaryKeys(List<T> ts) {
        dao.beginTransaction();
        try {
            dao.updateSomeByPrimaryKeys(ts);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public int deleteAll() {
        int affectedRow = 0;
        dao.beginTransaction();
        try {
            affectedRow = dao.deleteAll();
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
        return affectedRow;
    }

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    /**
     * !因为实际上父类和子类用的是同一个new出来的Dao实例,所以子类不必再在方法中close本Dao
     */
    @Override
    public void closeDB() {
        dao.closeDB();
    }
}
