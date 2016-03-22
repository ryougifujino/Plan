package link.ebbinghaus.planning.view.fragment.impl;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinhaus.planning.R;

/**
 * Created by WINFIELD on 2016/3/21.
 */
public class CommonSelectDialogFragment extends DialogFragment
        implements DialogInterface.OnShowListener,View.OnClickListener,TextWatcher{
    @Bind(R.id.et_common_select_add_input) EditText mAddInputEt;
    private OnCreateButtonClickListener mOnCreateButtonClickListener;
    private Button mPositiveButton;
    private AlertDialog mDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_common_select, null);
        ButterKnife.bind(this, v);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v)
                .setTitle(getString(R.string.common_select_add_dialog_title))
                .setPositiveButton(getString(R.string.common_create), null)
                .setNegativeButton(getString(R.string.common_cancel), null);
        mDialog = builder.create();
        mDialog.setOnShowListener(this);

        mAddInputEt.addTextChangedListener(this);
        return mDialog;
    }


    @Override
    public void onShow(DialogInterface dialog) {
        mPositiveButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
        mPositiveButton.setEnabled(false);
        mPositiveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(mOnCreateButtonClickListener == null){
            throw new IllegalStateException("创建按钮的监听器必须被注册");
        }
        mOnCreateButtonClickListener.onCreateButtonClick(mAddInputEt.getText().toString());
        mDialog.dismiss();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String content = mAddInputEt.getText().toString();
        if(content.length() == 0){
            mPositiveButton.setEnabled(false);      //FIXME:若要适配横屏,则这里要修复
        }else{
            mPositiveButton.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) { }

    public void setOnCreateButtonClickListener(OnCreateButtonClickListener l){
        mOnCreateButtonClickListener = l;
    }
    /**
     * 创建按钮的监听器
     */
    public interface OnCreateButtonClickListener{

        /**
         * 点击创建按钮时的回调函数
         * @param content 添加输入框里的内容
         */
        void onCreateButtonClick(String content);
    }
}
