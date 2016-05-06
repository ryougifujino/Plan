package link.ebbinghaus.planning.app.constant.model;

import link.ebbinghaus.planning.R;

/**
 * Created by WINFIELD on 2016/3/26.
 */
public class EventConstant {
    public static final int[] PROCESS_LEARNING = {R.string.event_process_common_not_started, R.string.event_process_learning_in_progress,R.string.event_process_learning_succeed, R.string.event_process_learning_failed};  // TODO: 2016/4/5 诸如此类可以在res里面代替 arr.xml

    public static final int[] PROCESS_NORMAL = {R.string.event_process_common_not_started, R.string.event_process_normal_todo,R.string.event_process_normal_finished, R.string.event_process_normal_expire};

    public static final int[] EVENT_TYPE = {R.string.event_type_spec_learning, R.string.event_type_spec_normal,R.string.event_type_abstract};
}
