package link.ebbinghaus.planning.custom.constant.config.entity;

/**
 * Created by WINFIELD on 2016/3/19.
 */
public class EventConfig {

    //1:学习型 2:普通型 3:模糊型
    public static final int TYPE_SPEC_LEARNING = 1;
    public static final int TYPE_SPEC_NORMAL = 2;
    public static final int TYPE_ABSTRACT = 3;

    //进程定义
    /** 公用 */
    public static final String NOT_STARTED = "未开始";
    public static final int PROCESS_NOT_STARTED = 1;
    /** 学习计划 */
    public static final String IN_PROGRESS = "进行中";
    public static final String SUCCEED = "成功";
    public static final String FAILED = "失败";
    public static final String[] PROCESSES_LEARNING = {NOT_STARTED,IN_PROGRESS,SUCCEED, FAILED};
    public static final int PROCESS_IN_PROGRESS = 2;
    public static final int PROCESS_SUCCEED = 3;
    public static final int PROCESS_FAILED = 4;
    /** 普通计划 */
    public static final String TODO = "待办";
    public static final String FINISHED = "完成";
    public static final String EXPIRE = "过期";
    public static final String[] PROCESSES_NORMAL = {NOT_STARTED,TODO,FINISHED, EXPIRE};
    public static final int PROCESS_TODO = PROCESS_IN_PROGRESS;
    public static final int PROCESS_FINISHED = PROCESS_SUCCEED;
    public static final int PROCESS_EXPIRE = PROCESS_FAILED;


}
