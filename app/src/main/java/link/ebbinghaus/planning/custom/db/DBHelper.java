package link.ebbinghaus.planning.custom.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/28.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        this(context, DBConfig.DB_NAME,null, DBConfig.DB_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConfig.CREATE_TABLE_EVENT);
        db.execSQL(DBConfig.CREATE_TABLE_LEARNING_EVENT_GROUP);
        db.execSQL(DBConfig.CREATE_TABLE_EVENT_GROUP);
        db.execSQL(DBConfig.CREATE_TABLE_EVENT_SUBTYPE);
        db.execSQL(DBConfig.CREATE_TABLE_FAST_TEMPLATE);
        db.execSQL(DBConfig.CREATE_TABLE_DEFAULT_INPUT_VALUE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                //不要用break
            case 2:
        }
    }
}
