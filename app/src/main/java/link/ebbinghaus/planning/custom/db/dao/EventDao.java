package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.Event;

public class EventDao extends BaseDao <Event>{

    public EventDao() {
        super(DBConfig.EventColumn.PK_EVENT_ID);
    }

    @Override
    protected void _insert(Event event) {
        ContentValues values = new ContentValues();
        event.convertToContentValues(values);
        db.insert(DBConfig.Table.EVENT, null, values);
    }

    @Override
    protected void _delete(String where, String[] args) {
        db.delete(DBConfig.Table.EVENT, where, args);
    }

    @Override
    protected void _update(Event event, String where, String[] args) {
        ContentValues values = new ContentValues();
        event.convertToContentValues(values);
        db.update(DBConfig.Table.EVENT, values, where, args);
    }


    @Override
    public void updateByPrimaryKey(Event event) {
        String whereClause = pkColumn + " = ?";
        _update(event, whereClause, new String[]{event.getPkEventId().toString()});
    }

    @Override
    public Event selectByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.EVENT + " WHERE " +
                pkColumn + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        Event event = new Event();
        event.filledByCursor(cursor);
        cursor.close();
        return event;
    }

    @Override
    public List<Event> selectAll() {
        String querySql = "SELECT * FROM " + DBConfig.Table.EVENT;
        Cursor cursor = db.rawQuery(querySql, null);
        List<Event> events = new ArrayList<>();
        while (cursor.moveToNext()){
            Event event = new Event();
            event.filledByCursor(cursor);
            events.add(event);
        }
        cursor.close();
        return events;
    }

    /* 以下方法为非通用方法 */

}
