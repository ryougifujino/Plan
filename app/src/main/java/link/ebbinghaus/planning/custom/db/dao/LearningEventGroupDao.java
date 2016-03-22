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
    protected long _insert(LearningEventGroup learningEventGroup) {
        ContentValues values = new ContentValues();
        learningEventGroup.convertToContentValues(values);
        return db.insert(DBConfig.Table.LEARNING_EVENT_GROUP, null, values);
    }

    @Override
    protected int _delete(String where, String[] args) {
        return db.delete(DBConfig.Table.LEARNING_EVENT_GROUP, where, args);
    }

    @Override
    protected int _update(LearningEventGroup learningEventGroup, String where, String[] args) {
        ContentValues values = new ContentValues();
        learningEventGroup.convertToContentValues(values);
        return db.update(DBConfig.Table.LEARNING_EVENT_GROUP, values, where, args);
    }

    @Override
    public long insert(LearningEventGroup learningEventGroup) {
        learningEventGroup.setPkLearningEventGroupId(_insert(learningEventGroup));
        return learningEventGroup.getPkLearningEventGroupId();
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
    public void insertSome(List<LearningEventGroup> learningEventGroups) {
        for (LearningEventGroup learningEventGroup : learningEventGroups) {
            learningEventGroup.setPkLearningEventGroupId(_insert(learningEventGroup));
        }
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
