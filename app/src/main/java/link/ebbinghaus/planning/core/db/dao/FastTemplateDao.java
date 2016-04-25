package link.ebbinghaus.planning.core.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.common.constant.config.DBConfig;
import link.ebbinghaus.planning.common.constant.config.entity.FastTemplateConfig;
import link.ebbinghaus.planning.core.model.local.po.FastTemplate;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class FastTemplateDao extends BaseDao<FastTemplate> implements DBConfig.FastTemplateColumn{

    public FastTemplateDao() {
        super(PK_FAST_TEMPLATE_ID, DBConfig.Table.FAST_TEMPLATE);
    }

    @Override
    protected List<FastTemplate> _select(String querySql, String[] selectionArgs) {
        Cursor cursor = db.rawQuery(querySql, selectionArgs);
        List<FastTemplate> fastTemplates = new ArrayList<>();
        while (cursor.moveToNext()){
            FastTemplate fastTemplate = new FastTemplate();
            fastTemplate.filledByCursor(cursor);
            fastTemplates.add(fastTemplate);
        }
        cursor.close();
        return fastTemplates;
    }

    @Override
    protected long _insert(FastTemplate fastTemplate) {
        ContentValues values = new ContentValues();
        fastTemplate.convertToContentValues(values);
        return db.insert(mTableName, null, values);
    }

    @Override
    protected int _update(FastTemplate fastTemplate, String where, String[] args) {
        ContentValues values = new ContentValues();
        fastTemplate.convertToContentValues(values);
        return db.update(mTableName, values, where, args);
    }

    @Override
    public long insert(FastTemplate fastTemplate) {
        fastTemplate.setPkFastTemplateId(_insert(fastTemplate));
        return fastTemplate.getPkFastTemplateId();
    }

    @Override
    public void updateByPrimaryKey(FastTemplate fastTemplate) {
        String whereClause = mPkColumn + " = ?";
        _update(fastTemplate, whereClause, new String[]{fastTemplate.getPkFastTemplateId().toString()});
    }

    @Override
    public void insertSome(List<FastTemplate> fastTemplates) {
        for (FastTemplate fastTemplate : fastTemplates) {
            fastTemplate.setPkFastTemplateId(_insert(fastTemplate));
        }
    }


    /* 以下方法为非通用方法 */

    public final List<FastTemplate> selectAllByEventType(int eventType){
        String querySql = "SELECT * FROM " + mTableName
                + " WHERE " + EVENT_TYPE + " = " + eventType;
        return _select(querySql);
    }

    public List<FastTemplate> selectSpecLearningAll() {
        return selectAllByEventType(FastTemplateConfig.TYPE_SPEC_LEARNING);
    }

    public List<FastTemplate> selectSpecNormalAll() {
        return selectAllByEventType(FastTemplateConfig.TYPE_SPEC_NORMAL);
    }

    public List<FastTemplate> selectAbstractAll() {
        return selectAllByEventType(FastTemplateConfig.TYPE_ABSTRACT);
    }
}
