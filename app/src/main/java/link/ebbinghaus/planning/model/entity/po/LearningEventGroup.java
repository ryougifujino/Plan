package link.ebbinghaus.planning.model.entity.po;

import android.content.ContentValues;
import android.database.Cursor;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class LearningEventGroup {
    private Long pkLearningEventGroupId;
    private Integer knowledgeQuantity;
    private Integer strategy;   //1:理解型 2:记忆型 3:强记型 4:永久型
    private Integer learningEventTotal;   //这个学习计划组的总数量
    private Integer learningEventFinishedCount;
    private Integer workload;   //工作量,单位:人分
    private Float efficiency;   //效率 0.3:差 0.6:一般 0.9:高效 1:非常高效
    private Float understandingDegree;   //理解情况 0.3:不太理解 0.7:大致理解 1:完全理解

    public Long getPkLearningEventGroupId() {
        return pkLearningEventGroupId;
    }

    public void setPkLearningEventGroupId(Long pkLearningEventGroupId) {
        this.pkLearningEventGroupId = pkLearningEventGroupId;
    }

    public Integer getKnowledgeQuantity() {
        return knowledgeQuantity;
    }

    public void setKnowledgeQuantity(Integer knowledgeQuantity) {
        this.knowledgeQuantity = knowledgeQuantity;
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }

    public Integer getLearningEventTotal() {
        return learningEventTotal;
    }

    public void setLearningEventTotal(Integer learningEventTotal) {
        this.learningEventTotal = learningEventTotal;
    }

    public Integer getLearningEventFinishedCount() {
        return learningEventFinishedCount;
    }

    public void setLearningEventFinishedCount(Integer learningEventFinishedCount) {
        this.learningEventFinishedCount = learningEventFinishedCount;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Float efficiency) {
        this.efficiency = efficiency;
    }

    public Float getUnderstandingDegree() {
        return understandingDegree;
    }

    public void setUnderstandingDegree(Float understandingDegree) {
        this.understandingDegree = understandingDegree;
    }

    public void convertToContentValues(ContentValues values){
        values.put(DBConfig.LearningEventGroupColumn.PK_LEARNING_EVENT_GROUP_ID, pkLearningEventGroupId);
        values.put(DBConfig.LearningEventGroupColumn.KNOWLEDGE_QUANTITY, knowledgeQuantity);
        values.put(DBConfig.LearningEventGroupColumn.STRATEGY, strategy);
        values.put(DBConfig.LearningEventGroupColumn.LEARNING_EVENT_TOTAL, learningEventTotal);
        values.put(DBConfig.LearningEventGroupColumn.LEARNING_EVENT_FINISHED_COUNT, learningEventFinishedCount);
        values.put(DBConfig.LearningEventGroupColumn.WORKLOAD, workload);
        values.put(DBConfig.LearningEventGroupColumn.EFFICIENCY, efficiency);
        values.put(DBConfig.LearningEventGroupColumn.UNDERSTANDING_DEGREE, understandingDegree);
    }
    
    public void filledByCursor(Cursor cursor){
        setPkLearningEventGroupId(cursor.getLong(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.PK_LEARNING_EVENT_GROUP_ID)));
        setKnowledgeQuantity(cursor.getInt(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.KNOWLEDGE_QUANTITY)));
        setStrategy(cursor.getInt(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.STRATEGY)));
        setLearningEventTotal(cursor.getInt(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.LEARNING_EVENT_TOTAL)));
        setLearningEventFinishedCount(cursor.getInt(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.LEARNING_EVENT_FINISHED_COUNT)));
        setWorkload(cursor.getInt(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.WORKLOAD)));
        setEfficiency(cursor.getFloat(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.EFFICIENCY)));
        setUnderstandingDegree(cursor.getFloat(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.UNDERSTANDING_DEGREE)));
    }

    public void copyFrom(LearningEventGroup learningEventGroup){
        setPkLearningEventGroupId(learningEventGroup.getPkLearningEventGroupId());
        setKnowledgeQuantity(learningEventGroup.getKnowledgeQuantity());
        setStrategy(learningEventGroup.getStrategy());
        setLearningEventTotal(learningEventGroup.getLearningEventTotal());
        setLearningEventFinishedCount(learningEventGroup.getLearningEventFinishedCount());
        setWorkload(learningEventGroup.getWorkload());
        setEfficiency(learningEventGroup.getEfficiency());
        setUnderstandingDegree(learningEventGroup.getUnderstandingDegree());
    }
}
