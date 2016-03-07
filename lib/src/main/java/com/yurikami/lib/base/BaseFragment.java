package com.yurikami.lib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.yurikami.lib.util.LogUtils;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class BaseFragment extends Fragment {
    protected AppCompatActivity mActivity;
    protected FragmentLifeCycleListener mFragmentLifeCycleListener;


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
        if(mFragmentLifeCycleListener != null){
            mFragmentLifeCycleListener.onDestroy();
        }
    }


    /**
     * !必须在接口里所有方法的生命周期之前调用此方法,否则将可能不执行一些方法
     */
    protected void setFragmentLifeCycleListener(FragmentLifeCycleListener listener){
        this.mFragmentLifeCycleListener = listener;
    }


    public interface FragmentLifeCycleListener{

        void onDestroy();
    }
}
