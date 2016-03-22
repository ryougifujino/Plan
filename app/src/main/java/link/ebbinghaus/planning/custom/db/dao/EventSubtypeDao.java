package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class EventSubtypeDao extends BaseDao<EventSubtype> {

    public EventSubtypeDao() {
        super(DBConfig.EventSubtypeColumn.PK_EVENT_SUBTYPE_ID);
    }

    @Override
    protected long _insert(EventSubtype eventSubtype) {
        ContentValues values = new ContentValues();
        eventSubtype.convertToContentValues(values);
        return db.insert(DBConfig.Table.EVENT_SUBTYPE, null, values);
    }

    @Override
    protected int _delete(String where, String[] args) {
        return db.delete(DBConfig.Table.EVENT_SUBTYPE, where, args);
    }

    @Override
    protected int _update(EventSubtype eventSubtype, String where, String[] args) {
        ContentValues values = new ContentValues();
        eventSubtype.convertToContentValues(values);
        return db.update(DBConfig.Table.EVENT_SUBTYPE, values, where, args);
    }

    @Override
    public long insert(EventSubtype eventSubtype) {
        eventSubtype.setPkEventSubtypeId(_insert(eventSubtype));
        return eventSubtype.getPkEventSubtypeId();
    }

    @Override
    public void updateByPrimaryKey(EventSubtype eventSubtype) {
        String whereClause = pkColumn + " = ?";
        _update(eventSubtype, whereClause, new String[]{eventSubtype.getPkEventSubtypeId().toString()});
    }

    @Override
    public EventSubtype selectByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.EVENT_SUBTYPE + " WHERE " +
                pkColumn + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        EventSubtype eventSubtype = new EventSubtype();
        eventSubtype.filledByCursor(cursor);
        cursor.close();
        return eventSubtype;
    }

    @Override
    public void insertSome(List<EventSubtype> eventSubtypes) {
        for (EventSubtype eventSubtype : eventSubtypes) {
            eventSubtype.setPkEventSubtypeId(_insert(eventSubtype));
        }
    }


    @Override
    public List<EventSubtype> selectAll() {
        String querySql = "SELECT * FROM " + DBConfig.Table.EVENT_SUBTYPE;
        Cursor cursor = db.rawQuery(querySql, null);
        List<EventSubtype> eventGroups = new ArrayList<>();
        while (cursor.moveToNext()){
            EventSubtype eventGroup = new EventSubtype();
            eventGroup.filledByCursor(cursor);
            eventGroups.add(eventGroup);
        }
        cursor.close();
        return eventGroups;
    }

    /* 以下方法为非通用方法 */


}
