package link.ebbinghaus.planning.model.entity.po;

import android.content.ContentValues;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class DefaultInputValue {
    private Integer pkDefaultInputValueId;
    private Integer maxWidth;   //预设值:5
    private boolean isGreekAlphabetMarked;  //预设值: false
    private boolean isRemind;   //预设值: false
    private long remindTime;    //预设值: 19:00
    private Integer strategy;   //1:理解型 2:记忆型 3:强记型 4:永久型   //预设值: 1
    private boolean isShowEventSequence;    //预设值: false

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

    public void convertToContentValues(ContentValues values){
        values.put(DBConfig.DefaultInputValueColumn.MAX_WIDTH, maxWidth);
        values.put(DBConfig.DefaultInputValueColumn.IS_GREEK_ALPHABET_MARKED, isGreekAlphabetMarked);
        values.put(DBConfig.DefaultInputValueColumn.IS_REMIND, isRemind);
        values.put(DBConfig.DefaultInputValueColumn.REMIND_TIME, remindTime);
        values.put(DBConfig.DefaultInputValueColumn.STRATEGY, strategy);
        values.put(DBConfig.DefaultInputValueColumn.IS_SHOW_EVENT_SEQUENCE, isShowEventSequence);
    }
}
