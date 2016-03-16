package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.yurikami.lib.util.DateUtils;
import com.yurikami.lib.util.Utils;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.DefaultInputValue;

/**
 * !此类的增方法不能调用(虽然实现了)
 */
public class DefaultInputValueDao extends BaseDao<DefaultInputValue> {

    @Override
    protected void insert(DefaultInputValue defaultInputValue) {
        ContentValues values = new ContentValues();
        defaultInputValue.convertToContentValues(values);
        db.insert(DBConfig.Table.DEFAULT_INPUT_VALUE, null, values);
    }

    @Override
    protected void delete(String where, String[] args) {
        db.delete(DBConfig.Table.DEFAULT_INPUT_VALUE, where, args);
    }

    @Override
    protected void update(DefaultInputValue defaultInputValue, String where, String[] args) {
        ContentValues values = new ContentValues();
        defaultInputValue.convertToContentValues(values);
        db.update(DBConfig.Table.DEFAULT_INPUT_VALUE, values, where, args);
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        String whereClause = DBConfig.DefaultInputValueColumn.PK_DEFAULT_INPUT_VALUE_ID + " = ?";
        remove(whereClause, new String[]{pk.toString()});
    }

    @Override
    public void modifyByPrimaryKey(DefaultInputValue defaultInputValue) {
        String whereClause = DBConfig.DefaultInputValueColumn.PK_DEFAULT_INPUT_VALUE_ID + " = ?";
        modify(defaultInputValue, whereClause, new String[]{defaultInputValue.getPkDefaultInputValueId().toString()});
    }

    @Override
    public DefaultInputValue findByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.DEFAULT_INPUT_VALUE + " WHERE " +
                DBConfig.DefaultInputValueColumn.PK_DEFAULT_INPUT_VALUE_ID + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        DefaultInputValue defaultInputValue = new DefaultInputValue();
        defaultInputValue.setPkDefaultInputValueId(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.PK_DEFAULT_INPUT_VALUE_ID)));
        defaultInputValue.setMaxWidth(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.MAX_WIDTH)));
        defaultInputValue.setIsGreekAlphabetMarked(Utils.int2Bool(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.IS_GREEK_ALPHABET_MARKED))));
        defaultInputValue.setIsRemind(Utils.int2Bool(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.IS_REMIND))));
        defaultInputValue.setRemindTime(cursor.getLong(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.REMIND_TIME)));
        defaultInputValue.setStrategy(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.STRATEGY)));
        defaultInputValue.setIsShowEventSequence(Utils.int2Bool(cursor.getInt(cursor.getColumnIndex(DBConfig.DefaultInputValueColumn.IS_SHOW_EVENT_SEQUENCE))));
        cursor.close();
        return defaultInputValue;
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
}
