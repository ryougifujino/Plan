package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.yurikami.lib.db.SelectHelper;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.custom.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.model.entity.po.Event;

public class EventDao extends BaseDao <Event>{

    public EventDao() {
        super(DBConfig.EventColumn.PK_EVENT_ID);
    }

    @Override
    protected long _insert(Event event) {
        ContentValues values = new ContentValues();
        event.convertToContentValues(values);
        return db.insert(DBConfig.Table.EVENT, null, values);
    }

    @Override
    protected int _delete(String where, String[] args) {
        return db.delete(DBConfig.Table.EVENT, where, args);
    }

    @Override
    protected int _update(Event event, String where, String[] args) {
        ContentValues values = new ContentValues();
        event.convertToContentValues(values);
        return db.update(DBConfig.Table.EVENT, values, where, args);
    }

    @Override
    public long insert(Event event) {
        event.setPkEventId(_insert(event));
        return event.getPkEventId();
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
    public void insertSome(List<Event> events) {
        for (Event event : events) {
            event.setPkEventId(_insert(event));
        }
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

    /**
     * 查找某年某月的具体计划
     * @param year
     * @param month
     * @return 某年某月的具体计划
     */
    public List<Event> selectSpecEvents(int year, int month){
        String eventType = DBConfig.EventColumn.EVENT_TYPE;
        String querySql = "SELECT * FROM " + DBConfig.Table.EVENT
                + " WHERE (" + eventType + " = "+ EventConfig.TYPE_SPEC_LEARNING + " OR " + eventType + " = "+ EventConfig.TYPE_SPEC_NORMAL + ")"
                + " AND " + SelectHelper.in(DBConfig.EventColumn.EVENT_EXPECTED_FINISHED_DATE, year, month);
        List<Event> specEvents = new ArrayList<>();
        Cursor cursor = db.rawQuery(querySql, null);
        while (cursor.moveToNext()){
            Event specEvent = new Event();
            specEvent.filledByCursor(cursor);
            specEvents.add(specEvent);
        }
        cursor.close();
        return specEvents;
    }

}
