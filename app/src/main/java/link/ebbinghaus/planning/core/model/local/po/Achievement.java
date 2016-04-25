package link.ebbinghaus.planning.core.model.local.po;

import android.content.ContentValues;
import android.database.Cursor;

import com.yurikami.lib.util.NonNullContentValues;
import com.yurikami.lib.util.Utils;

import link.ebbinghaus.planning.common.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/4/1.
 */
public class Achievement {
    private Long pkAchievementId;
    private String name;
    private String description;
    private String imageUrl;
    private Integer rarity;     //1:罕见 2:传奇 3:不朽
    private Boolean isAchieved;

    public Long getPkAchievementId() {
        return pkAchievementId;
    }

    public void setPkAchievementId(Long pkAchievementId) {
        this.pkAchievementId = pkAchievementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public Boolean getIsAchieved() {
        return isAchieved;
    }

    public void setIsAchieved(Boolean isAchieved) {
        this.isAchieved = isAchieved;
    }
    
    /* --- */

    public void setIsAchieved(Integer isAchieved){
        setIsAchieved(Utils.int2Bool(isAchieved));
    }

    public void convertToContentValues(ContentValues values){
        NonNullContentValues nonNullValues = new NonNullContentValues(values);
        nonNullValues.put(DBConfig.AchievementColumn.PK_ACHIEVEMENT_ID, pkAchievementId);
        nonNullValues.put(DBConfig.AchievementColumn.NAME, name);
        nonNullValues.put(DBConfig.AchievementColumn.DESCRIPTION, description);
        nonNullValues.put(DBConfig.AchievementColumn.IMAGE_URL, imageUrl);
        nonNullValues.put(DBConfig.AchievementColumn.RARITY, rarity);
        nonNullValues.put(DBConfig.AchievementColumn.IS_ACHIEVED, isAchieved);
    }

    public void filledByCursor(Cursor cursor){
        setPkAchievementId(cursor.getLong(cursor.getColumnIndex(DBConfig.AchievementColumn.PK_ACHIEVEMENT_ID)));
        setName(cursor.getString(cursor.getColumnIndex(DBConfig.AchievementColumn.NAME)));
        setDescription(cursor.getString(cursor.getColumnIndex(DBConfig.AchievementColumn.DESCRIPTION)));
        setImageUrl(cursor.getString(cursor.getColumnIndex(DBConfig.AchievementColumn.IMAGE_URL)));
        setRarity(cursor.getInt(cursor.getColumnIndex(DBConfig.AchievementColumn.RARITY)));
        setIsAchieved(cursor.getInt(cursor.getColumnIndex(DBConfig.AchievementColumn.IS_ACHIEVED)));
    }

    public void copyFrom(Achievement achievement){
        setPkAchievementId(achievement.getPkAchievementId());
        setName(achievement.getName());
        setDescription(achievement.getDescription());
        setImageUrl(achievement.getImageUrl());
        setRarity(achievement.getRarity());
        setIsAchieved(achievement.getIsAchieved());
    }
}
