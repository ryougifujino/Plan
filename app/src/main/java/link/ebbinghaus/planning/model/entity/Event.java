package link.ebbinghaus.planning.model.entity;


/**
 * Created by WINFIELD on 2016/2/29.
 */
public class Event {
    private Integer pkEventId;
    private Integer learningEventGroupId;
    private Integer eventGroupId;
    private String description;
    private String summary;
    private Integer eventType;   //1:学习型 2:普通型 3:模糊型
    private Integer eventSubtypeId;
    private Integer eventSequence;
    private boolean isShowEventSequence;   //1:true 2:false
    private long createTime; //精确到秒
    private long eventExpectedFinishedDate;   //精确到日
    private long eventFinishedTime;
    private boolean isEventFinished;
    private boolean isGreekAlphabetMarked;
    private boolean isRemind;
    private long remindTime;
    private Integer eventProcess; //1:未开始 2:进行中/待办 3:成功/完成 4:失败/过期

    public Integer getPkEventId() {
        return pkEventId;
    }

    public void setPkEventId(Integer pkEventId) {
        this.pkEventId = pkEventId;
    }

    public Integer getLearningEventGroupId() {
        return learningEventGroupId;
    }

    public void setLearningEventGroupId(Integer learningEventGroupId) {
        this.learningEventGroupId = learningEventGroupId;
    }

    public Integer getEventGroupId() {
        return eventGroupId;
    }

    public void setEventGroupId(Integer eventGroupId) {
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

    public Integer getEventSubtypeId() {
        return eventSubtypeId;
    }

    public void setEventSubtypeId(Integer eventSubtypeId) {
        this.eventSubtypeId = eventSubtypeId;
    }

    public Integer getEventSequence() {
        return eventSequence;
    }

    public void setEventSequence(Integer eventSequence) {
        this.eventSequence = eventSequence;
    }

    public boolean isShowEventSequence() {
        return isShowEventSequence;
    }

    public void setIsShowEventSequence(boolean isShowEventSequence) {
        this.isShowEventSequence = isShowEventSequence;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getEventExpectedFinishedDate() {
        return eventExpectedFinishedDate;
    }

    public void setEventExpectedFinishedDate(long eventExpectedFinishedDate) {
        this.eventExpectedFinishedDate = eventExpectedFinishedDate;
    }

    public long getEventFinishedTime() {
        return eventFinishedTime;
    }

    public void setEventFinishedTime(long eventFinishedTime) {
        this.eventFinishedTime = eventFinishedTime;
    }

    public boolean isEventFinished() {
        return isEventFinished;
    }

    public void setIsEventFinished(boolean isEventFinished) {
        this.isEventFinished = isEventFinished;
    }

    public boolean isGreekAlphabetMarked() {
        return isGreekAlphabetMarked;
    }

    public void setIsGreekAlphabetMarked(boolean isGreekAlphabetMarked) {
        this.isGreekAlphabetMarked = isGreekAlphabetMarked;
    }

    public boolean isRemind() {
        return isRemind;
    }

    public void setIsRemind(boolean isRemind) {
        this.isRemind = isRemind;
    }

    public long getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(long remindTime) {
        this.remindTime = remindTime;
    }

    public Integer getEventProcess() {
        return eventProcess;
    }

    public void setEventProcess(Integer eventProcess) {
        this.eventProcess = eventProcess;
    }
}
