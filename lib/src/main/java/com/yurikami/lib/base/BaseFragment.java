package com.yurikami.lib.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.yurikami.lib.utils.LogUtil;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class BaseFragment extends Fragment {
    protected AppCompatActivity mActivity;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d("currFragment", "current fragment is : " + getClass().getSimpleName());

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity)getActivity();
    }
}
