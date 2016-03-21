package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import java.util.List;

import link.ebbinghaus.planning.custom.db.dao.BaseDao;
import link.ebbinghaus.planning.custom.db.daoadapter.IBaseDaoAdapter;

//TODO: 操作数据库改为多线程,在DaoAdapter的方法层面上进行使用
/**
 * DaoAdapter的基类
 * @param <T>
 */
public class BaseDaoAdapter<T> implements IBaseDaoAdapter<T>{
    protected BaseDao<T> dao;

    public BaseDaoAdapter(BaseDao<T> dao){
        this.dao = dao;
    }

    @Override
    public void add(T t) {
        dao.beginTransaction();
        try {
            dao.insert(t);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        dao.beginTransaction();
        try {
            dao.deleteByPrimaryKey(pk);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public void modifyByPrimaryKey(T t) {
        dao.beginTransaction();
        try {
            dao.updateByPrimaryKey(t);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public T findByPrimaryKey(Integer pk) {
        return dao.selectByPrimaryKey(pk);
    }

    @Override
    public void addSome(List<T> ts) {
        dao.beginTransaction();
        try {
            dao.insertSome(ts);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public void removeSomeByPrimaryKeys(List<Integer> pks) {
        dao.beginTransaction();
        try {
            dao.deleteSomeByPrimaryKeys(pks);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public void modifySomeByPrimaryKeys(List<T> ts) {
        dao.beginTransaction();
        try {
            dao.updateSomeByPrimaryKeys(ts);
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public void removeAll() {
        dao.beginTransaction();
        try {
            dao.deleteAll();
            dao.setTransactionSuccessful();
        }finally {
            dao.endTransaction();
        }
    }

    @Override
    public List<T> findAll() {
        return dao.selectAll();
    }

    /**
     * !因为实际上父类和子类用的是同一个new出来的Dao实例,所以子类不必再在方法中close本Dao
     */
    @Override
    public void closeDB() {
        dao.close();
    }
}
