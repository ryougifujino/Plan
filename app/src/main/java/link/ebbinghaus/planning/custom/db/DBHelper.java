package link.ebbinghaus.planning.custom.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yurikami.lib.db.SqlBuilder;
import com.yurikami.lib.util.LogUtils;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.custom.db.dao.DefaultInputValueDao;
import link.ebbinghaus.planning.custom.db.dao.GreekAlphabetDao;

/**
 * !禁止直接获取本类实例后调用getWritableDatabase
 */
public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper sHelper;

    public static synchronized DBHelper getInstance(Context context){
        if (sHelper == null){
            sHelper = new DBHelper(context);
        }
        return sHelper;
    }

    private DBHelper(Context context){
        this(context, DBConfig.DB_NAME, null, DBConfig.DB_VERSION);
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConfig.CREATE_TABLE_EVENT);
        db.execSQL(DBConfig.CREATE_TABLE_GREEK_ALPHABET);
        db.execSQL(DBConfig.CREATE_TABLE_LEARNING_EVENT_GROUP);
        db.execSQL(DBConfig.CREATE_TABLE_EVENT_GROUP);
        db.execSQL(DBConfig.CREATE_TABLE_EVENT_SUBTYPE);
        db.execSQL(DBConfig.CREATE_TABLE_FAST_TEMPLATE);
        db.execSQL(DBConfig.CREATE_TABLE_DEFAULT_INPUT_VALUE);
        SqlBuilder.release();

        //向表中插入一些默认数据
        DefaultInputValueDao.presetDefaultInputValue(db);
        GreekAlphabetDao.presetGreekAlphabetValues(db);

        LogUtils.d(getClass().getSimpleName(),"Database Created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                //不要用break
            case 2:
        }
    }

    /**
     * 创建数据库(如果存在则不创建)
     * 这是一个异步方法,并且在App启动的时候就调用,这样第一次启动时就或创建或升级数据库;
     * 在其他地方调用getWritableDatabase就不用在子线程里调用了(因为不会再有升级的可能性)
     * TODO:这个方法应该改成异步的(getInstance已经是同步方法了),因为文档说Database upgrade may take a long time...
     *
     */
    public static void createDatabase(){
        DBManager.getInstance().openDB();
        DBManager.getInstance().closeDB();
    }

}
