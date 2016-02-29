package link.ebbinhaus.planning.model.entity;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class LearningEventGroup {
    private Integer pkLearningEventGroupId;
    private Integer knowledgeQuantity;
    private Integer strategy;   //1:理解型 2:记忆型 3:强记型 4:永久型
    private Integer learningEventTotal;   //这个学习计划组的总数量
    private Integer learningEventFinishedCount;
    private Integer workload;   //工作量,单位:人分
    private float efficiency;   //效率 0.3:差 0.6:一般 0.9:高效 1:非常高效
    private float understandingDegree;   //理解情况 0.3:不太理解 0.7:大致理解 1:完全理解

    public Integer getPkLearningEventGroupId() {
        return pkLearningEventGroupId;
    }

    public void setPkLearningEventGroupId(Integer pkLearningEventGroupId) {
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

    public float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(float efficiency) {
        this.efficiency = efficiency;
    }

    public float getUnderstandingDegree() {
        return understandingDegree;
    }

    public void setUnderstandingDegree(float understandingDegree) {
        this.understandingDegree = understandingDegree;
    }
}
