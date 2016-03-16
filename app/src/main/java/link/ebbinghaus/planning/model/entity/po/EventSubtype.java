package link.ebbinghaus.planning.model.entity.po;

import android.content.ContentValues;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class EventSubtype {
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

    public void convertToContentValues(ContentValues values){
        values.put(DBConfig.EventSubtypeColumn.PK_EVENT_SUBTYPE_ID, pkEventSubtypeId);
        values.put(DBConfig.EventSubtypeColumn.EVENT_SUBTYPE, eventSubtype);
    }
}
