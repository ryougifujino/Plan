package link.ebbinghaus.planning.common.callback;

/**
 * model层会用到的回调函数
 * @param <T>
 */
public interface DataCallback<T> {

    void onSuccess(T t);

    void onFailure(String msg);
}
