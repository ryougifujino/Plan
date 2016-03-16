package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class EventSubtypeDao extends BaseDao<EventSubtype> {

    @Override
    protected void insert(EventSubtype eventSubtype) {
        ContentValues values = new ContentValues();
        eventSubtype.convertToContentValues(values);
        db.insert(DBConfig.Table.EVENT_SUBTYPE, null, values);
    }

    @Override
    protected void delete(String where, String[] args) {
        db.delete(DBConfig.Table.EVENT_SUBTYPE, where, args);
    }

    @Override
    protected void update(EventSubtype eventSubtype, String where, String[] args) {
        ContentValues values = new ContentValues();
        eventSubtype.convertToContentValues(values);
        db.update(DBConfig.Table.EVENT_SUBTYPE, values, where, args);
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        String whereClause = DBConfig.EventSubtypeColumn.PK_EVENT_SUBTYPE_ID + " = ?";
        remove(whereClause, new String[]{pk.toString()});
    }

    @Override
    public void modifyByPrimaryKey(EventSubtype eventSubtype) {
        String whereClause = DBConfig.EventSubtypeColumn.PK_EVENT_SUBTYPE_ID + " = ?";
        modify(eventSubtype, whereClause, new String[]{eventSubtype.getPkEventSubtypeId().toString()});
    }

    @Override
    public EventSubtype findByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.EVENT_SUBTYPE + " WHERE " +
                DBConfig.EventSubtypeColumn.PK_EVENT_SUBTYPE_ID + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        EventSubtype eventSubtype = new EventSubtype();
        eventSubtype.setPkEventSubtypeId(cursor.getInt(cursor.getColumnIndex(DBConfig.EventSubtypeColumn.PK_EVENT_SUBTYPE_ID)));
        eventSubtype.setEventSubtype(cursor.getString(cursor.getColumnIndex(DBConfig.EventSubtypeColumn.EVENT_SUBTYPE)));
        cursor.close();
        return eventSubtype;
    }
}
