package link.ebbinghaus.planning.custom.adapter.common.select;

import java.util.List;

import link.ebbinghaus.planning.custom.constant.config.entity.FastTemplateConfig;
import link.ebbinghaus.planning.custom.db.decorator.impl.FastTemplateDaoDecorator;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;

/**
 * Created by WINFIELD on 2016/3/21.
 */
public class FastTemplateSelectDaoAdapter implements ISelectDaoAdapter<FastTemplate> {
    private FastTemplateDaoDecorator dao = new FastTemplateDaoDecorator();
    private int flag;

    public FastTemplateSelectDaoAdapter(int flag) {
        this.flag = flag;
    }

    @Override
    public List<FastTemplate> selectAll() {
        switch (flag){
            case FastTemplateConfig.TYPE_SPEC_LEARNING:
                return dao.selectSpecLearningAll();
            case FastTemplateConfig.TYPE_SPEC_NORMAL:
                return dao.selectSpecNormalAll();
            case FastTemplateConfig.TYPE_ABSTRACT:
                 return dao.selectAbstractAll();
            default:
                throw new IllegalArgumentException("传递的快速模板类型不正确");
        }
    }

    @Override
    public void deleteByPrimaryKey(Long pk) {
        dao.deleteByPrimaryKey(pk);
    }

    @Override
    public void insert(FastTemplate fastTemplate) {
        dao.insert(fastTemplate);
    }

    @Override
    public void closeDB() {
        dao.closeDB();
    }

    public Integer getEventType(){
        switch (flag){
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
