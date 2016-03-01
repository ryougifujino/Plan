package link.ebbinghaus.planning.custom.constant.config;

import com.yurikami.lib.db.SqlBuilder;

/**
 * Created by WINFIELD on 2016/2/28.
 */
public class DBConfig {
    public static final String DB_NAME = "planning.db3";
    public static final int DB_VERSION = 1;

    private static SqlBuilder sql = new SqlBuilder();
    public static final String CREATE_TABLE_EVENT = sql.create(Table.EVENT)
            .pk(EventColumn.PK_EVENT_ID)
            .integer(EventColumn.LEARNING_EVENT_GROUP_ID).integer(EventColumn.EVENT_GROUP_ID)
            .text(EventColumn.DESCRIPTION).text(EventColumn.SUMMARY)
            .integer(EventColumn.EVENT_TYPE).integer(EventColumn.EVENT_SUBTYPE_ID)
            .integer(EventColumn.EVENT_SEQUENCE).integer(EventColumn.IS_SHOW_EVENT_SEQUENCE)
            .integer(EventColumn.CREATE_TIME)
            .integer(EventColumn.EVENT_EXPECTED_FINISHED_DATE).integer(EventColumn.EVENT_FINISHED_TIME)
            .integer(EventColumn.IS_EVENT_FINISHED).integer(EventColumn.IS_GREEK_ALPHABET_MARKED)
            .integer(EventColumn.IS_REMIND).integer(EventColumn.REMIND_TIME)
            .last(EventColumn.EVENT_PROCESS,sql.INTEGER).sql();
    public static final String CREATE_TABLE_LEARNING_EVENT_GROUP = sql.create(Table.LEARNING_EVENT_GROUP)
            .pk(LearningEventGroupColumn.PK_LEARNING_EVENT_GROUP_ID)
            .integer(LearningEventGroupColumn.KNOWLEDGE_QUANTITY).integer(LearningEventGroupColumn.STRATEGY)
            .integer(LearningEventGroupColumn.LEARNING_EVENT_TOTAL).integer(LearningEventGroupColumn.LEARNING_EVENT_FINISHED_COUNT)
            .integer(LearningEventGroupColumn.WORKLOAD).real(LearningEventGroupColumn.EFFICIENCY)
            .last(LearningEventGroupColumn.UNDERSTANDING_DEGREE,sql.REAL).sql();
    public static final String CREATE_TABLE_EVENT_GROUP = sql.create(Table.EVENT_GROUP)
            .pk(EventGroupColumn.PK_EVENT_GROUP_ID)
            .integer(EventGroupColumn.CREATE_TIME).text(EventGroupColumn.DESCRIPTION)
            .integer(EventGroupColumn.LEARNING_EVENT_COUNT)
            .last(EventGroupColumn.NORMAL_OR_ABSTRACT_EVENT_COUNT,sql.INTEGER).sql();
    public static final String CREATE_TABLE_EVENT_SUBTYPE = sql.create(Table.EVENT_SUBTYPE)
            .pk(EventSubtypeColumn.PK_EVENT_SUBTYPE_ID)
            .last(EventSubtypeColumn.EVENT_SUBTYPE,sql.TEXT).sql();
    public static final String CREATE_TABLE_FAST_TEMPLATE = sql.create(Table.FAST_TEMPLATE)
            .pk(FastTemplateColumn.PK_FAST_TEMPLATE_ID)
            .text(FastTemplateColumn.TEMPLATE).last(FastTemplateColumn.EVENT_TYPE,sql.INTEGER).sql();
    public static final String CREATE_TABLE_DEFAULT_INPUT_VALUE = sql.create(Table.DEFAULT_INPUT_VALUE)
            .pk(DefaultInputValueColumn.PK_DEFAULT_INPUT_VALUE_ID)
            .integer(DefaultInputValueColumn.MAX_WIDTH)
            .integer(DefaultInputValueColumn.IS_GREEK_ALPHABET_MARKED).integer(DefaultInputValueColumn.IS_REMIND)
            .integer(DefaultInputValueColumn.REMIND_TIME).integer(DefaultInputValueColumn.STRATEGY)
            .last(DefaultInputValueColumn.IS_SHOW_EVENT_SEQUENCE,sql.INTEGER).sql();

    interface Table{
        String EVENT = "event";
        String LEARNING_EVENT_GROUP = "learning_event_group";
        String EVENT_GROUP = "event_group";
        String EVENT_SUBTYPE = "event_subtype";
        String FAST_TEMPLATE = "fast_template";
        String DEFAULT_INPUT_VALUE = "default_input_value";
    }
    interface EventColumn{
        String PK_EVENT_ID = "PK_EVENT_ID";
        String LEARNING_EVENT_GROUP_ID = "LEARNING_EVENT_GROUP_ID";
        String EVENT_GROUP_ID = "EVENT_GROUP_ID";
        String DESCRIPTION = "DESCRIPTION";
        String SUMMARY = "SUMMARY";
        String EVENT_TYPE = "EVENT_TYPE";   //1:学习型 2:普通型 3:模糊型
        String EVENT_SUBTYPE_ID = "EVENT_SUBTYPE_ID";
        String EVENT_SEQUENCE = "EVENT_SEQUENCE";
        String IS_SHOW_EVENT_SEQUENCE = "IS_SHOW_EVENT_SEQUENCE";   //1:true 2:false
        String CREATE_TIME = "CREATE_TIME"; //精确到秒
        String EVENT_EXPECTED_FINISHED_DATE = "EVENT_EXPECTED_FINISHED_DATE";   //精确到日
        String EVENT_FINISHED_TIME = "EVENT_FINISHED_TIME";
        String IS_EVENT_FINISHED = "IS_EVENT_FINISHED";
        String IS_GREEK_ALPHABET_MARKED = "IS_GREEK_ALPHABET_MARKED";
        String IS_REMIND = "IS_REMIND";
        String REMIND_TIME = "REMIND_TIME";
        String EVENT_PROCESS = "EVENT_PROCESS"; //1:未开始 2:进行中/待办 3:成功/完成 4:失败/过期
    }
    interface EventSubtypeColumn{
        String PK_EVENT_SUBTYPE_ID = "PK_EVENT_SUBTYPE_ID";
        String EVENT_SUBTYPE = "EVENT_SUBTYPE";
    }
    interface LearningEventGroupColumn{
        String PK_LEARNING_EVENT_GROUP_ID = "PK_LEARNING_EVENT_GROUP_ID";
        String KNOWLEDGE_QUANTITY = "KNOWLEDGE_QUANTITY";
        String STRATEGY = "STRATEGY";   //1:理解型 2:记忆型 3:强记型 4:永久型
        String LEARNING_EVENT_TOTAL = "LEARNING_EVENT_TOTAL";   //这个学习计划组的总数量
        String LEARNING_EVENT_FINISHED_COUNT = "LEARNING_EVENT_FINISHED_COUNT";
        String WORKLOAD = "WORKLOAD";   //工作量,单位:人分
        String EFFICIENCY = "EFFICIENCY";   //效率 0.3:差 0.6:一般 0.9:高效 1:非常高效
        String UNDERSTANDING_DEGREE = "UNDERSTANDING_DEGREE";   //理解情况 0.3:不太理解 0.7:大致理解 1:完全理解
    }
    interface EventGroupColumn{
        String PK_EVENT_GROUP_ID = "PK_EVENT_GROUP_ID";
        String CREATE_TIME = "CREATE_TIME";
        String DESCRIPTION = "DESCRIPTION";
        String LEARNING_EVENT_COUNT = "LEARNING_EVENT_COUNT";
        String NORMAL_OR_ABSTRACT_EVENT_COUNT = "NORMAL_OR_ABSTRACT_EVENT_COUNT";
    }
    interface FastTemplateColumn{
        String PK_FAST_TEMPLATE_ID = "PK_FAST_TEMPLATE_ID";
        String TEMPLATE = "TEMPLATE";
        String EVENT_TYPE = "EVENT_TYPE";
    }
    interface DefaultInputValueColumn{
        String PK_DEFAULT_INPUT_VALUE_ID = "PK_DEFAULT_INPUT_VALUE_ID";
        String MAX_WIDTH = "MAX_WIDTH";
        String IS_GREEK_ALPHABET_MARKED = "IS_GREEK_ALPHABET_MARKED";
        String IS_REMIND = "IS_REMIND";
        String REMIND_TIME = "REMIND_TIME";
        String STRATEGY = "STRATEGY";   //1:理解型 2:记忆型 3:强记型 4:永久型
        String IS_SHOW_EVENT_SEQUENCE = "IS_SHOW_EVENT_SEQUENCE";
    }


}
