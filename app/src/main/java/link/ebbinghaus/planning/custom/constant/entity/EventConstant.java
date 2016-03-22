package link.ebbinghaus.planning.custom.constant.entity;

/**
 * Created by WINFIELD on 2016/3/19.
 */
public class EventConstant {
    public static final String STRATEGY_COMPREHENSIVE = "理解型";
    public static final String STRATEGY_MEMORIAL = "记忆型";
    public static final String STRATEGY_MEMORIAL_PRO = "强记型";
    public static final String STRATEGY_PERSISTENT = "永久型";

    public static final String[] STRATEGYS= {STRATEGY_COMPREHENSIVE,STRATEGY_MEMORIAL,STRATEGY_MEMORIAL_PRO,STRATEGY_PERSISTENT};

    //1:学习型 2:普通型 3:模糊型
    public static final int SPEC_LEARNING_TYPE = 1;
    public static final int SPEC_NORMAL_TYPE = 2;
    public static final int ABSTRACT_TYPE = 3;
}
