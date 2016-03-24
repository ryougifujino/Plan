package link.ebbinghaus.planning.custom.constant.config.entity;

/**
 * Created by WINFIELD on 2016/3/23.
 */
public class LearningEventGroupConfig {
    public static final int[] STRATEGY_COMPREHENSIVE = {1, 2, 31};
    public static final int[] STRATEGY_MEMORIAL = {1, 2, 6, 31};
    public static final int[] STRATEGY_MEMORIAL_PRO = {1, 2, 4, 7, 15, 31};
    public static final int[] STRATEGY_PERSISTENT = {1, 2, 4, 7, 15, 31, 60, 90, 120, 150, 180};

    public static final String COMPREHENSIVE = "理解型";
    public static final String MEMORIAL = "记忆型";
    public static final String MEMORIAL_PRO = "强记型";
    public static final String PERSISTENT = "永久型";

    //每个类型描述的数据库对应值正好和它们在它们数组的索引+1所对应
    public static final String[] DESCRIPTIONS_STRATEGY = {COMPREHENSIVE, MEMORIAL, MEMORIAL_PRO, PERSISTENT};

    public static final int TYPE_COMPREHENSIVE = 1;
    public static final int TYPE_MEMORIAL = 2;
    public static final int TYPE_MEMORIAL_PRO = 3;
    public static final int TYPE_PERSISTENT = 4;


}
