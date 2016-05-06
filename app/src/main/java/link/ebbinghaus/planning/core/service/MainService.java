package link.ebbinghaus.planning.core.service;

import android.util.SparseArray;

import com.yurikami.lib.net.NetCallback;

import link.ebbinghaus.planning.core.model.server.po.User;
import okhttp3.Call;

/**
 * Created by WINFIELD on 2016/2/19.
 */
public interface MainService {

    void cacheMainDrawerFragmentMap(SparseArray<Class> fm);

    /**
     * 登录
     * @param loginName 登录名，手机号或邮箱
     * @param password 加密后的密码
     * @param callback 网络回调函数
     * @return 本次登录的连接
     */
    Call login(String loginName, String password, NetCallback callback);

    /**
     * 保存或更新user信息到SharedPreference中
     * @param user 需要保存或更新的user
     */
    void updateUser(User user);

    /**
     * 注册
     * @param registerName 注册名，手机号或邮箱
     * @param password 注册密码
     * @param callback 网络回调函数
     * @return 本次注册的连接
     */
    Call register(String registerName, String password, NetCallback callback);

    /**
     * 更新计划进程以及因日期改变产生的相关变化
     */
    void updateEventsProcessAndRelated();
}
