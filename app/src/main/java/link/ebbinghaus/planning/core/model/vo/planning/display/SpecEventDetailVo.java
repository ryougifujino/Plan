package link.ebbinghaus.planning.core.model.vo.planning.display;

import link.ebbinghaus.planning.core.model.po.Event;
import link.ebbinghaus.planning.core.model.po.LearningEventGroup;
import link.ebbinghaus.planning.core.model.po.EventSubtype;
import link.ebbinghaus.planning.core.model.po.EventGroup;

/**
 * 计划详情的vo
 */
public class SpecEventDetailVo {
    public Event event;
    public LearningEventGroup learningEventGroup;
    public EventGroup eventGroup;
    public EventSubtype eventSubtype;
}
