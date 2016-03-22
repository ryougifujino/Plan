package link.ebbinghaus.planning.custom.adapter.common.select;

import java.util.List;

import link.ebbinghaus.planning.custom.constant.entity.FastTemplateConstant;
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
            case FastTemplateConstant.SPEC_LEARNING_TYPE:
                return dao.findSpecLearningAll();
            case FastTemplateConstant.SPEC_NORMAL_TYPE:
                return dao.findSpecNormalAll();
            case FastTemplateConstant.ABSTRACT_TYPE:
                 return dao.findAbstractAll();
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
            case FastTemplateConstant.SPEC_LEARNING_TYPE:
                return FastTemplateConstant.SPEC_LEARNING_TYPE;
            case FastTemplateConstant.SPEC_NORMAL_TYPE:
                return FastTemplateConstant.SPEC_NORMAL_TYPE;
            case FastTemplateConstant.ABSTRACT_TYPE:
                return FastTemplateConstant.ABSTRACT_TYPE;
            default:
                throw new IllegalArgumentException("传递的快速模板类型不正确");
        }
    }
}
