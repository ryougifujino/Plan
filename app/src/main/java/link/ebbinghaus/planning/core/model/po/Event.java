package link.ebbinghaus.planning.core.model.po;


import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.yurikami.lib.util.NonNullContentValues;
import com.yurikami.lib.util.Utils;

import link.ebbinghaus.planning.common.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class Event implements Parcelable {
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
    private Long greekAlphabetId;
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

    public Long getGreekAlphabetId() {
        return greekAlphabetId;
    }

    public void setGreekAlphabetId(Long greekAlphabetId) {
        this.greekAlphabetId = greekAlphabetId;
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
        NonNullContentValues nonNullValues = new NonNullContentValues(values);
        nonNullValues.put(DBConfig.EventColumn.PK_EVENT_ID, pkEventId);
        nonNullValues.put(DBConfig.EventColumn.LEARNING_EVENT_GROUP_ID, learningEventGroupId);
        nonNullValues.put(DBConfig.EventColumn.EVENT_GROUP_ID, eventGroupId);
        nonNullValues.put(DBConfig.EventColumn.DESCRIPTION, description);
        nonNullValues.put(DBConfig.EventColumn.SUMMARY, summary);
        nonNullValues.put(DBConfig.EventColumn.EVENT_TYPE, eventType);
        nonNullValues.put(DBConfig.EventColumn.EVENT_SUBTYPE_ID, eventSubtypeId);
        nonNullValues.put(DBConfig.EventColumn.EVENT_SEQUENCE, eventSequence);
        nonNullValues.put(DBConfig.EventColumn.IS_SHOW_EVENT_SEQUENCE, isShowEventSequence);
        nonNullValues.put(DBConfig.EventColumn.CREATE_TIME, createTime);
        nonNullValues.put(DBConfig.EventColumn.EVENT_EXPECTED_FINISHED_DATE, eventExpectedFinishedDate);
        nonNullValues.put(DBConfig.EventColumn.EVENT_FINISHED_TIME, eventFinishedTime);
        nonNullValues.put(DBConfig.EventColumn.IS_EVENT_FINISHED, isEventFinished);
        nonNullValues.put(DBConfig.EventColumn.GREEK_ALPHABET_ID, greekAlphabetId);
        nonNullValues.put(DBConfig.EventColumn.IS_GREEK_ALPHABET_MARKED, isGreekAlphabetMarked);
        nonNullValues.put(DBConfig.EventColumn.IS_REMIND, isRemind);
        nonNullValues.put(DBConfig.EventColumn.REMIND_TIME, remindTime);
        nonNullValues.put(DBConfig.EventColumn.EVENT_PROCESS, eventProcess);
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
        setGreekAlphabetId(cursor.getLong(cursor.getColumnIndex(DBConfig.EventColumn.GREEK_ALPHABET_ID)));
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
        setGreekAlphabetId(event.getGreekAlphabetId());
        setIsGreekAlphabetMarked(event.getIsGreekAlphabetMarked());
        setIsRemind(event.getIsRemind());
        setRemindTime(event.getRemindTime());
        setEventProcess(event.getEventProcess());
    }

    /* Parcelable */

    public Event() {
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.pkEventId);
        dest.writeValue(this.learningEventGroupId);
        dest.writeValue(this.eventGroupId);
        dest.writeString(this.description);
        dest.writeString(this.summary);
        dest.writeValue(this.eventType);
        dest.writeValue(this.eventSubtypeId);
        dest.writeValue(this.eventSequence);
        dest.writeValue(this.isShowEventSequence);
        dest.writeValue(this.createTime);
        dest.writeValue(this.eventExpectedFinishedDate);
        dest.writeValue(this.eventFinishedTime);
        dest.writeValue(this.isEventFinished);
        dest.writeValue(this.greekAlphabetId);
        dest.writeValue(this.isGreekAlphabetMarked);
        dest.writeValue(this.isRemind);
        dest.writeValue(this.remindTime);
        dest.writeValue(this.eventProcess);
    }

    protected Event(Parcel in) {
        this.pkEventId = (Long) in.readValue(Long.class.getClassLoader());
        this.learningEventGroupId = (Long) in.readValue(Long.class.getClassLoader());
        this.eventGroupId = (Long) in.readValue(Long.class.getClassLoader());
        this.description = in.readString();
        this.summary = in.readString();
        this.eventType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.eventSubtypeId = (Long) in.readValue(Long.class.getClassLoader());
        this.eventSequence = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isShowEventSequence = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.createTime = (Long) in.readValue(Long.class.getClassLoader());
        this.eventExpectedFinishedDate = (Long) in.readValue(Long.class.getClassLoader());
        this.eventFinishedTime = (Long) in.readValue(Long.class.getClassLoader());
        this.isEventFinished = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.greekAlphabetId = (Long) in.readValue(Long.class.getClassLoader());
        this.isGreekAlphabetMarked = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isRemind = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.remindTime = (Long) in.readValue(Long.class.getClassLoader());
        this.eventProcess = (Integer) in.readValue(Integer.class.getClassLoader());
    }

}
