package link.ebbinhaus.planning.model.entity;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class FastTemplate {
    private Integer pkFastTemplateId;
    private String template;
    private String eventType;

    public Integer getPkFastTemplateId() {
        return pkFastTemplateId;
    }

    public void setPkFastTemplateId(Integer pkFastTemplateId) {
        this.pkFastTemplateId = pkFastTemplateId;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
