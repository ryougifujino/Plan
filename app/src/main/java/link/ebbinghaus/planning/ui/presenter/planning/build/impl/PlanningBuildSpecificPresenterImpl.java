package link.ebbinghaus.planning.ui.presenter.planning.build.impl;

import com.yurikami.lib.model.Datetime;
import com.yurikami.lib.util.DateUtils;

import java.util.Map;
import java.util.TreeMap;

import link.ebbinghaus.planning.core.model.local.po.DefaultInputValue;
import link.ebbinghaus.planning.core.model.local.po.EventGroup;
import link.ebbinghaus.planning.core.model.local.po.EventSubtype;
import link.ebbinghaus.planning.core.model.local.vo.planning.build.InputEventVo;
import link.ebbinghaus.planning.core.service.PlanningBuildService;
import link.ebbinghaus.planning.core.service.impl.PlanningBuildServiceImpl;
import link.ebbinghaus.planning.ui.presenter.planning.build.PlanningBuildSpecificPresenter;
import link.ebbinghaus.planning.ui.view.planning.build.PlanningBuildSpecificView;

/**
 * Created by WINFIELD on 2016/3/14.
 */
public class PlanningBuildSpecificPresenterImpl implements PlanningBuildSpecificPresenter {
    private PlanningBuildSpecificView mView;
    private PlanningBuildService mPlanningBuildService;

    public PlanningBuildSpecificPresenterImpl(PlanningBuildSpecificView planningBuildSpecificView) {
        this.mView = planningBuildSpecificView;
        this.mPlanningBuildService = new PlanningBuildServiceImpl();
    }

    @Override
    public void registerListeners() {
        mView.setListeners();
    }

    @Override
    public void getAndSetDefaultInputValues(InputEventVo inputEvent) {
        DefaultInputValue value = mPlanningBuildService.findDefaultInputValue();
        mView.setDefaultInputValue(value);
        inputEvent.setMaxWidth(value.getMaxWidth());
        inputEvent.setIsGreekAlphabetMarked(value.getIsGreekAlphabetMarked());
        inputEvent.setIsRemind(value.getIsRemind());
        inputEvent.setRemindTime(value.getRemindTime());
        inputEvent.setStrategy(value.getStrategy());
        inputEvent.setIsShowEventSequence(value.getIsShowEventSequence());
        //默认值,不在默认值数据表里,手动设置的
        inputEvent.setEventExpectedFinishedDate(DateUtils.currentDateTimestamp());
    }

    @Override
    public void switchBuildPanel() {
        mView.setBuildPanel();
    }

    @Override
    public void configureEventSubtype(EventSubtype result) {
        mView.setSubtype(result);
    }

    @Override
    public void configureDescription(String template) {
        if(template != null) {
            mView.setFastTemplate(template);
        }
    }

    @Override
    public void configureStrategy() {
        mView.selectStrategy();
    }

    @Override
    public void configureExpectedFinishDate() {
        mView.setExpectedFinishDate();
    }

    @Override
    public void configureRemind(boolean isChecked) {
        mView.setRemind(isChecked);
    }

    @Override
    public void configureRemindTime() {
        mView.setRemindTime();
    }

    @Override
    public void configureSequence(boolean isChecked) {
        mView.setSequence(isChecked);
    }

    @Override
    public void configureGreekAlphabet(boolean isChecked) {
        mView.setGreekAlphabet(isChecked);
    }

    @Override
    public void configureEventGroup(EventGroup eventGroup) {
        mView.setEventGroup(eventGroup);
    }

    @Override
    public void initChart(Datetime initDate) {
        long initDateTimestamp = DateUtils.newDateTimestamp(initDate.getYear(), initDate.getMonth(), initDate.getDay());
        updateChart(initDateTimestamp, null);
    }

    @Override
    public void updateChart(long startDateTimestamp, int[] strategy) {
        //30日每日的宽度信息，没有宽度的元素宽度值为0
        TreeMap<Long, Integer> widthStatistics = mPlanningBuildService.find30daysStatistics(startDateTimestamp);

        /* step1.把制定的计划所占的宽度加入统计信息中 */
        Map.Entry<Long, Integer> firstEntry = widthStatistics.firstEntry();
        if (strategy == null){
            //普通计划制定视图
            widthStatistics.put(firstEntry.getKey(),firstEntry.getValue() + 1);
        }else {
            //学习计划制定视图
            for (int aStrategy : strategy) {
                int daysOffset = aStrategy - 1;
                //如果天数offset超过了29就停止循环
                if (daysOffset > 29) {
                    break;
                }
                long dateTimestamp = DateUtils.timestampAfter(firstEntry.getKey(), daysOffset);
                widthStatistics.put(dateTimestamp, widthStatistics.get(dateTimestamp) + 1);
            }
        }

        /* step2.计算maxValue和step */
        //求最大值
        int maxWidth = 0;
        for (Integer width : widthStatistics.values()) {
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        //maxValue最大值为25，最小值为3（由1经while(true)...计算得出）
        int maxValue;
        int step;
        if (maxWidth == 0) {
            maxValue = 1;
        } else if (maxWidth > 25) {
            maxValue = 25;
        } else {
            maxValue = maxWidth;
        }
        while (true) {
            boolean ok = false;
            //Y轴标签最多5个，最少3个（并且数量从5~3优先级递减）
            int yAxisCount = 5;
            for (; yAxisCount >= 3; yAxisCount--) {
                if (maxValue % yAxisCount == 0) {
                    ok = true;
                    break;
                }
            }
            if (ok) {
                step = maxValue / yAxisCount;
                break;
            } else {
                maxValue++;
            }
        }
        /* step3.计算labels和values */
        String[] labels = new String[30];
        float[] values = new float[30];
        int firstMonth = DateUtils.month(firstEntry.getKey());
        int index = 0;
        boolean isShowedTransitionDate = false;
        for (Map.Entry<Long, Integer> widthEntry : widthStatistics.entrySet()) {
            //本次循环的时间和宽度
            long timestamp = widthEntry.getKey();
            int width = widthEntry.getValue();

            //提取时间的月日
            int month = DateUtils.month(timestamp);
            int day = DateUtils.day(timestamp);

            //每5个显示一次，并且如果是过渡月则特殊显示
            if (index == 0 || (((index + 1) % 5) == 0)) {
                if (firstMonth != month && !isShowedTransitionDate) {
                    labels[index] = month + "." + day;
                    isShowedTransitionDate = true;
                } else {
                    labels[index] = day + "";
                }
            }else {
                labels[index] = "";
            }

            values[index] = width;
            index++;
        }

        mView.renderChart(labels, values, maxValue, step, true);
    }

}
