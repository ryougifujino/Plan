package com.yurikami.lib.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.yurikami.lib.util.ActivityCollector;
import com.yurikami.lib.util.LogUtils;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("currActivity", "current activity is : " + getClass().getSimpleName());
        ActivityCollector.addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**
     * 获取ViewPager中正在显示的Page中Fragment实例
     * @param id ViewPager的Id
     * @param position ViewPager中Page的Position
     * @return ViewPager中正在显示的Page的Fragment
     */
    protected Fragment nowVPFragment(int id, int position) {
        String fragmentTag = "android:switcher:" + id + ":" + position;
        return getSupportFragmentManager().findFragmentByTag(fragmentTag);
    }
}
