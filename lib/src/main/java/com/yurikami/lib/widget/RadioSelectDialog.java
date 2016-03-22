package com.yurikami.lib.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yurikami.lib.R;
import com.yurikami.lib.util.DensityUtils;
import com.yurikami.lib.util.LogUtils;
import com.yurikami.lib.util.ViewGroupUtils;

/**
 * 带一个框的dialog,可通过setTitle设置标题
 */
public class RadioSelectDialog extends DialogFragment implements View.OnClickListener{
    public static final String KEY_TITLE = "Title";
    public static final String KEY_RADIO_TITLES = "RadioTitles";
    public static final String KEY_FIRST_INDEX = "FirstIndex";

    private OnRadioSelectListener mOnRadioSelectListener;
    private AlertDialog mAlertDialog;
    private RadioGroup mRadioGroup;

    public static RadioSelectDialog newInstance(String[] radioTitles, String title){
        return newInstance(radioTitles,title,0);
    }

    public static RadioSelectDialog newInstance(String[] radioTitles, String title,int firstIndex){
        RadioSelectDialog radioSelectDialog = new RadioSelectDialog();
        Bundle args = new Bundle();
        args.putStringArray(KEY_RADIO_TITLES, radioTitles);
        args.putString(KEY_TITLE, title);
        args.putInt(KEY_FIRST_INDEX, firstIndex);
        radioSelectDialog.setArguments(args);
        return radioSelectDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(mAlertDialog == null) {
            LogUtils.d("Radio", "onCreateDialog");
            String title = getArguments().getString(KEY_TITLE);
            String[] radioTitles = getArguments().getStringArray(KEY_RADIO_TITLES);
            int firstIndex = getArguments().getInt(KEY_FIRST_INDEX);

            FragmentActivity activity = getActivity();
            View v = activity.getLayoutInflater().inflate(R.layout.dialog_radio_select, null);

            mRadioGroup = (RadioGroup) v.findViewById(R.id.rg_dialog_select);
            ViewGroup.LayoutParams lp = ViewGroupUtils.genMatchWrapLP();
            int padding = DensityUtils.dp2px(getContext(), 20);    //TODO:换成软编码
            int textSize = DensityUtils.sp2px(getContext(), 5);
            for (int i = 0; i < radioTitles.length; i++) {
                RadioButton radioButton = new RadioButton(activity);
                radioButton.setText(radioTitles[i]);
                radioButton.setLayoutParams(lp);
                radioButton.setOnClickListener(this);
                radioButton.setId(i);   //必须设置id,不然显示会有问题
                radioButton.setTag(i);
                radioButton.setPadding(padding, padding, padding, padding);
                radioButton.setTextSize(textSize);
                radioButton.setChecked(i == firstIndex);
                mRadioGroup.addView(radioButton);
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setView(v)
                    .setTitle(title)
                    .setNegativeButton(getString(R.string.common_cancel), null);
            mAlertDialog = builder.create();
        }

        return mAlertDialog;
    }

    @Override
    public void onClick(View v) {
        ((RadioButton)v).setChecked(true);
        int index = (int) v.getTag();
        mOnRadioSelectListener.onRadioSelected(index);
    }

    public void setOnRadioSelectListener(OnRadioSelectListener l){
        mOnRadioSelectListener = l;
    }

    /**
     * radio选择时的监听器
     */
    public interface OnRadioSelectListener {

        /**
         * radio被选择时的回调函数
         * @param index 标题数组的索引
         */
        void onRadioSelected(int index);
    }
}
