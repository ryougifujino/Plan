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
import android.widget.EditText;

import com.yurikami.lib.R;
import com.yurikami.lib.util.LogUtils;

/**
 * 带一个框的dialog,可通过setTitle设置标题
 */
public class SingleInputDialog extends DialogFragment
        implements DialogInterface.OnClickListener,TextWatcher{
    public static final String KEY_TITLE = "title";
    public static final String TAG = "com.yurikami.lib.widget.SingleInputDialog";

    private EditText mInputEt;
    private AlertDialog mDialog;

    private OnDialogConfirmListener mOnDialogConfirmListener;

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
        mInputEt.addTextChangedListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v)
                .setTitle(title)
                .setPositiveButton(getString(R.string.common_confirm), this)
                .setNegativeButton(getString(R.string.common_cancel), null);
        mDialog = builder.create();

        return mDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        setPositiveButtonEnable(
                !TextUtils.isEmpty( mInputEt.getText().toString().trim() )
        );
        LogUtils.d("bug2|onStart","called");
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
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
        setPositiveButtonEnable(!TextUtils.isEmpty(mInputEt.getText().toString()));
    }
    @Override
    public void afterTextChanged(Editable s) { }

    /**
     * 设置确认按钮是否点击，为了防止屏幕旋转后直接通过mPositiveButton获取不到而设置
     */
    private void setPositiveButtonEnable(boolean enabled) {
        mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(enabled);
    }


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
