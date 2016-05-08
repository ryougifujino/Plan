package link.ebbinghaus.planning.app.util;

import android.content.Context;
import android.content.SharedPreferences;

import link.ebbinghaus.planning.app.App;

/**
 * 生成唯一Integer类型id的工具类
 */
public class UniqueUtils {

    private final static String BASE = UniqueUtils.class.getName();

    /**
     * 获取一个循环的id，达到Integer的最大值后将重新从0开始
     * @param saveKey 生成不同的id需要不同的key
     * @return 循环id
     */
    public static int getLoopId(String saveKey){
        SharedPreferences sp = App.getContext().getSharedPreferences(BASE + saveKey,Context.MODE_PRIVATE);
        int id = sp.getInt(saveKey,0);
        int nextId = ((id == Integer.MAX_VALUE) ? 0 : (id + 1));
        sp.edit().putInt(saveKey, nextId).commit();
        return id;
    }
}
