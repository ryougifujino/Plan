package link.ebbinghaus.planning.custom.other.mock;

import com.yurikami.lib.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.model.entity.Event;

/**
 * Created by WINFIELD on 2016/3/6.
 */
public class MonthEventsMock {
    public static List<Event> getOneMonthEvents(){
        List<Event> oneMonthEvents = new ArrayList<>();

        for (int i = 0;i<10;i++) {
            for (int j = 0; j < 10; j++) {
                Event event = new Event();
                event.setDescription("描述" + i + j);
                event.setEventExpectedFinishedDate(DateUtils.newDateTimestamp(2016, 4, 6 + i));
                event.setIsEventFinished(j % 2 == 0 ? true : false);
                event.setEventType(j % 2 == 0 ? 1 : 2);
                oneMonthEvents.add(event);
            }
        }
        return oneMonthEvents;
    }
}
