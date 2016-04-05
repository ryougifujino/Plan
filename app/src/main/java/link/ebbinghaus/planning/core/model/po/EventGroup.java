package link.ebbinghaus.planning.core.model.po;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.yurikami.lib.util.NonNullContentValues;

import link.ebbinghaus.planning.common.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class EventGroup implements Parcelable{
    private Long pkEventGroupId;
    private Long createTime;
    private String description;
    private Integer learningEventCount;
    private Integer normalEventCount;
    private Integer abstractEventCount;

    public Long getPkEventGroupId() {
        return pkEventGroupId;
    }

    public void setPkEventGroupId(Long pkEventGroupId) {
        this.pkEventGroupId = pkEventGroupId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLearningEventCount() {
        return learningEventCount;
    }

    public void setLearningEventCount(Integer learningEventCount) {
        this.learningEventCount = learningEventCount;
    }

    public Integer getNormalEventCount() {
        return normalEventCount;
    }

    public void setNormalEventCount(Integer normalEventCount) {
        this.normalEventCount = normalEventCount;
    }

    public Integer getAbstractEventCount() {
        return abstractEventCount;
    }

    public void setAbstractEventCount(Integer abstractEventCount) {
        this.abstractEventCount = abstractEventCount;
    }


    /** 辅助方法 */

    public void convertToContentValues(ContentValues values){
        NonNullContentValues nonNullValues = new NonNullContentValues(values);
        nonNullValues.put(DBConfig.EventGroupColumn.PK_EVENT_GROUP_ID, pkEventGroupId);
        nonNullValues.put(DBConfig.EventGroupColumn.CREATE_TIME, createTime);
        nonNullValues.put(DBConfig.EventGroupColumn.DESCRIPTION, description);
        nonNullValues.put(DBConfig.EventGroupColumn.LEARNING_EVENT_COUNT, learningEventCount);
        nonNullValues.put(DBConfig.EventGroupColumn.NORMAL_EVENT_COUNT, normalEventCount);
        nonNullValues.put(DBConfig.EventGroupColumn.ABSTRACT_EVENT_COUNT, abstractEventCount);
    }
    
    public void filledByCursor(Cursor cursor){
        setPkEventGroupId(cursor.getLong(cursor.getColumnIndex(DBConfig.EventGroupColumn.PK_EVENT_GROUP_ID)));
        setCreateTime(cursor.getLong(cursor.getColumnIndex(DBConfig.EventGroupColumn.CREATE_TIME)));
        setDescription(cursor.getString(cursor.getColumnIndex(DBConfig.EventGroupColumn.DESCRIPTION)));
        setLearningEventCount(cursor.getInt(cursor.getColumnIndex(DBConfig.EventGroupColumn.LEARNING_EVENT_COUNT)));
        setNormalEventCount(cursor.getInt(cursor.getColumnIndex(DBConfig.EventGroupColumn.NORMAL_EVENT_COUNT)));
        setAbstractEventCount(cursor.getInt(cursor.getColumnIndex(DBConfig.EventGroupColumn.ABSTRACT_EVENT_COUNT)));
    }

    public void copyFrom(EventGroup eventGroup){
        setPkEventGroupId(eventGroup.getPkEventGroupId());
        setCreateTime(eventGroup.getCreateTime());
        setDescription(eventGroup.getDescription());
        setLearningEventCount(eventGroup.getLearningEventCount());
        setNormalEventCount(eventGroup.getNormalEventCount());
        setAbstractEventCount(eventGroup.getAbstractEventCount());
    }

    /** Parcelable方法 */

    public EventGroup(){}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.pkEventGroupId);
        dest.writeValue(this.createTime);
        dest.writeString(this.description);
        dest.writeValue(this.learningEventCount);
        dest.writeValue(this.normalEventCount);
        dest.writeValue(this.abstractEventCount);
    }

    protected EventGroup(Parcel in) {
        this.pkEventGroupId = (Long) in.readValue(Long.class.getClassLoader());
        this.createTime = (Long) in.readValue(Long.class.getClassLoader());
        this.description = in.readString();
        this.learningEventCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.normalEventCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.abstractEventCount = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<EventGroup> CREATOR = new Creator<EventGroup>() {
        @Override
        public EventGroup createFromParcel(Parcel source) {
            return new EventGroup(source);
        }

        @Override
        public EventGroup[] newArray(int size) {
            return new EventGroup[size];
        }
    };
}
