package link.ebbinghaus.planning.core.model.local.po;

import android.content.ContentValues;
import android.database.Cursor;

import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.NonNullContentValues;
import com.yurikami.lib.util.Utils;

import link.ebbinghaus.planning.common.constant.config.DBConfig;
import link.ebbinghaus.planning.common.constant.config.entity.LearningEventGroupConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class DefaultInputValue {
    private Long pkDefaultInputValueId;
    private Integer maxWidth;   //预设值:5
    private Boolean isGreekAlphabetMarked;  //预设值: false
    private Boolean isRemind;   //预设值: false
    private Long remindTime;    //预设值: 19:00
    private Integer strategy;   //1:理解型 2:记忆型 3:强记型 4:永久型   //预设值: 1
    private Boolean isShowEventSequence;    //预设值: false

    public Long getPkDefaultInputValueId() {
        return pkDefaultInputValueId;
    }

    public void setPkDefaultInputValueId(Long pkDefaultInputValueId) {
        this.pkDefaultInputValueId = pkDefaultInputValueId;
    }

    public Integer getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(Integer maxWidth) {
        this.maxWidth = maxWidth;
    }

    public Boolean getIsGreekAlphabetMarked() {
        return isGreekAlphabetMarked;
    }

    public void setIsGreekAlphabetMarked(Boolean isGreekAlphabetMarked) {
        this.isGreekAlphabetMarked = isGreekAlphabetMarked;
    }

    public Boolean getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(Boolean isRemind) {
        this.isRemind = isRemind;
    }

    public Long getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Long remindTime) {
        this.remindTime = remindTime;
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }

    public Boolean getIsShowEventSequence() {
        return isShowEventSequence;
    }

    public void setIsShowEventSequence(Boolean isShowEventSequence) {
        this.isShowEventSequence = isShowEventSequence;
    }

    /* --- */

    public void setIsGreekAlphabetMarked(Integer isGreekAlphabetMarked){
        setIsGreekAlphabetMarked(Utils.int2Bool(isGreekAlphabetMarked));
    }
    public void setIsRemind(Integer isRemind) {
        setIsRemind(Utils.int2Bool(isRemind));
    }
    public void setIsShowEventSequence(Integer isShowEventSequence){
        setIsShowEventSequence(Utils.int2Bool(isShowEventSequence));
    }

    public String getChnStrategy(){
        switch (strategy){
            case 1:
                return LearningEventGroupConfig.COMPREHENSIVE;
            case 2:
                return LearningEventGroupConfig.MEMORIAL;
            case 3:
                return LearningEventGroupConfig.MEMORIAL_PRO;
            case 4:
                return LearningEventGroupConfig.PERSISTENT;
        }
        return "";
    }

    public String getFormatRemindTime(){
        return DateUtils.formatTimestamp2HourMinute(remindTime);
    }


    public void convertToContentValues(ContentValues values){
        NonNullContentValues nonNullValues = new NonNullContentValues(values);
        nonNullValues.put(DBConfig.DefaultInputValueColumn.MAX_WIDTH, maxWidth);
        nonNullValues.put(DBConfig.DefaultInputValueColumn.IS_GREEK_ALPHABET_MARKED, isGreekAlphabetMarked);
        nonNullValues.put(DBConfig.DefaultInputValueColumn.IS_REMIND, isRemind);
        nonNullValues.put(DBConfig.DefaultInputValueColumn.REMIND_TIME, remindTime);
        nonNullValues.put(DBConfig.DefaultInputValueColumn.STRATEGY, strategy);
        nonNullValues.put(DBConfig.DefaultInputValueColumn.IS_SHOW_EVENT_SEQUENCE, isShowEventSequence);
    }

    public void filledByCursor(Cursor cursor){
        setPkDefaultInputValueId(cursor.getLong(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.PK_DEFAULT_INPUT_VALUE_ID)));
        setMaxWidth(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.MAX_WIDTH)));
        setIsGreekAlphabetMarked(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.IS_GREEK_ALPHABET_MARKED)));
        setIsRemind(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.IS_REMIND)));
        setRemindTime(cursor.getLong(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.REMIND_TIME)));
        setStrategy(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.STRATEGY)));
        setIsShowEventSequence(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.IS_SHOW_EVENT_SEQUENCE)));
    }

    public void copyFrom(DefaultInputValue defaultInputValue){
        setPkDefaultInputValueId(defaultInputValue.getPkDefaultInputValueId());
        setMaxWidth(defaultInputValue.getMaxWidth());
        setIsGreekAlphabetMarked(defaultInputValue.getIsGreekAlphabetMarked());
        setIsRemind(defaultInputValue.getIsRemind());
        setRemindTime(defaultInputValue.getRemindTime());
        setStrategy(defaultInputValue.getStrategy());
        setIsShowEventSequence(defaultInputValue.getIsShowEventSequence());
    }
}
