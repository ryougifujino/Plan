package link.ebbinghaus.planning.core.service.impl;

import com.google.gson.Gson;
import com.yurikami.lib.util.LogUtils;

import java.io.IOException;

import link.ebbinghaus.planning.common.callback.DataCallback;
import link.ebbinghaus.planning.core.service.ExtensionService;
import link.ebbinghaus.planning.core.model.vo.extension.douban.book.Result;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by WINFIELD on 2016/4/4.
 */
public class ExtensionServiceImpl implements ExtensionService {

    private final OkHttpClient mClient = new OkHttpClient();
    private final Gson mGson = new Gson();

    @Override
    public void findBooks(String key,int start, final DataCallback<Result> callback) {
        Request request = new Request.Builder()
                .url("http://api.douban.com/v2/book/search?q=" + key + "&start=" + start)
                .build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response); // TODO: 2016/4/4 process

                LogUtils.d("ExtensionServiceImpl", Thread.currentThread().toString());
                Result result = mGson.fromJson(response.body().charStream(), Result.class);
                callback.onSuccess(result);
            }

        });
    }
}
