package link.ebbinghaus.planning.model.entity.po;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;

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
        values.put(DBConfig.EventGroupColumn.PK_EVENT_GROUP_ID, pkEventGroupId);
        values.put(DBConfig.EventGroupColumn.CREATE_TIME, createTime);
        values.put(DBConfig.EventGroupColumn.DESCRIPTION, description);
        values.put(DBConfig.EventGroupColumn.LEARNING_EVENT_COUNT, learningEventCount);
        values.put(DBConfig.EventGroupColumn.NORMAL_EVENT_COUNT, normalEventCount);
        values.put(DBConfig.EventGroupColumn.ABSTRACT_EVENT_COUNT, abstractEventCount);
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

    protected EventGroup(Parcel in) {
        createTime = in.readLong();
        description = in.readString();
    }

    public static final Creator<EventGroup> CREATOR = new Creator<EventGroup>() {
        @Override
        public EventGroup createFromParcel(Parcel in) {
            return new EventGroup(in);
        }

        @Override
        public EventGroup[] newArray(int size) {
            return new EventGroup[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(createTime);
        dest.writeString(description);
    }
}
