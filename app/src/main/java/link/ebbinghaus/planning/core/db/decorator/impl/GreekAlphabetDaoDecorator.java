package link.ebbinghaus.planning.core.db.decorator.impl;

import link.ebbinghaus.planning.core.db.dao.GreekAlphabetDao;
import link.ebbinghaus.planning.core.model.po.GreekAlphabet;

/**
 * Created by WINFIELD on 2016/3/28.
 */
public class GreekAlphabetDaoDecorator extends BaseDaoDecorator<GreekAlphabet> {
    private GreekAlphabetDao dao;

    public GreekAlphabetDaoDecorator() {
        super(new GreekAlphabetDao());
        dao = (GreekAlphabetDao) super.dao;
    }

    @Override
    public void closeDB() {
        super.closeDB();
    }
}
