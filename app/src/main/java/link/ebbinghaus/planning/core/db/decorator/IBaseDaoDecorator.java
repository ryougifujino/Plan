package link.ebbinghaus.planning.core.db.decorator;

import java.util.List;

/**
 * <strong>基本DaoDecorator接口,定义了通用的增删改查方法</strong><br>
 * <br>
 *     
 * 使用Decorator的目的:
 * 
 * 1. 隐藏一些Dao的底层方法<br>
 * 2. 组合Dao的方法构成新的方法,提高复用性<br>
 * 3. 为Dao的操作添加事务<br>
 * <br>
 *
 * 术语定义(与Dao一致)<br>
 * 增加对象 insert<br>
 * 删除对象 delete<br>
 * 修改对象 update<br>
 * 查询对象 select<br>
 * <br>
 * !在DaoDecorator的每个有增、删、改的方法中,记得使用事务<br>
 * !DaoDecorator实现类的方法通常会比接口定义的方法多<br>
 * !实际使用中直接使用相应的实现类,而不使用接口接收,这样方法会更完整<br>
 * !DaoDecorator代替Dao直接与外部交互
 * @param <T> 实体类型
 */
public interface IBaseDaoDecorator<T>{

    /* 单条记录操作 */

    /**
     * 增方法
     * @param t 欲添加的实体
     * @return 插入记录的id
     */
    long insert(T t);

    /**
     * 删方法
     * @param pk 要删除记录的主键
     */
    void deleteByPrimaryKey(Long pk);

    /**
     * 修改方法
     * @param t 修改信息,包含主键
     */
    void updateByPrimaryKey(T t);

    /**
     * 查找一条记录的方法
     * @param pk 要查找记录的主键
     * @return 返回查找到的结果的实体
     */
    T selectByPrimaryKey(Long pk);

    /* 批量记录操作 */

    /**
     * 批量增加
     * @param ts 要增加记录的实体集
     */
    void insertSome(List<T> ts);

    /**
     * 根据主键批量删除
     * @param pks 要删除记录的主键集
     */
    void deleteSomeByPrimaryKeys(List<Long> pks);

    /**
     * 根据主键批量修改
     * @param ts 要修改记录的实体集
     */
    void updateSomeByPrimaryKeys(List<T> ts);

    /* 所有记录操作 */

    /**
     * 删除所有记录
     * @return 删除的行数
     */
    int deleteAll();

    /**
     * 查找所有记录
     */
    List<T> selectAll();

    /* --- */

    /**
     * 关闭SQLiteDatabase
     */
    void closeDB();
}
