package link.ebbinghaus.planning.custom.db.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import link.ebbinghaus.planning.custom.db.DBManager;

/**
 * 此基类定义和实现了一些通用方法,Dao底层方法
 *
 * 通用方法分别为:
 * ①对单记录以主键为依据的增、删、改、查
 * ②批量对记录进行增、删
 *
 * Dao的操作术语为增insert、删delete、改update、查select,
 * 这个系列的通用方法的实现,可以调用Dao的底层方法_insert...系列,也可以直接操作db,也可以互相调用,
 * 其中_insert系列的底层方法最优先被调用,其次是互相调用,再其次是db(从效率和简洁性综合考虑);
 *
 * !Dao系列的方法都作为底层类,不直接与外部交互(至于相应的DaoAdapter交互)
 * !一个Dao对应一个DaoAdapter包装类
 * !Dao系列的类不使用事务,事务在Dao所对应的DaoAdapter以方法为范畴使用事务
 * !protected表示将被子类访问或设置,public则表示是用来暴露给外部(对象的DaoAdapter)使用的
 * @param <T> Dao所对应的实体类型
 */
public abstract class BaseDao<T> {
    protected SQLiteDatabase db;
    protected String pkColumn;

    public BaseDao(String pkColumn) {
        db = DBManager.getInstance().openDB();
        this.pkColumn = pkColumn;
    }

    /**
     * 底层抽象方法增删改(没有查找,查找直接用sql语句实现),由子类实现后可以确定对哪张表进行操作;
     * 这个系列的底层方法可以由insert delete update select开头的系列方法调用,
     * 比起直接使用db,操作要简化一些
     */
    protected abstract void _insert(T t);
    protected abstract void _delete(String where, String[] args);
    protected abstract void _update(T t, String where, String[] args);

    /**
     * 以下按顺序为单条记录操作,批量操作,所有记录操作,
     * 能实现则直接实现,不能实现则设置为抽象方法
     */
     /* 单条记录操作 */

    /**
     * 增方法
     * @param t 欲添加的实体
     */
    public void insert(T t){
        _insert(t);
    }

    /**
     * 删方法
     * @param pk 要删除记录的主键
     */
    public void deleteByPrimaryKey(Integer pk){
        String whereClause = pkColumn + " = ?";
        _delete(whereClause, new String[]{pk.toString()});
    }

    /**
     * 修改方法
     * @param t 修改信息,包含主键
     */
    public abstract void updateByPrimaryKey(T t);

    /**
     * 查找一条记录的方法
     * @param pk 要查找记录的主键
     * @return 返回查找到的结果的实体
     */
    public abstract T selectByPrimaryKey(Integer pk);

    /* 批量记录操作 */

    /**
     * 批量增加
     * @param ts 要增加记录的实体集
     */
    public void insertSome(List<T> ts){
        for (T t:ts){
            _insert(t);
        }
    }

    /**
     * 根据主键批量删除
     * @param pks 要删除记录的主键集
     */
    public void deleteSomeByPrimaryKeys(List<Integer> pks){
        for (Integer pk: pks){
            deleteByPrimaryKey(pk);
        }
    }

    /**
     * 根据主键批量修改
     * @param ts 要修改记录的实体集
     */
    public void updateSomeByPrimaryKeys(List<T> ts){
        for (T t:ts){
            updateByPrimaryKey(t);
        }
    }

    /* 所有记录操作 */

    /**
     * 删除所有记录
     */
    public void deleteAll(){
        _delete(null, null);
    }

    /**
     * 查找所有记录
     */
    public abstract List<T> selectAll();

    /* --- */


    /**
     * Dao使用后必须调用此方法
     */
    public void close(){
        DBManager.getInstance().closeDB();
    }

    public void beginTransaction(){
        db.beginTransaction();
    }
    public void setTransactionSuccessful(){
        db.setTransactionSuccessful();
    }
    public void endTransaction(){
        db.endTransaction();
    }
}
