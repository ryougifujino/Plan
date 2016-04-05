package link.ebbinghaus.planning.core.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yurikami.lib.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.common.constant.config.DBConfig;
import link.ebbinghaus.planning.core.model.po.DefaultInputValue;

/**
 * !此类的增方法不能调用(虽然实现了)
 * 因为按照业务逻辑,默认输入值只应该有一条记录
 */
public class DefaultInputValueDao extends BaseDao<DefaultInputValue> implements DBConfig.DefaultInputValueColumn{

    public DefaultInputValueDao() {
        super(PK_DEFAULT_INPUT_VALUE_ID, DBConfig.Table.DEFAULT_INPUT_VALUE);
    }

    @Override
    protected List<DefaultInputValue> _select(String querySql, String[] selectionArgs) {
        Cursor cursor = db.rawQuery(querySql, selectionArgs);
        List<DefaultInputValue> defaultInputValues = new ArrayList<>();
        while (cursor.moveToNext()){
            DefaultInputValue defaultInputValue = new DefaultInputValue();
            defaultInputValue.filledByCursor(cursor);
            defaultInputValues.add(defaultInputValue);
        }
        cursor.close();
        return defaultInputValues;
    }

    @Override
    protected long _insert(DefaultInputValue defaultInputValue) {
        ContentValues values = new ContentValues();
        defaultInputValue.convertToContentValues(values);
        return db.insert(mTableName, null, values);
    }

    @Override
    protected int _update(DefaultInputValue defaultInputValue, String where, String[] args) {
        ContentValues values = new ContentValues();
        defaultInputValue.convertToContentValues(values);
        return db.update(mTableName, values, where, args);
    }

    @Override
    public long insert(DefaultInputValue defaultInputValue) {
        defaultInputValue.setPkDefaultInputValueId(_insert(defaultInputValue));
        return defaultInputValue.getPkDefaultInputValueId();
    }

    @Override
    public void updateByPrimaryKey(DefaultInputValue defaultInputValue) {
        String whereClause = mPkColumn + " = ?";
        _update(defaultInputValue, whereClause, new String[]{defaultInputValue.getPkDefaultInputValueId().toString()});
    }

    @Override
    public void insertSome(List<DefaultInputValue> defaultInputValues) {
        for (DefaultInputValue defaultInputValue : defaultInputValues) {
            defaultInputValue.setPkDefaultInputValueId(_insert(defaultInputValue));
        }
    }


    /**
     * !只可以再创建此表时可调用此方法
     * !这张表里面只能有一条数据
     * 此方法的作用是插入默认值
     */
    public static void presetDefaultInputValue(SQLiteDatabase db){
        DefaultInputValue defaultInputValue = new DefaultInputValue();
        defaultInputValue.setPkDefaultInputValueId(1L);
        defaultInputValue.setMaxWidth(5);
        defaultInputValue.setIsGreekAlphabetMarked(false);
        defaultInputValue.setIsRemind(false);
        defaultInputValue.setRemindTime(DateUtils.convertHourMinute2Timestamp("19:00"));
        defaultInputValue.setStrategy(1);
        defaultInputValue.setIsShowEventSequence(false);

        ContentValues values = new ContentValues();
        defaultInputValue.convertToContentValues(values);

        db.beginTransaction();
        try {
            db.insert(DBConfig.Table.DEFAULT_INPUT_VALUE, null, values);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }

    }

    /* 以下方法为非通用方法 */


}
