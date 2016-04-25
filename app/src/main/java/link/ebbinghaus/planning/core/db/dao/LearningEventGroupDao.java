package link.ebbinghaus.planning.core.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.common.constant.config.DBConfig;
import link.ebbinghaus.planning.core.model.local.po.LearningEventGroup;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class LearningEventGroupDao extends BaseDao<LearningEventGroup> implements DBConfig.LearningEventGroupColumn{


    public LearningEventGroupDao() {
        super(PK_LEARNING_EVENT_GROUP_ID, DBConfig.Table.LEARNING_EVENT_GROUP);
    }

    @Override
    protected List<LearningEventGroup> _select(String querySql, String[] selectionArgs) {
        Cursor cursor = db.rawQuery(querySql, selectionArgs);
        List<LearningEventGroup> learningEventGroups = new ArrayList<>();
        while (cursor.moveToNext()){
            LearningEventGroup learningEventGroup = new LearningEventGroup();
            learningEventGroup.filledByCursor(cursor);
            learningEventGroups.add(learningEventGroup);
        }
        cursor.close();
        return learningEventGroups;
    }

    @Override
    protected long _insert(LearningEventGroup learningEventGroup) {
        ContentValues values = new ContentValues();
        learningEventGroup.convertToContentValues(values);
        return db.insert(mTableName, null, values);
    }

    @Override
    protected int _update(LearningEventGroup learningEventGroup, String where, String[] args) {
        ContentValues values = new ContentValues();
        learningEventGroup.convertToContentValues(values);
        return db.update(mTableName, values, where, args);
    }

    @Override
    public long insert(LearningEventGroup learningEventGroup) {
        learningEventGroup.setPkLearningEventGroupId(_insert(learningEventGroup));
        return learningEventGroup.getPkLearningEventGroupId();
    }

    @Override
    public void updateByPrimaryKey(LearningEventGroup learningEventGroup) {
        String whereClause = mPkColumn + " = ?";
        _update(learningEventGroup, whereClause, new String[]{learningEventGroup.getPkLearningEventGroupId().toString()});
    }

    @Override
    public void insertSome(List<LearningEventGroup> learningEventGroups) {
        for (LearningEventGroup learningEventGroup : learningEventGroups) {
            learningEventGroup.setPkLearningEventGroupId(_insert(learningEventGroup));
        }
    }

    /* 以下方法为非通用方法 */

    /**
     * 在完成一个学习计划后更新相应的学习计划组
     * @param leg 更新用到的数据
     */
    public void updateLearningEventGroupAfterFinishing1Event(LearningEventGroup leg){
        String updateSql = "UPDATE "+ mTableName +" SET " +
                KNOWLEDGE_QUANTITY + " = ?, " +
                LEARNING_EVENT_FINISHED_COUNT + " = "+ LEARNING_EVENT_FINISHED_COUNT +" + 1," +
                LEARNING_DURATION + " = ?,"+ EFFICIENCY +" = ?,"+ UNDERSTANDING_DEGREE +" = ? " +
                "WHERE "+ mPkColumn +" = ?";
        db.execSQL(updateSql,
                new Object[]{leg.getKnowledgeQuantity(),leg.getLearningDuration(),
                        leg.getEfficiency(),leg.getUnderstandingDegree(),leg.getPkLearningEventGroupId()});
        
    }

}
