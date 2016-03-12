package link.ebbinghaus.planning.model.impl;

import com.yurikami.lib.entity.Datetime;
import com.yurikami.lib.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.custom.constant.PlanningDisplayConstant;
import link.ebbinghaus.planning.model.PlanningDisplaySpecificModel;
import link.ebbinghaus.planning.model.entity.Event;
import link.ebbinghaus.planning.model.entity.sys.Tab;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDisplaySpecGroupFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDisplaySpecMonthFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDisplaySpecWeekFragment;

/**
 * Created by WINFIELD on 2016/3/1.
 */
public class PlanningDisplaySpecificModelImpl implements PlanningDisplaySpecificModel {

    @Override
    public List<Tab> makePlanningDisplaySpecificTabs() {
        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_SPEC_MONTH, new PlanningDisplaySpecMonthFragment()));
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_SPEC_WEEK, new PlanningDisplaySpecWeekFragment()));
        tabs.add(new Tab(PlanningDisplayConstant.SUB_TAB_NAME_SPEC_GROUP, new PlanningDisplaySpecGroupFragment()));
        return tabs;

    }

    @Override
    public List<Event>[] eventsToBlocks(List<Event> events, int dayOfMonth) {
        List<Event>[] blocks = new List[dayOfMonth];
        for (int i = 0 ;i < blocks.length ;i++){
            blocks[i] = new ArrayList<>();
        }
        for (Event event: events){
            long dateL = event.getEventExpectedFinishedDate();
            Integer day = DateUtils.day(dateL);
            blocks[day - 1].add(event);
        }
        return blocks;
    }

    @Override
    public void makeDayWeekListitems(List<Datetime> dayWeekListitems, int dayInMonth, Datetime datetime) {
        if (dayWeekListitems == null){
            dayWeekListitems = new ArrayList<>();
        }
        Datetime startDate = Datetime.buildDate(datetime.getYear(), datetime.getMonth(), 1);
        int startWeek = DateUtils.dayOfWeek(startDate);
        for (int day = 1; day <= dayInMonth ;day++){
            Datetime dayWeek = Datetime.buildDate(null, null, day);
            int w = (startWeek + day -1) % 7;
            dayWeek.setWeek( (w == 0) ? 7 : w );
            dayWeekListitems.add(dayWeek);
        }
    }

}
