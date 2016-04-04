package link.ebbinghaus.planning.ebbinghaus.adapter.common.select;

import java.util.List;

import link.ebbinghaus.planning.ebbinghaus.constant.config.entity.FastTemplateConfig;
import link.ebbinghaus.planning.ebbinghaus.db.decorator.impl.FastTemplateDaoDecorator;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;

/**
 * Created by WINFIELD on 2016/3/21.
 */
public class FastTemplateSelectDaoAdapter implements ISelectDaoAdapter<FastTemplate> {
    private int mFlag;

    public FastTemplateSelectDaoAdapter(int flag) {
        this.mFlag = flag;
    }

    @Override
    public List<FastTemplate> selectAll() {
        FastTemplateDaoDecorator dao = new FastTemplateDaoDecorator();
        List<FastTemplate> fastTemplates;
        switch (mFlag){
            case FastTemplateConfig.TYPE_SPEC_LEARNING:
                fastTemplates = dao.selectSpecLearningAll();
                break;
            case FastTemplateConfig.TYPE_SPEC_NORMAL:
                fastTemplates = dao.selectSpecNormalAll();
                break;
            case FastTemplateConfig.TYPE_ABSTRACT:
                 fastTemplates =  dao.selectAbstractAll();
                break;
            default:
                throw new IllegalArgumentException("传递的快速模板类型不正确");
        }
        dao.closeDB();
        return fastTemplates;
    }

    @Override
    public void deleteByPrimaryKey(Long pk) {
        FastTemplateDaoDecorator dao = new FastTemplateDaoDecorator();
        dao.deleteByPrimaryKey(pk);
        dao.closeDB();
    }

    @Override
    public void insert(FastTemplate fastTemplate) {
        FastTemplateDaoDecorator dao = new FastTemplateDaoDecorator();
        dao.insert(fastTemplate);
        dao.closeDB();
    }

    public Integer getEventType(){
        switch (mFlag){
            case FastTemplateConfig.TYPE_SPEC_LEARNING:
                return FastTemplateConfig.TYPE_SPEC_LEARNING;
            case FastTemplateConfig.TYPE_SPEC_NORMAL:
                return FastTemplateConfig.TYPE_SPEC_NORMAL;
            case FastTemplateConfig.TYPE_ABSTRACT:
                return FastTemplateConfig.TYPE_ABSTRACT;
            default:
                throw new IllegalArgumentException("传递的快速模板类型不正确");
        }
    }
}
