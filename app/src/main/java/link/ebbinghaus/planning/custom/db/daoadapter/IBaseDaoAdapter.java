package link.ebbinghaus.planning.custom.db.daoadapter;

import java.util.List;

/**
 * 基本DaoAdapter接口,定义了通用的增删改查方法
 *
 * 使用DaoAdapter比直接使用Dao封装性要更好,方法暴露更加正确;
 * 使用DaoAdapter可以组合使用对应的Dao中的方法,实在没有再向对应的Dao中添加方法;
 *
 * 术语定义
 * 增加对象 add
 * 删除对象 remove
 * 修改对象 modify
 * 查询对象 find
 *
 * !在DaoAdapter的每个有增、删、改的方法中,记得使用事务
 * !DaoAdapter中的每个方法都建议开启子线程进行执行
 * !DaoAdapter实现类的方法通常会比接口定义的方法多
 * !实际使用中直接使用相应的实现类,而不使用接口接收,这样方法会更完整
 * !DaoAdapter代替Dao直接与外部交互
 * @param <T> 实体类型
 */
public interface IBaseDaoAdapter<T>{

    /* 单条记录操作 */

    /**
     * 增方法
     * @param t 欲添加的实体
     */
    void add(T t);

    /**
     * 删方法
     * @param pk 要删除记录的主键
     */
    void removeByPrimaryKey(Integer pk);

    /**
     * 修改方法
     * @param t 修改信息,包含主键
     */
    void modifyByPrimaryKey(T t);

    /**
     * 查找一条记录的方法
     * @param pk 要查找记录的主键
     * @return 返回查找到的结果的实体
     */
    T findByPrimaryKey(Integer pk);

    /* 批量记录操作 */

    /**
     * 批量增加
     * @param ts 要增加记录的实体集
     */
    void addSome(List<T> ts);

    /**
     * 根据主键批量删除
     * @param pks 要删除记录的主键集
     */
    void removeSomeByPrimaryKeys(List<Integer> pks);

    /**
     * 根据主键批量修改
     * @param ts 要修改记录的实体集
     */
    void modifySomeByPrimaryKeys(List<T> ts);

    /* 所有记录操作 */

    /**
     * 删除所有记录
     */
    void removeAll();

    /**
     * 查找所有记录
     */
    List<T> findAll();

    /* --- */

    /**
     * 关闭SQLiteDatabase
     */
    void closeDB();
}
