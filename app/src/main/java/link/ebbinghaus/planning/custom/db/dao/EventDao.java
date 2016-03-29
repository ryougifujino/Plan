package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.yurikami.lib.db.SelectHelper;
import com.yurikami.lib.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.custom.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.model.entity.po.Event;

public class EventDao extends BaseDao <Event>{

    public EventDao() {
        super(DBConfig.EventColumn.PK_EVENT_ID, DBConfig.Table.EVENT);
    }

    @Override
    protected List<Event> _select(String querySql, String[] selectionArgs) {
        Cursor cursor = db.rawQuery(querySql, selectionArgs);
        List<Event> events = new ArrayList<>();
        while (cursor.moveToNext()){
            Event event = new Event();
            event.filledByCursor(cursor);
            events.add(event);
        }
        cursor.close();
        return events;
    }

    @Override
    protected long _insert(Event event) {
        ContentValues values = new ContentValues();
        event.convertToContentValues(values);
        return db.insert(mTableName, null, values);
    }

    @Override
    protected int _update(Event event, String where, String[] args) {
        ContentValues values = new ContentValues();
        event.convertToContentValues(values);
        return db.update(mTableName, values, where, args);
    }

    @Override
    public long insert(Event event) {
        event.setPkEventId(_insert(event));
        return event.getPkEventId();
    }


    @Override
    public void updateByPrimaryKey(Event event) {
        String whereClause = mPkColumn + " = ?";
        _update(event, whereClause, new String[]{event.getPkEventId().toString()});
    }

    @Override
    public void insertSome(List<Event> events) {
        for (Event event : events) {
            event.setPkEventId(_insert(event));
        }
    }

    /* 以下方法为非通用方法 */

    /**
     * 查找某年某个月的具体计划
     * @param year
     * @param month
     * @return 某年某月的具体计划
     */
    public List<Event> selectSpecMonthEvents(int year, int month){
        String eventType = DBConfig.EventColumn.EVENT_TYPE;
        String querySql = "SELECT * FROM " + mTableName
                + " WHERE (" + eventType + " = "+ EventConfig.TYPE_SPEC_LEARNING + " OR " + eventType + " = "+ EventConfig.TYPE_SPEC_NORMAL + ")"
                + " AND " + SelectHelper.in(DBConfig.EventColumn.EVENT_EXPECTED_FINISHED_DATE, year, month);
        return _select(querySql);
    }

    /**
     * 查找某一天所在周的具体计划
     * @param year
     * @param month
     * @param day
     * @return 某一天所在周的具体计划
     */
    public List<Event> selectSpecWeekEvents(int year, int month,int day){
        long[] startEnd = DateUtils.startEndTimestampOfWeek(year,month,day);
        String eventType = DBConfig.EventColumn.EVENT_TYPE;
        String querySql = "SELECT * FROM " + mTableName
                + " WHERE (" + eventType + " = "+ EventConfig.TYPE_SPEC_LEARNING + " OR " + eventType + " = "+ EventConfig.TYPE_SPEC_NORMAL + ")"
                + " AND " + SelectHelper.in(DBConfig.EventColumn.EVENT_EXPECTED_FINISHED_DATE, startEnd[0], startEnd[1])
                + " ORDER BY " + DBConfig.EventColumn.EVENT_EXPECTED_FINISHED_DATE + " ASC";
        return _select(querySql);
    }

    /**
     * 查找所有的模糊计划(按时间排序)
     * @return 所有的模糊计划
     */
    public List<Event> selectAbstAllEvents(){
        String querySql = "SELECT * FROM "+ mTableName
                +" WHERE "+ DBConfig.EventColumn.EVENT_TYPE +" = "+ EventConfig.TYPE_ABSTRACT
                +" ORDER BY "+ DBConfig.EventColumn.CREATE_TIME +" DESC";
        return _select(querySql);
    }

    /**
     * 根据计划组类型和计划组id查找相关的具体计划或模糊计划
     * @param eventGroupType 计划组类型 true:具体计划 false:模糊计划
     * @param eventGroupId 计划组id
     * @return 计划组相关的具体计划或模糊计划
     */
    public List<Event> selectEventGroupDetail(boolean eventGroupType, Long eventGroupId){
        String querySql;
        if (eventGroupType){
            querySql = "SELECT e.* FROM " +
                    "event e JOIN event_group eg ON e.EVENT_GROUP_ID = eg.PK_EVENT_GROUP_ID " +
                    "WHERE (e.EVENT_TYPE = 1 OR e.EVENT_TYPE = 2) AND eg.PK_EVENT_GROUP_ID = ? " +
                    "ORDER BY e.PK_EVENT_ID DESC";
        }else {
            querySql = "SELECT e.* FROM " +
                    "event e JOIN event_group eg ON e.EVENT_GROUP_ID = eg.PK_EVENT_GROUP_ID " +
                    "WHERE (e.EVENT_TYPE = 3) AND eg.PK_EVENT_GROUP_ID = ? " +
                    "ORDER BY e.PK_EVENT_ID DESC";
        }
        return _select(querySql,new String[]{ eventGroupId + "" });
    }
}
