package link.ebbinghaus.planning.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import link.ebbinghaus.planning.core.model.local.sys.Tab;

/**
 * Created by FIELD on 2016/2/29.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Tab> mTabs;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context, List<Tab> tabs) {
        super(fm);
        mContext = context;
        mTabs = tabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return mTabs.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }


}
