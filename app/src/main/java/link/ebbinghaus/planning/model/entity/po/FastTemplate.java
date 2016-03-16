package link.ebbinghaus.planning.model.entity.po;

import android.content.ContentValues;

import link.ebbinghaus.planning.custom.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class FastTemplate {
    private Integer pkFastTemplateId;
    private String template;
    private Integer eventType;

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

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public void convertToContentValues(ContentValues values){
        values.put(DBConfig.FastTemplateColumn.PK_FAST_TEMPLATE_ID, pkFastTemplateId);
        values.put(DBConfig.FastTemplateColumn.TEMPLATE, template);
        values.put(DBConfig.FastTemplateColumn.EVENT_TYPE, eventType);
    }
}
