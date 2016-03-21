package com.yurikami.lib.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.yurikami.lib.util.LogUtils;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class BaseFragment extends Fragment {
    protected AppCompatActivity mActivity;
    protected OnFragmentDestroyListener mOnFragmentDestroyListener;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d("currFragment", "current fragment is : " + getClass().getSimpleName());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity)getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mOnFragmentDestroyListener != null){
            mOnFragmentDestroyListener.onDestroy();
        }
    }


    /**
     * !必须在接口里所有方法的生命周期之前调用此方法,否则将可能不执行
     */
    protected void setOnFragmentDestroyListener(OnFragmentDestroyListener listener){
        this.mOnFragmentDestroyListener = listener;
    }


    /**
     * 定义接口,这个接口里有一个回调函数,当Fragment的生命周期执行到onDestroy时调用
     */
    public interface OnFragmentDestroyListener {

        /**
         * 当Fragment的生命周期执行到onDestroy时调用
         */
        void onDestroy();
    }

    /**
     * 获取ViewPager中正在显示的Page中Fragment实例
     * @param id ViewPager的Id
     * @param position ViewPager中Page的Position
     * @return ViewPager中正在显示的Page的Fragment
     */
    protected Fragment nowVPFragment(int id, int position) {
        String fragmentTag = "android:switcher:" + id + ":" + position;
        return getChildFragmentManager().findFragmentByTag(fragmentTag);
    }

    /**
     * 创建一个新的Intent
     * @param cls 目标class
     * @return 新的Intent
     */
    protected Intent newIntent(Class<?> cls){
        return new Intent(mActivity, cls);
    }
}
