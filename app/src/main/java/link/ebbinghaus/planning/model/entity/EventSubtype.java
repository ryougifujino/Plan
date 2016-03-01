package link.ebbinghaus.planning.model.entity;

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
}
