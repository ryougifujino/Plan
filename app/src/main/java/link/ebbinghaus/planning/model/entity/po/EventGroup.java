package link.ebbinghaus.planning.model.entity.po;

import android.content.ContentValues;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class EventGroup {
    private Integer pkEventGroupId;
    private long createTime;
    private String description;
    private Integer learningEventCount;
    private Integer normalEventCount;
    private Integer abstractEventCount;

    public Integer getPkEventGroupId() {
        return pkEventGroupId;
    }

    public void setPkEventGroupId(Integer pkEventGroupId) {
        this.pkEventGroupId = pkEventGroupId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
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

    public void convertToContentValues(ContentValues values){
        values.put(DBConfig.EventGroupColumn.PK_EVENT_GROUP_ID, pkEventGroupId);
        values.put(DBConfig.EventGroupColumn.CREATE_TIME, createTime);
        values.put(DBConfig.EventGroupColumn.DESCRIPTION, description);
        values.put(DBConfig.EventGroupColumn.LEARNING_EVENT_COUNT, learningEventCount);
        values.put(DBConfig.EventGroupColumn.NORMAL_EVENT_COUNT, normalEventCount);
        values.put(DBConfig.EventGroupColumn.ABSTRACT_EVENT_COUNT, abstractEventCount);
    }
}
