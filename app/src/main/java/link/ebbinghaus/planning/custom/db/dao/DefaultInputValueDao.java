package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.yurikami.lib.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.DefaultInputValue;

/**
 * !此类的增方法不能调用(虽然实现了)
 * 因为按照业务逻辑,默认输入值只应该有一条记录
 */
public class DefaultInputValueDao extends BaseDao<DefaultInputValue> {

    public DefaultInputValueDao() {
        super(DBConfig.DefaultInputValueColumn.PK_DEFAULT_INPUT_VALUE_ID);
    }

    @Override
    protected void _insert(DefaultInputValue defaultInputValue) {
        ContentValues values = new ContentValues();
        defaultInputValue.convertToContentValues(values);
        db.insert(DBConfig.Table.DEFAULT_INPUT_VALUE, null, values);
    }

    @Override
    protected void _delete(String where, String[] args) {
        db.delete(DBConfig.Table.DEFAULT_INPUT_VALUE, where, args);
    }

    @Override
    protected void _update(DefaultInputValue defaultInputValue, String where, String[] args) {
        ContentValues values = new ContentValues();
        defaultInputValue.convertToContentValues(values);
        db.update(DBConfig.Table.DEFAULT_INPUT_VALUE, values, where, args);
    }

    @Override
    public void updateByPrimaryKey(DefaultInputValue defaultInputValue) {
        String whereClause = pkColumn + " = ?";
        _update(defaultInputValue, whereClause, new String[]{defaultInputValue.getPkDefaultInputValueId().toString()});
    }

    @Override
    public DefaultInputValue selectByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.DEFAULT_INPUT_VALUE + " WHERE " +
                pkColumn + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        DefaultInputValue defaultInputValue = new DefaultInputValue();
        defaultInputValue.filledByCursor(cursor);
        cursor.close();
        return defaultInputValue;
    }

    @Override
    public List<DefaultInputValue> selectAll() {
        String querySql = "SELECT * FROM " + DBConfig.Table.DEFAULT_INPUT_VALUE;
        Cursor cursor = db.rawQuery(querySql, null);
        List<DefaultInputValue> defaultInputValues = new ArrayList<>();
        while (cursor.moveToNext()){
            DefaultInputValue defaultInputValue = new DefaultInputValue();
            defaultInputValue.filledByCursor(cursor);
            defaultInputValues.add(defaultInputValue);
        }
        cursor.close();
        return defaultInputValues;
    }

    /**
     * !只可以再创建此表时可调用此方法
     * !这张表里面只能有一条数据
     * 此方法的作用是获取一条预设的默认输入值方案
     */
    public static ContentValues presetDefaultInputValue(){
        DefaultInputValue defaultInputValue = new DefaultInputValue();
        defaultInputValue.setMaxWidth(5);
        defaultInputValue.setIsGreekAlphabetMarked(false);
        defaultInputValue.setIsRemind(false);
        defaultInputValue.setRemindTime(DateUtils.convertHourMinute2Timestamp("19:00"));
        defaultInputValue.setStrategy(1);
        defaultInputValue.setIsShowEventSequence(false);

        ContentValues values = new ContentValues();
        defaultInputValue.convertToContentValues(values);
        return values;
    }

    /* 以下方法为非通用方法 */


}
