package link.ebbinghaus.planning.core.model.local.po;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.yurikami.lib.util.NonNullContentValues;

import link.ebbinghaus.planning.app.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class EventSubtype implements Parcelable{
    private Long pkEventSubtypeId;
    private String eventSubtype;

    public Long getPkEventSubtypeId() {
        return pkEventSubtypeId;
    }

    public void setPkEventSubtypeId(Long pkEventSubtypeId) {
        this.pkEventSubtypeId = pkEventSubtypeId;
    }

    public String getEventSubtype() {
        return eventSubtype;
    }

    public void setEventSubtype(String eventSubtype) {
        this.eventSubtype = eventSubtype;
    }

    /** 辅助方法 */

    public void convertToContentValues(ContentValues values){
        NonNullContentValues nonNullValues = new NonNullContentValues(values);
        nonNullValues.put(DBConfig.EventSubtypeColumn.PK_EVENT_SUBTYPE_ID, pkEventSubtypeId);
        nonNullValues.put(DBConfig.EventSubtypeColumn.EVENT_SUBTYPE, eventSubtype);
    }

    public void filledByCursor(Cursor cursor){
        setPkEventSubtypeId(cursor.getLong(cursor.getColumnIndex(DBConfig.EventSubtypeColumn.PK_EVENT_SUBTYPE_ID)));
        setEventSubtype(cursor.getString(cursor.getColumnIndex(DBConfig.EventSubtypeColumn.EVENT_SUBTYPE)));
    }

    public void copyFrom(EventSubtype eventSubtype){
        setPkEventSubtypeId(eventSubtype.getPkEventSubtypeId());
        setEventSubtype(eventSubtype.getEventSubtype());
    }

    /** Parcelable方法 */

    public EventSubtype(){}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.pkEventSubtypeId);
        dest.writeString(this.eventSubtype);
    }

    protected EventSubtype(Parcel in) {
        this.pkEventSubtypeId = (Long) in.readValue(Long.class.getClassLoader());
        this.eventSubtype = in.readString();
    }

    public static final Creator<EventSubtype> CREATOR = new Creator<EventSubtype>() {
        @Override
        public EventSubtype createFromParcel(Parcel source) {
            return new EventSubtype(source);
        }

        @Override
        public EventSubtype[] newArray(int size) {
            return new EventSubtype[size];
        }
    };
}
