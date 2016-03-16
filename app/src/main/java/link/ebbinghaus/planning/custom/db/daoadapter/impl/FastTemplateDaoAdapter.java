package link.ebbinghaus.planning.custom.db.daoadapter.impl;

import link.ebbinghaus.planning.custom.db.dao.FastTemplateDao;
import link.ebbinghaus.planning.custom.db.daoadapter.BaseDaoAdapter;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;

/**
 * Created by WINFIELD on 2016/3/17.
 */
public class FastTemplateDaoAdapter implements BaseDaoAdapter<FastTemplate> {
    private FastTemplateDao dao;

    @Override
    public void add(FastTemplate fastTemplate) {
        dao = new FastTemplateDao();
    }

    @Override
    public void removeByPrimaryKey(Integer pk) {
        dao.removeByPrimaryKey(pk);
    }

    @Override
    public void modifyByPrimaryKey(FastTemplate fastTemplate) {
        dao.modifyByPrimaryKey(fastTemplate);
    }

    @Override
    public FastTemplate findByPrimaryKey(Integer pk) {
        return dao.findByPrimaryKey(pk);
    }

    @Override
    public void closeDB() {
        dao.closeDB();
    }
}
