package link.ebbinghaus.planning.ui.view.main.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.util.StringUtils;
import com.yurikami.lib.widget.TextObserver;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.util.CommonUtils;
import link.ebbinghaus.planning.ui.presenter.main.LoginPresenter;
import link.ebbinghaus.planning.ui.presenter.main.impl.LoginPresenterImpl;
import link.ebbinghaus.planning.ui.view.main.LoginView;

public class LoginActivity extends BaseActivity implements LoginView,
        View.OnClickListener{

    private static final int FLAG_ACCOUNT = 0;
    private static final int FLAG_PASSWORD = 1;

    @Bind(R.id.tb_common_head) Toolbar mToolbar;
    @Bind(R.id.et_login_account) EditText accountEt;
    @Bind(R.id.til_login_account) TextInputLayout accountTil;
    @Bind(R.id.et_login_password) EditText passwordEt;
    @Bind(R.id.til_login_password) TextInputLayout passwordTil;
    @Bind(R.id.btn_login_sign_in) TextView signInBtn;
    @Bind(R.id.btn_login_register) TextView registerBtn;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();


    }

    private void init() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenterImpl(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitle(R.string.login_title);

        signInBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        accountEt.addTextChangedListener(new TextChangedListener(FLAG_ACCOUNT));
        passwordEt.addTextChangedListener(new TextChangedListener(FLAG_PASSWORD));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_sign_in:
                mPresenter.login(accountEt.getText().toString(),passwordEt.getText().toString());
                break;
            case R.id.btn_login_register:
                mPresenter.register(accountEt.getText().toString(),passwordEt.getText().toString());
                break;
        }
    }

    @Override
    public void showRegisterSuccessHint(String successMsg) {
        CommonUtils.showLongToast(successMsg);
    }

    @Override
    public void showRegisterFailureHint(String failureMsg) {
        CommonUtils.showLongToast(failureMsg);
    }

    @Override
    public void showLoginSuccessHint(String successMsg) {
        CommonUtils.showLongToast(successMsg);
    }

    @Override
    public void exitLoginView() {
        finish();
    }

    @Override
    public void showLoginFailureHint(String failureMsg) {
        CommonUtils.showLongToast(failureMsg);
    }


    class TextChangedListener extends TextObserver{

        public TextChangedListener(int editTextFlag) {
            super(editTextFlag);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            switch (mEditTextFlag){
                case FLAG_ACCOUNT:
                    if (!mPresenter.validateName(s.toString())){
                        accountTil.setError(getString(R.string.login_account_error));
                    }else {
                        //必须先null再false才能有toggle的效果
                        accountTil.setError(null);
                        accountTil.setErrorEnabled(false);
                    }

                    break;
                case FLAG_PASSWORD:
                    if (!mPresenter.validatePassword(s.toString())){
                        passwordTil.setError(getString(R.string.login_password_error));
                    }else {
                        passwordTil.setError(null);
                        passwordTil.setErrorEnabled(false);
                    }
                    break;
            }
            boolean btnEnable = !accountTil.isErrorEnabled() && !passwordTil.isErrorEnabled()
                    && !StringUtils.isAnyEmpty(accountEt.getText().toString(),passwordEt.getText().toString());
            signInBtn.setEnabled(btnEnable);
            registerBtn.setEnabled(btnEnable);
            signInBtn.setAlpha(btnEnable ? 1.0f : 0.5f);
            registerBtn.setAlpha(btnEnable ? 1.0f : 0.5f);
        }
    }

}
