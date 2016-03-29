package link.ebbinghaus.planning.presenter;

import link.ebbinghaus.planning.model.entity.vo.planning.display.SpecEventDetailVo;

/**
 * Created by WINFIELD on 2016/3/24.
 */
public interface PlanningDisplaySpecEventDetailPresenter {

    /**
     * 初始化具体计划详情页面(视图和数据两方面)
     * @param specEventDetail 页面数据
     */
    void initSpecEventDetail(SpecEventDetailVo specEventDetail);
}
