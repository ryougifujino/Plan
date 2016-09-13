package com.yurikami.lib.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yurikami.lib.util.LogUtils;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class BaseFragment extends Fragment {
    final protected String TAG = getClass().getSimpleName();
    protected AppCompatActivity mActivity;
    protected OnFragmentStopListener mOnFragmentStopListener;

    /**
     * !必须在接口里所有方法的生命周期之前调用此方法,否则将可能不执行
     */
    protected void setOnFragmentStopListener(OnFragmentStopListener listener){
        this.mOnFragmentStopListener = listener;
    }   

    /**
     * 定义接口,这个接口里有一个回调函数,当Fragment的生命周期执行到onStop时调用
     */
    public interface OnFragmentStopListener {

        /**
         * 当Fragment的生命周期执行到onStop时调用
         */
        void onStop();
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

    /**
     * 跳转到某个Activity
     * @param cls 目标Activity的class
     */
    protected void startActivity(Class<?> cls){
        startActivity(newIntent(cls));
    }

    //life circle

    @Override
    public void onAttach(Context context) {
        LogUtils.i(TAG,"---------------Current fragment is : " + TAG + "---------------");
        LogUtils.i(TAG,"onAttach");
        super.onAttach(context);
        mActivity = (AppCompatActivity)getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LogUtils.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.i(TAG,"onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        LogUtils.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        LogUtils.i(TAG,"onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        LogUtils.i(TAG,"onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtils.i(TAG,"onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtils.i(TAG,"onStop");
        super.onStop();
        if(mOnFragmentStopListener != null){
            mOnFragmentStopListener.onStop();
        }
    }


    @Override
    public void onDestroyView() {
        LogUtils.i(TAG,"onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtils.i(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        LogUtils.i(TAG,"onDetach");
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        LogUtils.i(TAG,"onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        LogUtils.i(TAG,"onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

}
