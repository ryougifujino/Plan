package link.ebbinghaus.planning.ui.view.common;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public interface CommonSelectView {

    /**
     * 对toolbar进行设置
     */
    void setToolbar();

    /**
     * 初始化点击toolbar上的+按钮后弹出的dialog
     */
    void initToolbarAddDialog();

    /**
     * 把发送者发送过来的数据设置到成员变量中
     */
    void setSenderData();

    /**
     * 选择RecyclerViewAdapter
     */
    void chooseRecyclerViewAdapter();

    /**
     * 对RecyclerView相关的内容(如Adapter,LayoutManager之类的)进行设置
     */
    void setRecyclerView();

    /**
     * 设置创建按钮的监听器
     */
    void setOnCreateButtonClickListener();
}
