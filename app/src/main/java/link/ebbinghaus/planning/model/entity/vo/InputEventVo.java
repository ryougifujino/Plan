package link.ebbinghaus.planning.model.entity.vo;

import link.ebbinghaus.planning.model.entity.po.Event;

/**
 * 在PlanningBuildSpec模块用来组织输入信息的vo<br>
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
        setIsGreekAlphabetMarked(inputEvent.getIsGreekAlphabetMarked());
        setIsRemind(inputEvent.getIsRemind());
        setRemindTime(inputEvent.getRemindTime());
        setEventProcess(inputEvent.getEventProcess());
        setMaxWidth(inputEvent.getMaxWidth());
        setStrategy(inputEvent.getStrategy());
    }
}
