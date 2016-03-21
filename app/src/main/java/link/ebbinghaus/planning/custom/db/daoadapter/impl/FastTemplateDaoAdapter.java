package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import java.util.List;

import link.ebbinghaus.planning.custom.db.dao.FastTemplateDao;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class FastTemplateDaoAdapter extends BaseDaoAdapter<FastTemplate> {
    private FastTemplateDao dao;

    public FastTemplateDaoAdapter() {
        super(new FastTemplateDao());
        dao = (FastTemplateDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }

    public List<FastTemplate> findSpecLearningAll() {
        return dao.selectSpecLearningAll();
    }

    public List<FastTemplate> findSpecNormalAll() {
        return dao.selectSpecNormalAll();
    }

    public List<FastTemplate> findAbstractAll() {
        return dao.selectAbstractAll();
    }
}
