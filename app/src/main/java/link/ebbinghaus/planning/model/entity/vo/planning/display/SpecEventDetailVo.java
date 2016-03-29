package link.ebbinghaus.planning.model.entity.vo.planning.display;

import link.ebbinghaus.planning.model.entity.po.Event;
import link.ebbinghaus.planning.model.entity.po.EventGroup;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;

/**
 * 计划详情的vo
 */
public class SpecEventDetailVo {
    public Event event;
    public LearningEventGroup learningEventGroup;
    public EventGroup eventGroup;
    public EventSubtype eventSubtype;
}
