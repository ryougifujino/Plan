package link.ebbinhaus.planning.model.entity;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class DefaultInputValue {
    private Integer pkDefaultInputValueId;
    private Integer maxWidth;
    private boolean isGreekAlphabetMarked;
    private boolean isRemind;
    private long remindTime;
    private Integer strategy;   //1:理解型 2:记忆型 3:强记型 4:永久型
    private boolean isShowEventSequence;

    public Integer getPkDefaultInputValueId() {
        return pkDefaultInputValueId;
    }

    public void setPkDefaultInputValueId(Integer pkDefaultInputValueId) {
        this.pkDefaultInputValueId = pkDefaultInputValueId;
    }

    public Integer getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(Integer maxWidth) {
        this.maxWidth = maxWidth;
    }

    public boolean isGreekAlphabetMarked() {
        return isGreekAlphabetMarked;
    }

    public void setIsGreekAlphabetMarked(boolean isGreekAlphabetMarked) {
        this.isGreekAlphabetMarked = isGreekAlphabetMarked;
    }

    public boolean isRemind() {
        return isRemind;
    }

    public void setIsRemind(boolean isRemind) {
        this.isRemind = isRemind;
    }

    public long getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(long remindTime) {
        this.remindTime = remindTime;
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }

    public boolean isShowEventSequence() {
        return isShowEventSequence;
    }

    public void setIsShowEventSequence(boolean isShowEventSequence) {
        this.isShowEventSequence = isShowEventSequence;
    }
}
