package link.ebbinghaus.planning.ui.presenter.planning.display;

import link.ebbinghaus.planning.core.model.local.po.Event;
import link.ebbinghaus.planning.core.model.local.vo.planning.display.SpecEventDetailVo;

/**
 * Created by WINFIELD on 2016/3/24.
 */
public interface PlanningDisplaySpecEventDetailPresenter {

    /**
     * 初始化具体计划详情页面(视图和数据两方面)
     * @param specEventDetail 页面数据
     */
    void initSpecEventDetail(SpecEventDetailVo specEventDetail);

    /**
     * 删除本详情页面所显示的计划<br>
     * 并且会处理这个计划的相关内容：学习计划组、计划组、希腊字母
     * @param event 删除参数
     */
    void deleteThisEventAndProcessRelated(Event event);
}
