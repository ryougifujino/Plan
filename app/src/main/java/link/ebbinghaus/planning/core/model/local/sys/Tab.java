package link.ebbinghaus.planning.core.model.local.sys;

import android.support.v4.app.Fragment;

/**
 * Created by WINFIELD on 2016/2/29.
 */
public class Tab {
    private String title;
    private Fragment fragment;

    public Tab(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
