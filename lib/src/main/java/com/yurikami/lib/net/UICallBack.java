package com.yurikami.lib.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.JsonSyntaxException;
import com.yurikami.lib.net.parser.Parser;
import com.yurikami.lib.util.LogUtils;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * OkHttp的UI回调
 */
public class UICallBack<T> implements Callback {
    public static final int FAILURE = 0;
    public static final int SUCCESS = 1;

    private Handler mUIHandler = new UIHandler(this);
    private Parser<T> mParser;
    private NetCallback mNetCallback;
    private Call mCall;

    public UICallBack(Parser<T> parser,NetCallback netCallback) {
        this.mParser = parser;
        this.mNetCallback = netCallback;
    }

    public UICallBack(Parser<T> parser) {
        this.mParser = parser;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        mCall = call;
        Message msg = Message.obtain();
        msg.what = FAILURE;
        msg.obj = e.getMessage();
        mUIHandler.sendMessage(msg);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        mCall = call;
        Message msg = Message.obtain();
        if (response.isSuccessful()){
            msg.what = SUCCESS;
            try {
                msg.obj = mParser.parse(response);  // FIXME: 2016/4/20  may JsonSyntaxException
            }catch (JsonSyntaxException e){
                LogUtils.e("Json Syntax:",response.body().string());
                e.printStackTrace();
            }
        }else {
            msg.what = FAILURE;
            msg.obj = response.message();
        }
        mUIHandler.sendMessage(msg);
        //用了body之后要关闭，不然会报Weak Connection
        response.body().close();
    }

    /**
     * 错误回调
     * @param errorMsg 错误信息
     */
    public void onFailure(String errorMsg){
        if (mNetCallback != null){
            mNetCallback.onFailure(errorMsg,mCall);
        }
    }

    /**
     * 正确回调
     * @param result 结果
     */
    public void onSuccess(Object result){
        if (mNetCallback != null){
            mNetCallback.onSuccess(result,mCall);
        }
    }

    static class UIHandler extends Handler {
        private WeakReference<UICallBack> mWeakUICallBack;

        public UIHandler(UICallBack uiCallBack) {
            super(Looper.getMainLooper());
            mWeakUICallBack = new WeakReference<>(uiCallBack);
        }

        @Override
        public void handleMessage(Message msg) {
            UICallBack callBack = mWeakUICallBack.get();
            if (callBack == null){
                return;
            }
            switch (msg.what){
                case SUCCESS:
                    callBack.onSuccess(msg.obj);
                    break;
                case FAILURE:
                    callBack.onFailure((String) msg.obj);
                    break;
            }
        }
    }


}
