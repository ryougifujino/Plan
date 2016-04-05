package link.ebbinghaus.planning.core.service.impl;

import android.util.SparseArray;

import link.ebbinghaus.planning.core.service.MainService;
import link.ebbinghaus.planning.ui.view.achievement.fragment.AchievementFragment;
import link.ebbinghaus.planning.ui.view.history.fragment.HistoryFragment;
import link.ebbinghaus.planning.ui.view.planning.done.fragment.PlanningDoneFragment;
import link.ebbinghaus.planning.ui.view.setting.fragment.SettingsFragment;
import link.ebbinghaus.planning.ui.view.store.fragment.StoreFragment;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.ui.view.extension.fragment.ExtensionFragment;
import link.ebbinghaus.planning.ui.view.planning.display.fragment.PlanningDisplayFragment;

/**
 * Created by WINFIELD on 2016/2/19.
 */
public class MainServiceImpl implements MainService {
    @Override
    public void cacheMainDrawerFragmentMap(SparseArray<Class> fm) {
        fm.put(R.id.item_main_drawer_planning_display, PlanningDisplayFragment.class);
        fm.put(R.id.item_main_drawer_planning_done, PlanningDoneFragment.class);
        fm.put(R.id.item_main_drawer_history, HistoryFragment.class);
        fm.put(R.id.item_main_drawer_achievement, AchievementFragment.class);
        fm.put(R.id.item_main_drawer_extension, ExtensionFragment.class);
        fm.put(R.id.item_main_drawer_store, StoreFragment.class);
        fm.put(R.id.item_main_drawer_settings, SettingsFragment.class);
    }
}
