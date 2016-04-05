package link.ebbinghaus.planning.ui.presenter.extension;

import link.ebbinghaus.planning.common.callback.DataCallback;
import link.ebbinghaus.planning.core.model.vo.extension.douban.book.Result;

/**
 * Created by WINFIELD on 2016/4/4.
 */
public interface ExtensionReadPresenter {

    /**
     * 根据关键字获取图书
     * @param key 关键字
     * @param start 取结果的offset
     * @param callback 数据回调函数
     */
    void obtainBooks(String key,int start,DataCallback<Result> callback);
}
