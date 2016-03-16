package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class FastTemplateDao extends BaseDao<FastTemplate> {
    @Override
    protected void insert(FastTemplate fastTemplate) {
        ContentValues values = new ContentValues();
        fastTemplate.convertToContentValues(values);
        db.insert(DBConfig.Table.FAST_TEMPLATE, null, values);
    }

    @Override
    protected void delete(String where, String[] args) {
        db.delete(DBConfig.Table.FAST_TEMPLATE, where, args);
    }

    @Override
    protected void update(FastTemplate fastTemplate, String where, String[] args) {
        ContentValues values = new ContentValues();
        fastTemplate.convertToContentValues(values);
        db.update(DBConfig.Table.FAST_TEMPLATE, values, where, args);
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        String whereClause = DBConfig.FastTemplateColumn.PK_FAST_TEMPLATE_ID + " = ?";
        remove(whereClause, new String[]{pk.toString()});
    }

    @Override
    public void modifyByPrimaryKey(FastTemplate fastTemplate) {
        String whereClause = DBConfig.FastTemplateColumn.PK_FAST_TEMPLATE_ID + " = ?";
        modify(fastTemplate, whereClause, new String[]{fastTemplate.getPkFastTemplateId().toString()});
    }

    @Override
    public FastTemplate findByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.FAST_TEMPLATE + " WHERE " +
                DBConfig.FastTemplateColumn.PK_FAST_TEMPLATE_ID + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        FastTemplate fastTemplate = new FastTemplate();
        fastTemplate.setPkFastTemplateId(cursor.getInt(cursor.getColumnIndex(DBConfig.FastTemplateColumn.PK_FAST_TEMPLATE_ID)));
        fastTemplate.setTemplate(cursor.getString(cursor.getColumnIndex(DBConfig.FastTemplateColumn.TEMPLATE)));
        fastTemplate.setEventType(cursor.getInt(cursor.getColumnIndex(DBConfig.FastTemplateColumn.EVENT_TYPE)));
        cursor.close();
        return fastTemplate;
    }
}
