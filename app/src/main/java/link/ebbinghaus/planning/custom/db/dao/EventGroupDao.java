package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.EventGroup;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class EventGroupDao extends BaseDao<EventGroup> {

    @Override
    protected void insert(EventGroup eventGroup) {
        ContentValues values = new ContentValues();
        eventGroup.convertToContentValues(values);
        db.insert(DBConfig.Table.EVENT_GROUP, null, values);
    }

    @Override
    protected void delete(String where, String[] args) {
        db.delete(DBConfig.Table.EVENT_GROUP, where, args);
    }

    @Override
    protected void update(EventGroup eventGroup, String where, String[] args) {
        ContentValues values = new ContentValues();
        eventGroup.convertToContentValues(values);
        db.update(DBConfig.Table.EVENT_GROUP, values, where, args);
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        String whereClause = DBConfig.EventGroupColumn.PK_EVENT_GROUP_ID + " = ?";
        remove(whereClause, new String[]{pk.toString()});
    }

    @Override
    public void modifyByPrimaryKey(EventGroup eventGroup) {
        String whereClause = DBConfig.EventGroupColumn.PK_EVENT_GROUP_ID + " = ?";
        modify(eventGroup, whereClause, new String[]{eventGroup.getPkEventGroupId().toString()});
    }

    @Override
    public EventGroup findByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.EVENT_GROUP + " WHERE " +
                DBConfig.EventGroupColumn.PK_EVENT_GROUP_ID + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        EventGroup eventGroup = new EventGroup();
        eventGroup.setPkEventGroupId(cursor.getInt(cursor.getColumnIndex(DBConfig.EventGroupColumn.PK_EVENT_GROUP_ID)));
        eventGroup.setCreateTime(cursor.getLong(cursor.getColumnIndex(DBConfig.EventGroupColumn.CREATE_TIME)));
        eventGroup.setDescription(cursor.getString(cursor.getColumnIndex(DBConfig.EventGroupColumn.DESCRIPTION)));
        eventGroup.setLearningEventCount(cursor.getInt(cursor.getColumnIndex(DBConfig.EventGroupColumn.LEARNING_EVENT_COUNT)));
        eventGroup.setNormalEventCount(cursor.getInt(cursor.getColumnIndex(DBConfig.EventGroupColumn.NORMAL_EVENT_COUNT)));
        eventGroup.setAbstractEventCount(cursor.getInt(cursor.getColumnIndex(DBConfig.EventGroupColumn.ABSTRACT_EVENT_COUNT)));
        cursor.close();
        return eventGroup;
    }
}
