package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.yurikami.lib.util.Utils;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.Event;

public class EventDao extends BaseDao <Event>{

    @Override
    protected void insert(Event event) {
        ContentValues values = new ContentValues();
        event.convertToContentValues(values);
        db.insert(DBConfig.Table.EVENT, null, values);
    }

    @Override
    protected void delete(String where, String[] args) {
        db.delete(DBConfig.Table.EVENT, where, args);
    }

    @Override
    protected void update(Event event, String where, String[] args) {
        ContentValues values = new ContentValues();
        event.convertToContentValues(values);
        db.update(DBConfig.Table.EVENT, values, where, args);
    }


    @Override
    public void removeByPrimaryKey(Integer pk) {
        String whereClause = DBConfig.EventColumn.PK_EVENT_ID + " = ?";
        remove(whereClause, new String[]{pk.toString()});
    }

    @Override
    public void modifyByPrimaryKey(Event event) {
        String whereClause = DBConfig.EventColumn.PK_EVENT_ID + " = ?";
        modify(event, whereClause, new String[]{event.getPkEventId().toString()});
    }

    @Override
    public Event findByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.EVENT + " WHERE " +
                DBConfig.EventColumn.PK_EVENT_ID + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        Event event = new Event();
        event.setPkEventId(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.PK_EVENT_ID)));
        event.setLearningEventGroupId(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.LEARNING_EVENT_GROUP_ID)));
        event.setEventGroupId(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_GROUP_ID)));
        event.setDescription(cursor.getString(cursor.getColumnIndex(DBConfig.EventColumn.DESCRIPTION)));
        event.setSummary(cursor.getString(cursor.getColumnIndex(DBConfig.EventColumn.SUMMARY)));
        event.setEventType(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_TYPE)));
        event.setEventSubtypeId(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_SUBTYPE_ID)));
        event.setEventSequence(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_SEQUENCE)));
        event.setIsShowEventSequence(Utils.int2Bool(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.IS_SHOW_EVENT_SEQUENCE))));
        event.setCreateTime(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.CREATE_TIME)));
        event.setEventExpectedFinishedDate(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_EXPECTED_FINISHED_DATE)));
        event.setEventFinishedTime(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_FINISHED_TIME)));
        event.setIsEventFinished(Utils.int2Bool(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.IS_EVENT_FINISHED))));
        event.setIsGreekAlphabetMarked(Utils.int2Bool(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.IS_GREEK_ALPHABET_MARKED))));
        event.setIsRemind(Utils.int2Bool(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.IS_REMIND))));
        event.setRemindTime(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.REMIND_TIME)));
        event.setEventProcess(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_PROCESS)));
        cursor.close();
        return event;
    }
}
