package link.ebbinghaus.planning.core.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.common.constant.config.DBConfig;
import link.ebbinghaus.planning.core.model.po.EventSubtype;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class EventSubtypeDao extends BaseDao<EventSubtype> implements DBConfig.EventSubtypeColumn{

    public EventSubtypeDao() {
        super(PK_EVENT_SUBTYPE_ID, DBConfig.Table.EVENT_SUBTYPE);
    }

    @Override
    protected List<EventSubtype> _select(String querySql, String[] selectionArgs) {
        Cursor cursor = db.rawQuery(querySql, selectionArgs);
        List<EventSubtype> eventGroups = new ArrayList<>();
        while (cursor.moveToNext()){
            EventSubtype eventGroup = new EventSubtype();
            eventGroup.filledByCursor(cursor);
            eventGroups.add(eventGroup);
        }
        cursor.close();
        return eventGroups;
    }

    @Override
    protected long _insert(EventSubtype eventSubtype) {
        ContentValues values = new ContentValues();
        eventSubtype.convertToContentValues(values);
        return db.insert(mTableName, null, values);
    }

    @Override
    protected int _update(EventSubtype eventSubtype, String where, String[] args) {
        ContentValues values = new ContentValues();
        eventSubtype.convertToContentValues(values);
        return db.update(mTableName, values, where, args);
    }

    @Override
    public long insert(EventSubtype eventSubtype) {
        eventSubtype.setPkEventSubtypeId(_insert(eventSubtype));
        return eventSubtype.getPkEventSubtypeId();
    }

    @Override
    public void updateByPrimaryKey(EventSubtype eventSubtype) {
        String whereClause = mPkColumn + " = ?";
        _update(eventSubtype, whereClause, new String[]{eventSubtype.getPkEventSubtypeId().toString()});
    }

    @Override
    public void insertSome(List<EventSubtype> eventSubtypes) {
        for (EventSubtype eventSubtype : eventSubtypes) {
            eventSubtype.setPkEventSubtypeId(_insert(eventSubtype));
        }
    }

    /* 以下方法为非通用方法 */


}
