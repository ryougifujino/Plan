package link.ebbinghaus.planning.core.service.impl;

import com.yurikami.lib.net.NetCallback;
import com.yurikami.lib.net.UICallBack;
import com.yurikami.lib.net.parser.impl.BigModelParser;

import link.ebbinghaus.planning.app.constant.API;
import link.ebbinghaus.planning.core.model.local.vo.extension.douban.book.Result;
import link.ebbinghaus.planning.core.service.ExtensionService;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by WINFIELD on 2016/4/4.
 */
public class ExtensionServiceImpl implements ExtensionService {

    private final OkHttpClient mClient = new OkHttpClient();

    @Override
    public Call findBooks(final String key, final int start, final NetCallback callback) {
//        Observable.create(new Observable.OnSubscribe<Result>() {
//            @Override
//            public void call(Subscriber<? super Result> subscriber) {
//                try {
//                    Request request = new Request.Builder()
//                            .url(API.DOUBAN_READ + "search?q=" + key + "&start=" + start)
//                            .build();
//
//                    Response response = mClient.newCall(request).execute();
//                    if (!response.isSuccessful())
//                        throw new IOException("Unexpected code " + response);
//
//                    Result result = mGson.fromJson(response.body().charStream(), Result.class);
//                    subscriber.onNext(result);
//                    subscriber.onCompleted();
//                } catch (IOException e) {
//                    subscriber.onError(e);
//                }
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Result>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callback.onFailure(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Result result) {
//                        callback.onSuccess(result);
//                    }
//                });

        Request request = new Request.Builder()
                .url(API.DOUBAN_READ + "search?q=" + key + "&start=" + start)
                .build();

        final Call call = mClient.newCall(request);
        call.enqueue(new UICallBack<>(new BigModelParser<>(Result.class),callback));
        return call;
    }
}
