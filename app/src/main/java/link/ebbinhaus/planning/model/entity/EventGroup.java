package link.ebbinhaus.planning.model.entity;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class EventGroup {
    private Integer pkEventGroupId;
    private long createTime;
    private String description;
    private Integer learningEventCount;
    private Integer normalOrAbstractEventCount;

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

    public Integer getNormalOrAbstractEventCount() {
        return normalOrAbstractEventCount;
    }

    public void setNormalOrAbstractEventCount(Integer normalOrAbstractEventCount) {
        this.normalOrAbstractEventCount = normalOrAbstractEventCount;
    }
}
