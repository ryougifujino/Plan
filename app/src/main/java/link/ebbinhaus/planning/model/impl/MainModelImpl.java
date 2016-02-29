package link.ebbinhaus.planning.model.impl;

import android.util.SparseArray;

import link.ebbinhaus.planning.R;
import link.ebbinhaus.planning.model.MainModel;
import link.ebbinhaus.planning.view.fragment.AchievementFragment;
import link.ebbinhaus.planning.view.fragment.ExtensionFragment;
import link.ebbinhaus.planning.view.fragment.HistoryFragment;
import link.ebbinhaus.planning.view.fragment.PlanningDisplayFragment;
import link.ebbinhaus.planning.view.fragment.PlanningDoneFragment;
import link.ebbinhaus.planning.view.fragment.SettingsFragment;
import link.ebbinhaus.planning.view.fragment.StoreFragment;

/**
 * Created by WINFIELD on 2016/2/19.
 */
public class MainModelImpl implements MainModel {
    @Override
    public void cacheMainDrawerFragmentMap(SparseArray<Class> fm) {
        if(fm == null)
            fm = new SparseArray<>();
        fm.put(R.id.item_main_drawer_planning_display, PlanningDisplayFragment.class);
        fm.put(R.id.item_main_drawer_planning_done, PlanningDoneFragment.class);
        fm.put(R.id.item_main_drawer_history, HistoryFragment.class);
        fm.put(R.id.item_main_drawer_achievement, AchievementFragment.class);
        fm.put(R.id.item_main_drawer_extension, ExtensionFragment.class);
        fm.put(R.id.item_main_drawer_store, StoreFragment.class);
        fm.put(R.id.item_main_drawer_settings, SettingsFragment.class);
    }
}
