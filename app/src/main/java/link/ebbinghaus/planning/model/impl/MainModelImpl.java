package link.ebbinghaus.planning.model.impl;

import android.util.SparseArray;

import link.ebbinghaus.planning.model.MainModel;
import link.ebbinghaus.planning.view.fragment.impl.AchievementFragment;
import link.ebbinghaus.planning.view.fragment.impl.HistoryFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDoneFragment;
import link.ebbinghaus.planning.view.fragment.impl.SettingsFragment;
import link.ebbinghaus.planning.view.fragment.impl.StoreFragment;
import link.ebbinhaus.planning.R;
import link.ebbinghaus.planning.view.fragment.impl.ExtensionFragment;
import link.ebbinghaus.planning.view.fragment.impl.PlanningDisplayFragment;

/**
 * Created by WINFIELD on 2016/2/19.
 */
public class MainModelImpl implements MainModel {
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
