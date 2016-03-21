package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class FastTemplateDao extends BaseDao<FastTemplate> {

    public FastTemplateDao() {
        super(DBConfig.FastTemplateColumn.PK_FAST_TEMPLATE_ID);
    }

    @Override
    protected void _insert(FastTemplate fastTemplate) {
        ContentValues values = new ContentValues();
        fastTemplate.convertToContentValues(values);
        db.insert(DBConfig.Table.FAST_TEMPLATE, null, values);
    }

    @Override
    protected void _delete(String where, String[] args) {
        db.delete(DBConfig.Table.FAST_TEMPLATE, where, args);
    }

    @Override
    protected void _update(FastTemplate fastTemplate, String where, String[] args) {
        ContentValues values = new ContentValues();
        fastTemplate.convertToContentValues(values);
        db.update(DBConfig.Table.FAST_TEMPLATE, values, where, args);
    }

    @Override
    public void updateByPrimaryKey(FastTemplate fastTemplate) {
        String whereClause = pkColumn + " = ?";
        _update(fastTemplate, whereClause, new String[]{fastTemplate.getPkFastTemplateId().toString()});
    }

    @Override
    public FastTemplate selectByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.FAST_TEMPLATE + " WHERE " +
                pkColumn + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        FastTemplate fastTemplate = new FastTemplate();
        fastTemplate.filledByCursor(cursor);
        cursor.close();
        return fastTemplate;
    }

    @Override
    public List<FastTemplate> selectAll() {
        return selectAll("");
    }

    /* 以下方法为非通用方法 */

    private List<FastTemplate> selectAll(String whereClause){
        String querySql = "SELECT * FROM " + DBConfig.Table.FAST_TEMPLATE + whereClause;
        Cursor cursor = db.rawQuery(querySql, null);
        List<FastTemplate> fastTemplates = new ArrayList<>();
        while (cursor.moveToNext()){
            FastTemplate fastTemplate = new FastTemplate();
            fastTemplate.filledByCursor(cursor);
            fastTemplates.add(fastTemplate);
        }
        cursor.close();
        return fastTemplates;
    }

    public List<FastTemplate> selectSpecLearningAll() {
        return selectAll(" WHERE " + DBConfig.FastTemplateColumn.EVENT_TYPE + " = 1");
    }

    public List<FastTemplate> selectSpecNormalAll() {
        return selectAll(" WHERE " + DBConfig.FastTemplateColumn.EVENT_TYPE + " = 2");
    }

    public List<FastTemplate> selectAbstractAll() {
        return selectAll(" WHERE " + DBConfig.FastTemplateColumn.EVENT_TYPE + " = 3");
    }
}
