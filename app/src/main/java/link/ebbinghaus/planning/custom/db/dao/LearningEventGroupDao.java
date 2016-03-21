package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class LearningEventGroupDao extends BaseDao<LearningEventGroup> {

    public LearningEventGroupDao() {
        super(DBConfig.LearningEventGroupColumn.PK_LEARNING_EVENT_GROUP_ID);
    }

    @Override
    protected void _insert(LearningEventGroup learningEventGroup) {
        ContentValues values = new ContentValues();
        learningEventGroup.convertToContentValues(values);
        db.insert(DBConfig.Table.LEARNING_EVENT_GROUP, null, values);
    }

    @Override
    protected void _delete(String where, String[] args) {
        db.delete(DBConfig.Table.LEARNING_EVENT_GROUP, where, args);
    }

    @Override
    protected void _update(LearningEventGroup learningEventGroup, String where, String[] args) {
        ContentValues values = new ContentValues();
        learningEventGroup.convertToContentValues(values);
        db.update(DBConfig.Table.LEARNING_EVENT_GROUP, values, where, args);
    }

    @Override
    public void updateByPrimaryKey(LearningEventGroup learningEventGroup) {
        String whereClause = pkColumn + " = ?";
        _update(learningEventGroup, whereClause, new String[]{learningEventGroup.getPkLearningEventGroupId().toString()});
    }

    @Override
    public LearningEventGroup selectByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.LEARNING_EVENT_GROUP + " WHERE " +
                pkColumn + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        LearningEventGroup learningEventGroup = new LearningEventGroup();
        learningEventGroup.filledByCursor(cursor);
        cursor.close();
        return learningEventGroup;
    }

    @Override
    public List<LearningEventGroup> selectAll() {
        String querySql = "SELECT * FROM " + DBConfig.Table.LEARNING_EVENT_GROUP;
        Cursor cursor = db.rawQuery(querySql, null);
        List<LearningEventGroup> learningEventGroups = new ArrayList<>();
        while (cursor.moveToNext()){
            LearningEventGroup learningEventGroup = new LearningEventGroup();
            learningEventGroup.filledByCursor(cursor);
            learningEventGroups.add(learningEventGroup);
        }
        cursor.close();
        return learningEventGroups;
    }

    /* 以下方法为非通用方法 */


}
