package link.ebbinghaus.planning.ui.viewholder;

import android.app.Activity;
import android.view.View;

/**
 * Created by WINFIELD on 2016/3/21.
 */
public class BaseActivityViewHolder {
    private Activity mActivity;


    public BaseActivityViewHolder(Activity activity) {
        this.mActivity = activity;
    }

    @SuppressWarnings("unchecked")
    protected  <T extends View> T find(int viewId) {
        return (T) mActivity.findViewById(viewId);
    }

    protected String getString(int resId){
        return mActivity.getString(resId);
    }

    /** Returns a color integer associated with a particular resource ID. */
    protected int getColor(int resId){
        return mActivity.getResources().getColor(resId);
    }

}
