package link.ebbinghaus.planning.core.service;

import com.yurikami.lib.net.NetCallback;
import okhttp3.Call;

/**
 * Created by WINFIELD on 2016/4/4.
 */
public interface ExtensionService {

    /**
     * 根据关键字查找图书
     * @param key 关键字
     * @param start 取结果的offset
     * @param callback 返回数据用的回调函数
     */
    Call findBooks(String key, int start, NetCallback callback);
}
