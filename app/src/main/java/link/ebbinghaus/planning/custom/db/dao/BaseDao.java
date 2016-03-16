package link.ebbinghaus.planning.custom.db.dao;

import android.database.sqlite.SQLiteDatabase;

import link.ebbinghaus.planning.custom.db.DBHelper;
import link.ebbinghaus.planning.custom.db.daoadapter.BaseDaoAdapter;
import link.ebbinghaus.planning.custom.other.App;

/**
 * Dao的操作术语为增add 删remove 改modify 查find
 * Dao系列的方法都作为底层类,外部使用DaoAdapter
 * @param <T> Dao所对应的类型
 */
public abstract class BaseDao<T> implements BaseDaoAdapter<T> {
    protected SQLiteDatabase db;

    public BaseDao() {
        db = DBHelper.getInstance(App.getContext()).getWritableDatabase();
    }

    /**
     * Dao使用后必须调用此方法
     */
    public void closeDB(){
        db.close();
    }

    /**
     * 建议外部调用,作为基础增删改查的方法之一
     * 根据实体添加(携带事务)
     * @param t 实体
     */
    @Override
    public void add(T t){
        db.beginTransaction();
        try {
            insert(t);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    /**
     * !不建议外部调用
     * 根据条件删除(携带事务)
     * @param where where条件,参数用?代替
     * @param args where条件的参数
     */
    protected void remove(String where,String[] args){
        db.beginTransaction();
        try {
            delete(where, args);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    /**
     * !不建议外部调用
     * 根据实体更改(携带事务)
     * @param t 实体
     * @param where where条件,参数用?代替
     * @param args where条件的参数
     */
    protected void modify(T t, String where, String[] args){
        db.beginTransaction();
        try {
            update(t, where, args);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }


    /**
     * !不建议外部调用
     * 辅助方法,不携带事务
     * @param t
     */
    protected abstract void insert(T t);
    protected abstract void delete(String where, String[] args);
    protected abstract void update(T t, String where, String[] args);

}
