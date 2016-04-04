package link.ebbinghaus.planning.ebbinghaus.db.decorator.impl;

import java.util.List;

import link.ebbinghaus.planning.ebbinghaus.db.dao.FastTemplateDao;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class FastTemplateDaoDecorator extends BaseDaoDecorator<FastTemplate> {
    private FastTemplateDao dao;

    public FastTemplateDaoDecorator() {
        super(new FastTemplateDao());
        dao = (FastTemplateDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }

    public List<FastTemplate> selectSpecLearningAll() {
        return dao.selectSpecLearningAll();
    }

    public List<FastTemplate> selectSpecNormalAll() {
        return dao.selectSpecNormalAll();
    }

    public List<FastTemplate> selectAbstractAll() {
        return dao.selectAbstractAll();
    }
}
