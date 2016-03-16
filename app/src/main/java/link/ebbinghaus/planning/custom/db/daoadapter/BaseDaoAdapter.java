package link.ebbinghaus.planning.custom.db.daoadapter;

/**
 * 基本Dao适配器,定义了最基础的增删改查方法
 * 使用DaoAdapter比直接使用Dao封装性要更好,方法暴露更加正确;
 * DaoAdapter类至少定义了基础的增删改查;
 * 可以子类添加除了基础增删改查以外的其他方法.
 * @param <T> 实体类型
 */
public interface BaseDaoAdapter <T>{

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

    /**
     * 关闭SQLiteDatabase
     */
    void closeDB();
}
