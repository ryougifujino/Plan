package com.yurikami.lib.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yurikami.lib.utils.LogUtil;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class BaseFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d("currFragment","current fragment is : " + getClass().getSimpleName());
    }

}
