package link.ebbinghaus.planning.ui.view.main;

/**
 * Created by WINFIELD on 2016/4/5.
 */
public interface LoginView {

    /**
     * 显示注册成功的提示
     * @param successMsg 注册成功信息
     */
    void showRegisterSuccessHint(String successMsg);

    /**
     * 显示注册失败的提示
     * @param failureMsg 注册失败信息
     */
    void showRegisterFailureHint(String failureMsg);

    /**
     * 显示登陆成功提示
     * @param successMsg 成功登陆信息
     */
    void showLoginSuccessHint(String successMsg);

    /**
     * 退出登录界面
     */
    void exitLoginView();

    /**
     * 显示登陆失败的提示
     * @param failureMsg 登陆失败信息
     */
    void showLoginFailureHint(String failureMsg);
}
