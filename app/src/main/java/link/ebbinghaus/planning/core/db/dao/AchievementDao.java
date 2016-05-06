package link.ebbinghaus.planning.core.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.app.constant.config.DBConfig;
import link.ebbinghaus.planning.core.model.local.po.Achievement;

/**
 * Created by WINFIELD on 2016/4/1.
 */
public class AchievementDao extends BaseDao<Achievement> implements DBConfig.AchievementColumn{

    public AchievementDao() {
        super(PK_ACHIEVEMENT_ID, DBConfig.Table.ACHIEVEMENT);
    }

    @Override
    protected List<Achievement> _select(String querySql, String[] selectionArgs) {
        Cursor cursor = db.rawQuery(querySql, selectionArgs);
        List<Achievement> achievements = new ArrayList<>();
        while (cursor.moveToNext()){
            Achievement achievement = new Achievement();
            achievement.filledByCursor(cursor);
            achievements.add(achievement);
        }
        cursor.close();
        return achievements;
    }

    @Override
    protected long _insert(Achievement achievement) {
        ContentValues values = new ContentValues();
        achievement.convertToContentValues(values);
        return db.insert(mTableName, null, values);
    }

    @Override
    protected int _update(Achievement achievement, String where, String[] args) {
        ContentValues values = new ContentValues();
        achievement.convertToContentValues(values);
        return db.update(mTableName, values, where, args);
    }

    @Override
    public long insert(Achievement achievement) {
        achievement.setPkAchievementId(_insert(achievement));
        return achievement.getPkAchievementId();
    }

    @Override
    public void updateByPrimaryKey(Achievement achievement) {
        String whereClause = mPkColumn + " = ?";
        _update(achievement, whereClause, new String[]{achievement.getPkAchievementId().toString()});
    }

    @Override
    public void insertSome(List<Achievement> achievements) {
        for (Achievement achievement : achievements) {
            achievement.setPkAchievementId(_insert(achievement));
        }
    }

    /* 以下方法为非通用方法 */

    /**
     * 预设成就模块<br>
     */
    public static void presetAchievement(SQLiteDatabase db){
        String[] names = {"略有成就","志士","学者"};
        String[] descriptions = {"成功完成100组学习计划","成功完成1000组学习计划","成功完成5000组学习计划"};
        String[] imageUrls = {"finish_learning_event_100.png","finish_learning_event_1000.png","finish_learning_event_5000.png"};
        Integer[] rarities = {1,2,3};

        db.beginTransaction();
        try {
            int index = 0;
            for (String name : names) {
                Achievement achievement = new Achievement();
                achievement.setName(name);
                achievement.setDescription(descriptions[index]);
                achievement.setImageUrl(imageUrls[index]);
                achievement.setRarity(rarities[index]);

                ContentValues values = new ContentValues();
                achievement.convertToContentValues(values);
                db.insert(DBConfig.Table.ACHIEVEMENT,null,values);
                index++;
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }

    }
}
