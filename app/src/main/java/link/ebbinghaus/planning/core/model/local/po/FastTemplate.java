package link.ebbinghaus.planning.core.model.local.po;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.yurikami.lib.util.NonNullContentValues;

import link.ebbinghaus.planning.common.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class FastTemplate implements Parcelable{
    private Long pkFastTemplateId;
    private String template;
    private Integer eventType;  //1:学习型 2:普通型 3:模糊型

    public Long getPkFastTemplateId() {
        return pkFastTemplateId;
    }

    public void setPkFastTemplateId(Long pkFastTemplateId) {
        this.pkFastTemplateId = pkFastTemplateId;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    /** 辅助方法 */

    public void convertToContentValues(ContentValues values){
        NonNullContentValues nonNullValues = new NonNullContentValues(values);
        nonNullValues.put(DBConfig.FastTemplateColumn.PK_FAST_TEMPLATE_ID, pkFastTemplateId);
        nonNullValues.put(DBConfig.FastTemplateColumn.TEMPLATE, template);
        nonNullValues.put(DBConfig.FastTemplateColumn.EVENT_TYPE, eventType);
    }
    
    public void filledByCursor(Cursor cursor){
        setPkFastTemplateId(cursor.getLong(cursor.getColumnIndex(DBConfig.FastTemplateColumn.PK_FAST_TEMPLATE_ID)));
        setTemplate(cursor.getString(cursor.getColumnIndex(DBConfig.FastTemplateColumn.TEMPLATE)));
        setEventType(cursor.getInt(cursor.getColumnIndex(DBConfig.FastTemplateColumn.EVENT_TYPE)));
    }

    public void copyFrom(FastTemplate fastTemplate){
        setPkFastTemplateId(fastTemplate.getPkFastTemplateId());
        setTemplate(fastTemplate.getTemplate());
        setEventType(fastTemplate.getEventType());
    }

    /** Parcelable方法 */

    public FastTemplate(){}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.pkFastTemplateId);
        dest.writeString(this.template);
        dest.writeValue(this.eventType);
    }

    protected FastTemplate(Parcel in) {
        this.pkFastTemplateId = (Long) in.readValue(Long.class.getClassLoader());
        this.template = in.readString();
        this.eventType = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<FastTemplate> CREATOR = new Creator<FastTemplate>() {
        @Override
        public FastTemplate createFromParcel(Parcel source) {
            return new FastTemplate(source);
        }

        @Override
        public FastTemplate[] newArray(int size) {
            return new FastTemplate[size];
        }
    };
}
