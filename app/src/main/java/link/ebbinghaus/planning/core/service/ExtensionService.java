package link.ebbinghaus.planning.core.service;

import link.ebbinghaus.planning.common.callback.DataCallback;
import link.ebbinghaus.planning.core.model.vo.extension.douban.book.Result;

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
    void findBooks(String key,int start,DataCallback<Result> callback);
}
