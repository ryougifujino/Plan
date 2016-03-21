package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.EventGroup;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class EventGroupDao extends BaseDao<EventGroup> {

    public EventGroupDao() {
        super(DBConfig.EventGroupColumn.PK_EVENT_GROUP_ID);
    }

    @Override
    protected void _insert(EventGroup eventGroup) {
        ContentValues values = new ContentValues();
        eventGroup.convertToContentValues(values);
        db.insert(DBConfig.Table.EVENT_GROUP, null, values);
    }

    @Override
    protected void _delete(String where, String[] args) {
        db.delete(DBConfig.Table.EVENT_GROUP, where, args);
    }

    @Override
    protected void _update(EventGroup eventGroup, String where, String[] args) {
        ContentValues values = new ContentValues();
        eventGroup.convertToContentValues(values);
        db.update(DBConfig.Table.EVENT_GROUP, values, where, args);
    }

    @Override
    public void updateByPrimaryKey(EventGroup eventGroup) {
        String whereClause = pkColumn + " = ?";
        _update(eventGroup, whereClause, new String[]{eventGroup.getPkEventGroupId().toString()});
    }

    @Override
    public EventGroup selectByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.EVENT_GROUP + " WHERE " +
                pkColumn + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        EventGroup eventGroup = new EventGroup();
        eventGroup.filledByCursor(cursor);
        cursor.close();
        return eventGroup;
    }

    @Override
    public List<EventGroup> selectAll() {
        String querySql = "SELECT * FROM " + DBConfig.Table.EVENT_GROUP;
        Cursor cursor = db.rawQuery(querySql, null);
        List<EventGroup> eventGroups = new ArrayList<>();
        while (cursor.moveToNext()){
            EventGroup eventGroup = new EventGroup();
            eventGroup.filledByCursor(cursor);
            eventGroups.add(eventGroup);
        }
        return eventGroups;
    }

    /* 以下方法为非通用方法 */


}
