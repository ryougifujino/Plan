package com.yurikami.lib.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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
     * 跳转到某个Activity
     * @param cls 目标class
     */
    protected void startActivity(Class<?> cls) {
        startActivity(newIntent(cls));
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

    protected void translucentStatusBar() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    /*| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION*/);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    /*| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION*/
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            /*window.setNavigationBarColor(Color.TRANSPARENT);*/
        }
    }

    /** 有一个按钮的消息框 */
    protected void showMessageDialog(String title, String message, String buttonTitle){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(buttonTitle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dlg = builder.create();
        dlg.show();
    }

    /** 有一个按钮的消息框 */
    protected void showMessageDialog(int titleResId, int messageResId, int buttonTitleResId){
        showMessageDialog(getString(titleResId),
                getString(messageResId),getString(buttonTitleResId));
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
