package link.ebbinhaus.planning.custom.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by WINFIELD on 2016/2/28.
 */
public class DBManager {
    private SQLiteDatabase db;

    public DBManager(Context context) {
        db = new DBHelper(context).getWritableDatabase();
    }


}
