package link.ebbinghaus.planning.custom.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.LearningEventGroup;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class LearningEventGroupDao extends BaseDao<LearningEventGroup> {

    @Override
    protected void insert(LearningEventGroup learningEventGroup) {
        ContentValues values = new ContentValues();
        learningEventGroup.convertToContentValues(values);
        db.insert(DBConfig.Table.LEARNING_EVENT_GROUP, null, values);
    }

    @Override
    protected void delete(String where, String[] args) {
        db.delete(DBConfig.Table.LEARNING_EVENT_GROUP, where, args);
    }

    @Override
    protected void update(LearningEventGroup learningEventGroup, String where, String[] args) {
        ContentValues values = new ContentValues();
        learningEventGroup.convertToContentValues(values);
        db.update(DBConfig.Table.LEARNING_EVENT_GROUP, values, where, args);
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        String whereClause = DBConfig.LearningEventGroupColumn.PK_LEARNING_EVENT_GROUP_ID + " = ?";
        remove(whereClause, new String[]{pk.toString()});
    }

    @Override
    public void modifyByPrimaryKey(LearningEventGroup learningEventGroup) {
        String whereClause = DBConfig.LearningEventGroupColumn.PK_LEARNING_EVENT_GROUP_ID + " = ?";
        modify(learningEventGroup, whereClause, new String[]{learningEventGroup.getPkLearningEventGroupId().toString()});
    }

    @Override
    public LearningEventGroup findByPrimaryKey(Integer pk) {
        String querySql = "SELECT * FROM " + DBConfig.Table.LEARNING_EVENT_GROUP + " WHERE " +
                DBConfig.LearningEventGroupColumn.PK_LEARNING_EVENT_GROUP_ID + " = ?";
        Cursor cursor = db.rawQuery(querySql, new String[]{pk.toString()});
        cursor.moveToFirst();
        LearningEventGroup learningEventGroup = new LearningEventGroup();
        learningEventGroup.setPkLearningEventGroupId(cursor.getInt(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.PK_LEARNING_EVENT_GROUP_ID)));
        learningEventGroup.setKnowledgeQuantity(cursor.getInt(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.KNOWLEDGE_QUANTITY)));
        learningEventGroup.setStrategy(cursor.getInt(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.STRATEGY)));
        learningEventGroup.setLearningEventTotal(cursor.getInt(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.LEARNING_EVENT_TOTAL)));
        learningEventGroup.setLearningEventFinishedCount(cursor.getInt(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.LEARNING_EVENT_FINISHED_COUNT)));
        learningEventGroup.setWorkload(cursor.getInt(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.WORKLOAD)));
        learningEventGroup.setEfficiency(cursor.getFloat(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.EFFICIENCY)));
        learningEventGroup.setUnderstandingDegree(cursor.getFloat(cursor.getColumnIndex(DBConfig.LearningEventGroupColumn.UNDERSTANDING_DEGREE)));
        cursor.close();
        return learningEventGroup;
    }
}
