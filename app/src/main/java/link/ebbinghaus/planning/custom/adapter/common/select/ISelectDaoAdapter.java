package link.ebbinghaus.planning.custom.adapter.common.select;

import java.util.List;

/**
 * 在SelectActivity里面的RecyclerView所用到的Adapter,<br>
 * 在这个Adapter里用到的Dao的适配器;<br>
 * 这个适配器的作用是让SelectRecycleViewAdapter这个基类能尽可能的实现方法,<br>
 * 这样子类就只写很少的方法,解决了由于DaoDecorator之间Api不同导致了子类变得繁杂的问题
 */
public interface ISelectDaoAdapter<T>{

    List<T> selectAll();

    void deleteByPrimaryKey(Integer pk);

    void closeDB();
}
