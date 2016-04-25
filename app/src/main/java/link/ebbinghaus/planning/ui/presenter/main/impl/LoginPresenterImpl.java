package link.ebbinghaus.planning.ui.presenter.main.impl;

import com.yurikami.lib.net.NetCallback;

import link.ebbinghaus.planning.common.constant.Constant;
import link.ebbinghaus.planning.core.model.server.po.User;
import link.ebbinghaus.planning.core.model.server.sys.Result;
import link.ebbinghaus.planning.core.service.MainService;
import link.ebbinghaus.planning.core.service.impl.MainServiceImpl;
import link.ebbinghaus.planning.ui.presenter.main.LoginPresenter;
import link.ebbinghaus.planning.ui.view.main.LoginView;
import okhttp3.Call;

/**
 * Created by WINFIELD on 2016/4/18.
 */
public class LoginPresenterImpl implements LoginPresenter,NetCallback {
    private LoginView mView;
    private MainService mMainService;

    private Call mLoginCall;
    private Call mRegisterCall;

    public LoginPresenterImpl(LoginView view) {
        this.mView = view;
        mMainService = new MainServiceImpl();
    }


    @Override
    public boolean validateName(String name) {
        if (name == null)
            return false;
        if (name.matches(Constant.REGEX_EMAIL))
            return true;
        else if (name.matches(Constant.REGEX_PHONENUMBER))
            return true;
        else
            return false;
    }

    @Override
    public boolean validatePassword(String password) {
        if (password == null)
            return false;
        if (password.length() >= 6)
            return true;
        else
            return false;
    }

    @Override
    public void login(String loginName, String password) {
        if (mLoginCall != null){
            mLoginCall.cancel();
        }
        mLoginCall = mMainService.login(loginName,password,this);
    }


    @Override
    public void onSuccess(Object result, Call call) {
        if (call == mLoginCall){
            Result<User> loginResult = (Result<User>) result;
            if (loginResult.getCode() == Result.RIGHT_CODE){
                //登录成功
                mMainService.updateUser(loginResult.getData());
            }else if (loginResult.getCode() == Result.DEFAULT_ERROR_CODE){
                //登录失败
            }
        }
        mLoginCall = null;
        mRegisterCall = null;
    }

    @Override
    public void onFailure(String errorMsg, Call call) {

    }
}
