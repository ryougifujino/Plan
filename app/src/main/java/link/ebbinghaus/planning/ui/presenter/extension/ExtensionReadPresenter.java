package link.ebbinghaus.planning.ui.presenter.extension;

import com.yurikami.lib.net.NetCallback;
import okhttp3.Call;

/**
 * Created by WINFIELD on 2016/4/4.
 */
public interface ExtensionReadPresenter {

    /**
     * 根据关键字获取图书
     * @param key 关键字
     * @param start 取结果的offset
     * @param callback 数据回调函数
     * @return 网络连接
     */
    Call obtainBooks(String key, int start, NetCallback callback);
}
