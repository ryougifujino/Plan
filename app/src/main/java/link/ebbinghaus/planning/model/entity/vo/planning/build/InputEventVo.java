package link.ebbinghaus.planning.model.entity.vo.planning.build;

import android.os.Parcel;

import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * 在PlanningBuild模块用来组织输入信息的vo<br>
 * 增加了一个只在这个页面使用的未来最大计划宽度限制的属性;<br>
 * 增加了一个在PlanningBuildActivity进行插入一条新的学习计划组记录时,<br>
 * 所用到的方案strategy
 */
public class InputEventVo extends Event {
    private Integer maxWidth;
    private Integer strategy;

    public Integer getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(Integer maxWidth) {
        this.maxWidth = maxWidth;
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }

    public void copyFrom(InputEventVo inputEvent){
        setPkEventId(inputEvent.getPkEventId());
        setLearningEventGroupId(inputEvent.getLearningEventGroupId());
        setEventGroupId(inputEvent.getEventGroupId());
        setDescription(inputEvent.getDescription());
        setSummary(inputEvent.getSummary());
        setEventType(inputEvent.getEventType());
        setEventSubtypeId(inputEvent.getEventSubtypeId());
        setEventSequence(inputEvent.getEventSequence());
        setIsShowEventSequence(inputEvent.getIsShowEventSequence());
        setCreateTime(inputEvent.getCreateTime());
        setEventExpectedFinishedDate(inputEvent.getEventExpectedFinishedDate());
        setEventFinishedTime(inputEvent.getEventFinishedTime());
        setIsEventFinished(inputEvent.getIsEventFinished());
        setGreekAlphabetId(inputEvent.getGreekAlphabetId());
        setIsGreekAlphabetMarked(inputEvent.getIsGreekAlphabetMarked());
        setIsRemind(inputEvent.getIsRemind());
        setRemindTime(inputEvent.getRemindTime());
        setEventProcess(inputEvent.getEventProcess());
        setMaxWidth(inputEvent.getMaxWidth());
        setStrategy(inputEvent.getStrategy());
    }

    /* Parcelable */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeValue(this.maxWidth);
        dest.writeValue(this.strategy);
    }

    public InputEventVo() {
    }

    protected InputEventVo(Parcel in) {
        super(in);
        this.maxWidth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.strategy = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<InputEventVo> CREATOR = new Creator<InputEventVo>() {
        @Override
        public InputEventVo createFromParcel(Parcel source) {
            return new InputEventVo(source);
        }

        @Override
        public InputEventVo[] newArray(int size) {
            return new InputEventVo[size];
        }
    };
}
