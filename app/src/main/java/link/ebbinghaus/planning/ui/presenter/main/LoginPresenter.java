package link.ebbinghaus.planning.ui.presenter.main;

/**
 * Created by WINFIELD on 2016/4/18.
 */
public interface LoginPresenter {

    /**
     * 验证登录/注册名
     * @param name 登陆/注册名
     * @return 是否验证通过
     */
    boolean validateName(String name);

    /**
     * 验证密码，至少6位
     * @param password 密码
     * @return 是否验证通过
     */
    boolean validatePassword(String password);

    /**
     * 用户登录
     * @param loginName 用户名
     * @param password 密码
     * @return 登录的网络连接
     */
    void login(String loginName, String password);

}
