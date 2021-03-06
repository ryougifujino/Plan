package link.ebbinghaus.planning.ui.adapter.common.select;

import java.util.List;

/**
 * 在SelectActivity里面的RecyclerView所用到的Adapter,<br>
 * 在这个Adapter里用到的Dao的适配器;<br>
 * 这个适配器的作用是让SelectRecycleViewAdapter这个基类能尽可能的实现方法,<br>
 * 这样子类就只写很少的方法,解决了由于DaoDecorator之间Api不同导致了子类变得繁杂的问题<br>
 * <p>!DaoAdapter实现了方法级别的数据库关闭</p>
 *
 */
public interface ISelectDaoAdapter<T>{

    List<T> selectAll();

    void deleteByPrimaryKey(Long pk);

    void insert(T t);

}
