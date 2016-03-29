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
        super(DBConfig.LearningEventGroupColumn.PK_LEARNING_EVENT_GROUP_ID, DBConfig.Table.LEARNING_EVENT_GROUP);
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


}
