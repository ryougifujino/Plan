package link.ebbinghaus.planning.model.entity.po;

import android.content.ContentValues;
import android.database.Cursor;

import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.Utils;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.custom.constant.entity.EventConstant;

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
                return EventConstant.STRATEGY_COMPREHENSIVE;
            case 2:
                return EventConstant.STRATEGY_MEMORIAL;
            case 3:
                return EventConstant.STRATEGY_MEMORIAL_PRO;
            case 4:
                return EventConstant.STRATEGY_PERSISTENT;
        }
        return "";
    }

    public String getFormatRemindTime(){
        return DateUtils.formatTimestamp2HourMinute(remindTime);
    }


    public void convertToContentValues(ContentValues values){
        values.put(DBConfig.DefaultInputValueColumn.MAX_WIDTH, maxWidth);
        values.put(DBConfig.DefaultInputValueColumn.IS_GREEK_ALPHABET_MARKED, isGreekAlphabetMarked);
        values.put(DBConfig.DefaultInputValueColumn.IS_REMIND, isRemind);
        values.put(DBConfig.DefaultInputValueColumn.REMIND_TIME, remindTime);
        values.put(DBConfig.DefaultInputValueColumn.STRATEGY, strategy);
        values.put(DBConfig.DefaultInputValueColumn.IS_SHOW_EVENT_SEQUENCE, isShowEventSequence);
    }

    public void filledByCursor(Cursor cursor){
        setPkDefaultInputValueId(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.PK_DEFAULT_INPUT_VALUE_ID)));
        setMaxWidth(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.MAX_WIDTH)));
        setIsGreekAlphabetMarked(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.IS_GREEK_ALPHABET_MARKED)));
        setIsRemind(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.IS_REMIND)));
        setRemindTime(cursor.getLong(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.REMIND_TIME)));
        setStrategy(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.STRATEGY)));
        setIsShowEventSequence(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.IS_SHOW_EVENT_SEQUENCE)));
    }
}
