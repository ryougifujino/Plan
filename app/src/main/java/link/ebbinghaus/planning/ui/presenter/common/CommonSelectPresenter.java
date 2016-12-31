package link.ebbinghaus.planning.ui.presenter.common;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public interface CommonSelectPresenter {

    /**
     * 对toolbar进行设置
     */
    void configureToolbar(boolean isInstanceSaved);

    /**
     * 获取并设置发送者发过来的数据
     */
    void getAndSetSenderData();

    /**
     * 对RecyclerView进行配置
     */
    void configureRecyclerView();

}
