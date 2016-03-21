package link.ebbinghaus.planning.model.entity.po;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class EventSubtype implements Parcelable{
    private Integer pkEventSubtypeId;
    private String eventSubtype;


    public Integer getPkEventSubtypeId() {
        return pkEventSubtypeId;
    }

    public void setPkEventSubtypeId(Integer pkEventSubtypeId) {
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
        values.put(DBConfig.EventSubtypeColumn.PK_EVENT_SUBTYPE_ID, pkEventSubtypeId);
        values.put(DBConfig.EventSubtypeColumn.EVENT_SUBTYPE, eventSubtype);
    }

    public void filledByCursor(Cursor cursor){
        setPkEventSubtypeId(cursor.getInt(cursor.getColumnIndex(DBConfig.EventSubtypeColumn.PK_EVENT_SUBTYPE_ID)));
        setEventSubtype(cursor.getString(cursor.getColumnIndex(DBConfig.EventSubtypeColumn.EVENT_SUBTYPE)));
    }

    /** Parcelable方法 */

    public EventSubtype(){}

    protected EventSubtype(Parcel in) {
        eventSubtype = in.readString();
    }

    public static final Creator<EventSubtype> CREATOR = new Creator<EventSubtype>() {
        @Override
        public EventSubtype createFromParcel(Parcel in) {
            return new EventSubtype(in);
        }

        @Override
        public EventSubtype[] newArray(int size) {
            return new EventSubtype[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventSubtype);
    }
}
