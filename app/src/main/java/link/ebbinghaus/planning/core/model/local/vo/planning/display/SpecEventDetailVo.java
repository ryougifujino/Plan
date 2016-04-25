package link.ebbinghaus.planning.core.model.local.vo.planning.display;

import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.model.local.po.EventSubtype;
import link.ebbinghaus.planning.core.model.local.po.LearningEventGroup;
import link.ebbinghaus.planning.core.model.local.po.EventGroup;

/**
 * 计划详情的vo
 */
public class SpecEventDetailVo {
    public Event event;
    public LearningEventGroup learningEventGroup;
    public EventGroup eventGroup;
    public EventSubtype eventSubtype;
}
