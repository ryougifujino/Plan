package link.ebbinghaus.planning.model.entity.po;


import android.content.ContentValues;
import android.database.Cursor;

import com.yurikami.lib.util.Utils;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class Event {
    private Long pkEventId;
    private Long learningEventGroupId;
    private Long eventGroupId;
    private String description;
    private String summary;
    private Integer eventType;   //1:学习型 2:普通型 3:模糊型
    private Long eventSubtypeId;
    private Integer eventSequence;
    private Boolean isShowEventSequence;   //1:true 0:false
    private Long createTime; //精确到秒
    private Long eventExpectedFinishedDate;   //精确到日
    private Long eventFinishedTime;
    private Boolean isEventFinished;
    private Boolean isGreekAlphabetMarked;
    private Boolean isRemind;
    private Long remindTime;
    private Integer eventProcess; //1:未开始 2:进行中/待办 3:成功/完成 4:失败/过期

    public Long getPkEventId() {
        return pkEventId;
    }

    public void setPkEventId(Long pkEventId) {
        this.pkEventId = pkEventId;
    }

    public Long getLearningEventGroupId() {
        return learningEventGroupId;
    }

    public void setLearningEventGroupId(Long learningEventGroupId) {
        this.learningEventGroupId = learningEventGroupId;
    }

    public Long getEventGroupId() {
        return eventGroupId;
    }

    public void setEventGroupId(Long eventGroupId) {
        this.eventGroupId = eventGroupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Long getEventSubtypeId() {
        return eventSubtypeId;
    }

    public void setEventSubtypeId(Long eventSubtypeId) {
        this.eventSubtypeId = eventSubtypeId;
    }

    public Integer getEventSequence() {
        return eventSequence;
    }

    public void setEventSequence(Integer eventSequence) {
        this.eventSequence = eventSequence;
    }

    public Boolean getIsShowEventSequence() {
        return isShowEventSequence;
    }

    public void setIsShowEventSequence(Boolean isShowEventSequence) {
        this.isShowEventSequence = isShowEventSequence;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getEventExpectedFinishedDate() {
        return eventExpectedFinishedDate;
    }

    public void setEventExpectedFinishedDate(Long eventExpectedFinishedDate) {
        this.eventExpectedFinishedDate = eventExpectedFinishedDate;
    }

    public Long getEventFinishedTime() {
        return eventFinishedTime;
    }

    public void setEventFinishedTime(Long eventFinishedTime) {
        this.eventFinishedTime = eventFinishedTime;
    }

    public Boolean getIsEventFinished() {
        return isEventFinished;
    }

    public void setIsEventFinished(Boolean isEventFinished) {
        this.isEventFinished = isEventFinished;
    }

    public Boolean getIsGreekAlphabetMarked() {
        return isGreekAlphabetMarked;
    }

    public void setIsGreekAlphabetMarked(Boolean isGreekAlphabetMarked) {
        this.isGreekAlphabetMarked = isGreekAlphabetMarked;
    }

    public Boolean getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(Boolean isRemind) {
        this.isRemind = isRemind;
    }

    public Long getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Long remindTime) {
        this.remindTime = remindTime;
    }

    public Integer getEventProcess() {
        return eventProcess;
    }

    public void setEventProcess(Integer eventProcess) {
        this.eventProcess = eventProcess;
    }

    /* --- */

    public void setIsShowEventSequence(Integer isShowEventSequence){
        setIsShowEventSequence(Utils.int2Bool(isShowEventSequence));
    }

    public void setIsEventFinished(Integer isEventFinished) {
        setIsEventFinished(Utils.int2Bool(isEventFinished));
    }

    public void setIsGreekAlphabetMarked(Integer isGreekAlphabetMarked) {
        setIsGreekAlphabetMarked(Utils.int2Bool(isGreekAlphabetMarked));
    }

    public void setIsRemind(Integer isRemind) {
        setIsRemind(Utils.int2Bool(isRemind));
    }


    public void convertToContentValues(ContentValues values){
        values.put(DBConfig.EventColumn.PK_EVENT_ID, pkEventId);
        values.put(DBConfig.EventColumn.LEARNING_EVENT_GROUP_ID, learningEventGroupId);
        values.put(DBConfig.EventColumn.EVENT_GROUP_ID, eventGroupId);
        values.put(DBConfig.EventColumn.DESCRIPTION, description);
        values.put(DBConfig.EventColumn.SUMMARY, summary);
        values.put(DBConfig.EventColumn.EVENT_TYPE, eventType);
        values.put(DBConfig.EventColumn.EVENT_SUBTYPE_ID, eventSubtypeId);
        values.put(DBConfig.EventColumn.EVENT_SEQUENCE, eventSequence);
        values.put(DBConfig.EventColumn.IS_SHOW_EVENT_SEQUENCE, isShowEventSequence);
        values.put(DBConfig.EventColumn.CREATE_TIME, createTime);
        values.put(DBConfig.EventColumn.EVENT_EXPECTED_FINISHED_DATE, eventExpectedFinishedDate);
        values.put(DBConfig.EventColumn.EVENT_FINISHED_TIME, eventFinishedTime);
        values.put(DBConfig.EventColumn.IS_EVENT_FINISHED, isEventFinished);
        values.put(DBConfig.EventColumn.IS_GREEK_ALPHABET_MARKED, isGreekAlphabetMarked);
        values.put(DBConfig.EventColumn.IS_REMIND, isRemind);
        values.put(DBConfig.EventColumn.REMIND_TIME, remindTime);
        values.put(DBConfig.EventColumn.EVENT_PROCESS, eventProcess);
    }
    
    public void filledByCursor(Cursor cursor){
        setPkEventId(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.PK_EVENT_ID)));
        setLearningEventGroupId(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.LEARNING_EVENT_GROUP_ID)));
        setEventGroupId(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_GROUP_ID)));
        setDescription(cursor.getString(cursor.getColumnIndex(DBConfig.EventColumn.DESCRIPTION)));
        setSummary(cursor.getString(cursor.getColumnIndex(DBConfig.EventColumn.SUMMARY)));
        setEventType(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_TYPE)));
        setEventSubtypeId(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_SUBTYPE_ID)));
        setEventSequence(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_SEQUENCE)));
        setIsShowEventSequence(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.IS_SHOW_EVENT_SEQUENCE)));
        setCreateTime(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.CREATE_TIME)));
        setEventExpectedFinishedDate(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_EXPECTED_FINISHED_DATE)));
        setEventFinishedTime(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_FINISHED_TIME)));
        setIsEventFinished(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.IS_EVENT_FINISHED)));
        setIsGreekAlphabetMarked(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.IS_GREEK_ALPHABET_MARKED)));
        setIsRemind(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.IS_REMIND)));
        setRemindTime(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.REMIND_TIME)));
        setEventProcess(cursor.getInt(cursor.getColumnIndex(DBConfig.EventColumn.EVENT_PROCESS)));
    }

    public void copyFrom(Event event){
        setPkEventId(event.getPkEventId());
        setLearningEventGroupId(event.getLearningEventGroupId());
        setEventGroupId(event.getEventGroupId());
        setDescription(event.getDescription());
        setSummary(event.getSummary());
        setEventType(event.getEventType());
        setEventSubtypeId(event.getEventSubtypeId());
        setEventSequence(event.getEventSequence());
        setIsShowEventSequence(event.getIsShowEventSequence());
        setCreateTime(event.getCreateTime());
        setEventExpectedFinishedDate(event.getEventExpectedFinishedDate());
        setEventFinishedTime(event.getEventFinishedTime());
        setIsEventFinished(event.getIsEventFinished());
        setIsGreekAlphabetMarked(event.getIsGreekAlphabetMarked());
        setIsRemind(event.getIsRemind());
        setRemindTime(event.getRemindTime());
        setEventProcess(event.getEventProcess());
    }
}
