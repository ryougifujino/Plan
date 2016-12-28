package com.yurikami.lib.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yurikami.lib.R;
import com.yurikami.lib.util.DensityUtils;
import com.yurikami.lib.util.LogUtils;
import com.yurikami.lib.util.ViewGroupUtils;
import com.yurikami.lib.util.ViewUtils;

/**
 * 单选dialog
 */
public class SingleSelectDialog extends DialogFragment implements View.OnClickListener{
    public static final String KEY_TITLE = "Title";
    public static final String KEY_OPTION_TITLES = "OptionTitles";
    public static final String KEY_FLAG = "Flag";

    private OnSelectListener mOnSelectListener;
    private AlertDialog mAlertDialog;
    private int mFlag;

    /**
     * 构造器
     * @param optionTitles 选项们的标题
     * @param title dialog的标题
     * @param flag 标记dialog
     * @return SingleSelectDialog实例
     */
    public static SingleSelectDialog newInstance(String[] optionTitles, String title,int flag){
        SingleSelectDialog singleSelectDialog = new SingleSelectDialog();
        Bundle args = new Bundle();
        args.putStringArray(KEY_OPTION_TITLES, optionTitles);
        args.putString(KEY_TITLE, title);
        args.putInt(KEY_FLAG,flag);
        singleSelectDialog.setArguments(args);
        return singleSelectDialog;
    }

    public static SingleSelectDialog newInstance(Context contexts,int[] optionTitlesRes, int titleRes,int flag){
        String[] optionTitles = new String[optionTitlesRes.length];
        int index = 0;
        for (int res : optionTitlesRes) {
            optionTitles[index++] = contexts.getString(res);
        }
        String title = contexts.getString(titleRes);
        return newInstance(optionTitles,title,flag);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(mAlertDialog == null) {
            LogUtils.d("SingleSelect", "onCreateDialog");
            String title = getArguments().getString(KEY_TITLE);
            String[] optionTitles = getArguments().getStringArray(KEY_OPTION_TITLES);
            mFlag = getArguments().getInt(KEY_FLAG);

            FragmentActivity activity = getActivity();
            View v = activity.getLayoutInflater().inflate(R.layout.dialog_single_select, null);
            LinearLayout optionContainerLl = (LinearLayout) v.findViewById(R.id.ll_dialog_single_select);
            ViewGroup.LayoutParams lp = ViewGroupUtils.genMatchWrapLP();
            int padding = DensityUtils.dp2px(getContext(), 17);    //TODO:换成软编码
            int textSize = DensityUtils.sp2px(getContext(), 7);
            for (int i = 0; i < optionTitles.length; i++) {
                TextView optionTv = new TextView(activity);
                optionTv.setText(optionTitles[i]);
                optionTv.setLayoutParams(lp);
                optionTv.setOnClickListener(this);
                optionTv.setId(i);
                optionTv.setTag(i);
                optionTv.setPadding(padding, padding, padding, padding);
                optionTv.setTextSize(textSize);
                ViewUtils.setAttrBackground(activity, optionTv, R.attr.selectableItemBackground);
                optionContainerLl.addView(optionTv);
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setView(v)
                    .setTitle(title)
                    .setNegativeButton(getString(R.string.common_cancel), null);
            mAlertDialog = builder.create();
        }

        return mAlertDialog;
    }

    public void show(FragmentActivity activity) {
        super.show(activity.getSupportFragmentManager(), this.getTag());
    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        mOnSelectListener.onSelected(index, mFlag);
    }

    public void setOnSelectListener(OnSelectListener l){
        mOnSelectListener = l;
    }

    /**
     * radio选择时的监听器
     */
    public interface OnSelectListener {

        /**
         * 被选择后的回调函数
         * @param index 标题数组的索引
         * @param flag 标记dialog
         */
        void onSelected(int index, int flag);
    }
}
