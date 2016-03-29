package com.yurikami.lib.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yurikami.lib.R;

/**
 * 带一个框的dialog,可通过setTitle设置标题
 */
public class SingleInputDialog extends DialogFragment
        implements DialogInterface.OnShowListener,View.OnClickListener,TextWatcher{
    public static final String KEY_TITLE = "title";

    private EditText mInputEt;
    private OnDialogConfirmListener mOnDialogConfirmListener;
    private Button mPositiveButton;
    private AlertDialog mDialog;

    public static SingleInputDialog newInstance(String title){
        SingleInputDialog singleInputDialog = new SingleInputDialog();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        singleInputDialog.setArguments(args);
        return singleInputDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString(KEY_TITLE);
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_single_input, null);
        mInputEt = (EditText) v.findViewById(R.id.et_dialog_single_input);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v)
                .setTitle(title)
                .setPositiveButton(getString(R.string.common_confirm), null)
                .setNegativeButton(getString(R.string.common_cancel), null);
        mDialog = builder.create();
        mDialog.setOnShowListener(this);

        mInputEt.addTextChangedListener(this);
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
        if(mOnDialogConfirmListener == null){
            throw new IllegalStateException("确认按钮的监听器必须被注册");
        }
        mOnDialogConfirmListener.onDialogConfirm(mInputEt.getText().toString());
        mDialog.dismiss();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String content = mInputEt.getText().toString();
        if(TextUtils.isEmpty(content.trim())){
            mPositiveButton.setEnabled(false);      //FIXME:若要适配横屏,则这里要修复
        }else{
            mPositiveButton.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) { }

    public void setOnDialogConfirmListener(OnDialogConfirmListener l){
        mOnDialogConfirmListener = l;
    }
    /**
     * 确认按钮的监听器
     */
    public interface OnDialogConfirmListener {

        /**
         * 点击确认按钮时的回调函数
         * @param content 添加输入框里的内容
         */
        void onDialogConfirm(String content);
    }
}
