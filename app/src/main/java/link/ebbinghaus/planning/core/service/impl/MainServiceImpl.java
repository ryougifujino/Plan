package link.ebbinghaus.planning.core.service.impl;

import android.util.SparseArray;

import com.google.gson.reflect.TypeToken;
import com.yurikami.lib.net.NetCallback;
import com.yurikami.lib.net.UICallBack;
import com.yurikami.lib.net.parser.impl.ModelParser;
import com.yurikami.lib.util.LogUtils;
import com.yurikami.lib.util.SharedPreferencesUtils;

import java.util.Arrays;
import java.util.List;

import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.App;
import link.ebbinghaus.planning.app.constant.API;
import link.ebbinghaus.planning.app.constant.config.Config;
import link.ebbinghaus.planning.app.util.AlarmUtils;
import link.ebbinghaus.planning.core.db.decorator.impl.EventDaoDecorator;
import link.ebbinghaus.planning.core.model.server.po.User;
import link.ebbinghaus.planning.core.model.server.sys.Result;
import link.ebbinghaus.planning.core.service.MainService;
import link.ebbinghaus.planning.ui.view.achievement.fragment.AchievementFragment;
import link.ebbinghaus.planning.ui.view.extension.fragment.ExtensionFragment;
import link.ebbinghaus.planning.ui.view.history.fragment.HistoryFragment;
import link.ebbinghaus.planning.ui.view.planning.display.fragment.PlanningDisplayFragment;
import link.ebbinghaus.planning.ui.view.planning.done.fragment.PlanningDoneFragment;
import link.ebbinghaus.planning.ui.view.setting.fragment.SettingsFragment;
import link.ebbinghaus.planning.ui.view.store.fragment.StoreFragment;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by WINFIELD on 2016/2/19.
 */
public class MainServiceImpl implements MainService {
    private final OkHttpClient mClient = new OkHttpClient();

    @Override
    public void cacheMainDrawerFragmentMap(SparseArray<Class> fm) {
        fm.put(R.id.item_main_drawer_planning_display, PlanningDisplayFragment.class);
        fm.put(R.id.item_main_drawer_planning_done, PlanningDoneFragment.class);
        fm.put(R.id.item_main_drawer_history, HistoryFragment.class);
        fm.put(R.id.item_main_drawer_achievement, AchievementFragment.class);
        fm.put(R.id.item_main_drawer_extension, ExtensionFragment.class);
        fm.put(R.id.item_main_drawer_store, StoreFragment.class);
        fm.put(R.id.item_main_drawer_settings, SettingsFragment.class);
    }

    @Override
    public Call login(String loginName, String password, NetCallback callback) {
        RequestBody formBody = new FormBody.Builder()
                .add("loginName", loginName)
                .add("password", password)
                .build();
        final Request request = new Request.Builder()
                .url( API.BASE_URL + "user/login.do")
                .post(formBody)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new UICallBack<>(new ModelParser<Result<User>>(new TypeToken<Result<User>>(){}.getType()),callback));
        return call;
    }

    @Override
    public void updateUser(User user) {
        SharedPreferencesUtils.saveSync(App.getContext(), Config.SP_USER,user);
    }

    @Override
    public Call register(String registerName, String password, NetCallback callback) {
        RequestBody formBody = new FormBody.Builder()
                .add("registerName", registerName)
                .add("password", password)
                .build();
        final Request request = new Request.Builder()
                .url( API.BASE_URL + "user/register.do")
                .post(formBody)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new UICallBack<>(new ModelParser<>(Result.class),callback));
        return call;
    }

    @Override
    public void updateEventsProcessAndRelated() {
        new Thread() {
            @Override
            public void run() {
                LogUtils.d("sub process","子线程启动");
                EventDaoDecorator dao = new EventDaoDecorator();
                List<Long> learningEventIds = dao.selectAllFailedLearningEventIds();
                LogUtils.d("fuck", Arrays.toString(learningEventIds.toArray(new Long[learningEventIds.size()])));
                dao.updateEventsProcessAndRelated();
                dao.closeDB();
                if (learningEventIds.size() > 0) {
                    LogUtils.d("fuck","fuckyou");
                    AlarmUtils.cancelNotificationAlarm(learningEventIds);
                }
            }
        }.start();
    }
}
