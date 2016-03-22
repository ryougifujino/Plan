package com.yurikami.lib.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.yurikami.lib.util.ActivityCollector;
import com.yurikami.lib.util.LogUtils;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class BaseActivity extends AppCompatActivity {
    protected AppCompatActivity mActivity;
    protected OnActivityDestroyListener mOnActivityDestroyListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("currActivity", "current activity is : " + getClass().getSimpleName());
        ActivityCollector.addActivity(this);
        mActivity = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //全局竖屏

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        if(mOnActivityDestroyListener != null){
            mOnActivityDestroyListener.onDestroy();
        }
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

    /**
     * 创建一个新的Intent
     * @param cls 目标class
     * @return 新的Intent
     */
    protected Intent newIntent(Class<?> cls){
        return new Intent(mActivity, cls);
    }

    /**
     * !必须在接口里所有方法的生命周期之前调用此方法,否则将可能不执行
     */
    protected void setOnActivityDestroyListener(OnActivityDestroyListener listener){
        this.mOnActivityDestroyListener = listener;
    }


    /**
     * 定义接口,这个接口里有一个回调函数,当Activity的生命周期执行到onDestroy时调用
     */
    public interface OnActivityDestroyListener {

        /**
         * 当Activity的生命周期执行到onDestroy时调用
         */
        void onDestroy();
    }

}
