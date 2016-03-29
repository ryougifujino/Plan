package com.yurikami.lib.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.yurikami.lib.util.ActivityCollector;
import com.yurikami.lib.util.LogUtils;

/**
 * Created by WINFIELD on 2016/2/17.
 */
public class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    protected AppCompatActivity mActivity;
    protected OnActivityStopListener mOnActivityStopListener;

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
    protected void setOnActivityStopListener(OnActivityStopListener listener){
        this.mOnActivityStopListener = listener;
    }


    /**
     * 定义接口,这个接口里有一个回调函数,当Activity的生命周期执行到onStop时调用
     */
    public interface OnActivityStopListener {

        /**
         * 当Activity的生命周期执行到onStop时调用
         */
        void onStop();
    }

    //life circle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(TAG, "---------------Current activity is : " + TAG + "---------------");
        LogUtils.i(TAG, "onCreate");
        ActivityCollector.addActivity(this);
        mActivity = this;
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //全局强制竖屏
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        LogUtils.i(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogUtils.i(TAG, "onStop");
        super.onStop();
        if(mOnActivityStopListener != null){
            mOnActivityStopListener.onStop();
        }
    }

    @Override
    protected void onRestart() {
        LogUtils.i(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        LogUtils.i(TAG, "onDestroy");
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        LogUtils.i(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        LogUtils.i(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
