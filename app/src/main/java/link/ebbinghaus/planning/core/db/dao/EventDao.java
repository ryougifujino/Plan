package link.ebbinghaus.planning.core.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.yurikami.lib.db.WhereHelper;
import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.app.constant.config.DBConfig;
import link.ebbinghaus.planning.app.constant.config.entity.EventConfig;
import link.ebbinghaus.planning.core.model.local.po.Event;

public class EventDao extends BaseDao <Event> implements DBConfig.EventColumn{

    public EventDao() {
        super(PK_EVENT_ID, DBConfig.Table.EVENT);
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
        String querySql = "SELECT * FROM " + mTableName
                + " WHERE (" + EVENT_TYPE + " = "+ EventConfig.TYPE_SPEC_LEARNING + " OR " + EVENT_TYPE + " = "+ EventConfig.TYPE_SPEC_NORMAL + ")"
                + " AND " + WhereHelper.in(EVENT_EXPECTED_FINISHED_DATE, year, month);
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
        String eventType = EVENT_TYPE;
        String querySql = "SELECT * FROM " + mTableName
                + " WHERE (" + eventType + " = "+ EventConfig.TYPE_SPEC_LEARNING + " OR " + eventType + " = "+ EventConfig.TYPE_SPEC_NORMAL + ")"
                + " AND " + WhereHelper.between(EVENT_EXPECTED_FINISHED_DATE, startEnd[0], startEnd[1])
                + " ORDER BY " + EVENT_EXPECTED_FINISHED_DATE + " ASC";
        return _select(querySql);
    }

    /**
     * 查找所有的模糊计划(按时间排序)
     * @return 所有的模糊计划
     */
    public List<Event> selectAbstAllEvents(){
        String querySql = "SELECT * FROM "+ mTableName
                +" WHERE "+ EVENT_TYPE +" = "+ EventConfig.TYPE_ABSTRACT
                +" ORDER BY "+ CREATE_TIME +" DESC";
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

    /**
     * 从某年某月某日开始查找指定天数的具体计划
     *
     * @param startDateTimestamp 开始日期时间戳
     * @param days 从指定日开始查找的天数
     * @param isFailed 是否查找失败（过期）的具体计划
     * @return 从某日开始N天的具体计划
     */
    public List<Event> selectSpecEventsFrom(long startDateTimestamp, int days, boolean isFailed){
        long end = DateUtils.timestampAfter(startDateTimestamp,days);
        String failedClause = isFailed ? (" AND " + EVENT_PROCESS + " != " + EventConfig.PROCESS_EXPIRED) : "";
        String querySql = "SELECT * FROM " + mTableName + " WHERE " +
                WhereHelper.between(EVENT_EXPECTED_FINISHED_DATE,startDateTimestamp,end) + failedClause +
                " AND (" + EVENT_TYPE + " = " + EventConfig.TYPE_SPEC_NORMAL + " OR " + EVENT_TYPE + " = " + EventConfig.TYPE_SPEC_LEARNING + ")";
        return _select(querySql);
    }

    /**
     * 查找从今天算起的过去两天的具体计划（不包括失败（过期）的计划）
     * @return 从今天(包括今天)算起的过去两天的具体计划
     */
    public List<Event> selectLast2DaysSpecEvents(){
        long todayTimestamp = DateUtils.dateTimestampOfToday();
        long start = DateUtils.timestampBefore(todayTimestamp, 1);
        long end = DateUtils.timestampAfter(todayTimestamp,1);
        String querySql = "SELECT * FROM "+ mTableName +" WHERE " +
                WhereHelper.between(EVENT_EXPECTED_FINISHED_DATE,start,end) +
                " AND " + EVENT_PROCESS + " != " + EventConfig.PROCESS_EXPIRED +
                " AND (" + EVENT_TYPE + " = " + EventConfig.TYPE_SPEC_NORMAL + " OR " + EVENT_TYPE + " = " + EventConfig.TYPE_SPEC_LEARNING + ")";
        return _select(querySql);
    }

    /**
     * 查找所有已经完成的具体计划
     * @return 所有已经完成的具体计划
     */
    public List<Event> selectAllDoneSpecEvents(){
        String querySql = "SELECT * FROM "+ mTableName +" WHERE ("+
                EVENT_TYPE +" = ? OR "+ EVENT_TYPE +" = ?) AND "+ IS_EVENT_FINISHED +" = ? AND " + EVENT_PROCESS + " != ?";
        return _select(querySql,EventConfig.TYPE_SPEC_LEARNING,EventConfig.TYPE_SPEC_NORMAL,1,EventConfig.PROCESS_EXPIRED);
    }

    /**
     * 查找出所有即将被置为失败的学习计划
     * @return 即将被置为失败的学习计划
     */
    public List<Event> selectAllFailedLearningEvents(){
        long now = System.currentTimeMillis();
        String querySql = "SELECT * FROM " + mTableName + " WHERE " + LEARNING_EVENT_GROUP_ID + " IN " +
                "(SELECT DISTINCT LEARNING_EVENT_GROUP_ID FROM event " +
                "WHERE EVENT_TYPE = 1 AND EVENT_PROCESS = 1 AND " +
                "(((EVENT_SEQUENCE = 1 OR EVENT_SEQUENCE = 2) AND " + now + " >= (EVENT_EXPECTED_FINISHED_DATE + 86400000) AND EVENT_FINISHED_TIME IS NULL)" +
                " OR ("+ now +" >= (EVENT_EXPECTED_FINISHED_DATE + 172800000) AND EVENT_FINISHED_TIME IS NULL) ))";
        LogUtils.d("fuck",querySql);
        return _select(querySql);
    }

    /**
     * 通过学习计划组id删除计划(实际上删的是学习计划)
     * @param learningEventGroupId 学习计划组id
     * @return 删除计划的个数
     */
    public int deleteEventsByLearningEventGroupId(Long learningEventGroupId){
        String whereClause = LEARNING_EVENT_GROUP_ID + " = " + learningEventGroupId;
        return _delete(whereClause,null);
    }

    /** 更新过期的普通计划 */
    public void updateExpiredNormalEvents() {
        String updateSql = "UPDATE " + mTableName + " SET " + EVENT_PROCESS + " = ? WHERE " +
                EVENT_PROCESS + " != ? AND (" +
                EVENT_EXPECTED_FINISHED_DATE + " + ?) <= ? AND " + IS_EVENT_FINISHED + " = 0 AND "+
                EVENT_TYPE + " = ?";
        LogUtils.d("update events","更新过期的普通计划 : " + updateSql);
        db.execSQL(updateSql, new Object[]{EventConfig.PROCESS_EXPIRED,
                EventConfig.PROCESS_EXPIRED, DateUtils.DAY_MILLISECONDS,
                System.currentTimeMillis(), EventConfig.TYPE_SPEC_NORMAL});
    }

    /** 更新失败的学习计划组 */
    public void updateFailedLearningEventGroups(){
        String updateSql = "UPDATE learning_event_group SET KNOWLEDGE_QUANTITY = 0,LEARNING_EVENT_FINISHED_COUNT = 0 " +
                "WHERE PK_LEARNING_EVENT_GROUP_ID IN " +
                "(SELECT DISTINCT LEARNING_EVENT_GROUP_ID FROM event " +
                "WHERE EVENT_TYPE = 1 AND EVENT_PROCESS = 1 AND " +
                "((EVENT_SEQUENCE = 1 OR EVENT_SEQUENCE = 2) AND ? >= (EVENT_EXPECTED_FINISHED_DATE + 86400000)" +
                " OR ? >= (EVENT_EXPECTED_FINISHED_DATE + 172800000) ))";
        long now = System.currentTimeMillis();
        LogUtils.d("update events","更新失败的学习计划组 : " + updateSql);
        db.execSQL(updateSql,new Object[]{now,now});
    }

    /** 更新失败的学习计划 */
    public void updateFailedLearningEvents() {
        String updateSql = "UPDATE event SET EVENT_PROCESS = 3,IS_EVENT_FINISHED = 0 " +
                "WHERE LEARNING_EVENT_GROUP_ID IN " +
                "(SELECT DISTINCT LEARNING_EVENT_GROUP_ID FROM event " +
                "WHERE EVENT_TYPE = 1 AND EVENT_PROCESS = 1 AND " +
                "((EVENT_SEQUENCE = 1 OR EVENT_SEQUENCE = 2) AND ? >= (EVENT_EXPECTED_FINISHED_DATE + 86400000)" +
                " OR ? >= (EVENT_EXPECTED_FINISHED_DATE + 172800000) ))";
        long now = System.currentTimeMillis();
        LogUtils.d("update events","更新失败的学习计划 : " + updateSql);
        db.execSQL(updateSql,new Object[]{now,now});
    }

    /** 搜索模糊计划 */
    public List<Event> selectAbstEventsByDescription(String key) {
        String querySql = "SELECT * FROM " + mTableName + " WHERE " +
                EVENT_TYPE + " = " + EventConfig.TYPE_ABSTRACT +
                " AND " + DESCRIPTION + " LIKE %?%";
        return _select(querySql,new String[]{ key });
    }

    /** 搜索具体计划 */
    public List<Event> selectSpecEventsByDescription(String key) {
        String querySql = "SELECT * FROM " + mTableName + " WHERE " +
                EVENT_TYPE + " != " + EventConfig.TYPE_ABSTRACT +
                " AND " + DESCRIPTION + " LIKE %?%";
        return _select(querySql,new String[]{ key });
    }
}
