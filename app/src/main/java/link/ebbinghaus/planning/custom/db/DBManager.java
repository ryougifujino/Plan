package link.ebbinghaus.planning.custom.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

import link.ebbinghaus.planning.custom.other.App;

/**
 * 数据库管理器,使用此可以让数据库变得线程安全
 *
 * 所有的Dao都要使用此管理器来管理SQLiteDatabase,
 * 不能直接调用DBHelper的实例然后调用getWritableDatabase方法,这样数据库就变得线程不安全了
 *
 * !但要注意,使用了此管理器,new了多少Dao就要close多少,否则会造成计数异常,
 * !(导致的结果就是SQLiteDatabase不能正常close)
 */
public class DBManager {

    private AtomicInteger counter = new AtomicInteger();

    private static DBManager instance;
    private static DBHelper helper;
    private SQLiteDatabase db;

    private DBManager(){ }

    public static synchronized DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
            helper = DBHelper.getInstance(App.getContext());
        }
        return instance;
    }

    public synchronized SQLiteDatabase openDB(){
        if(counter.incrementAndGet() == 1){
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public synchronized void closeDB(){
        if(counter.decrementAndGet() == 0){
            db.close();
            instance = null;
        }
    }
}
