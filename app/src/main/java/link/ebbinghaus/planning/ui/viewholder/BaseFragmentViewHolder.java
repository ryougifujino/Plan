package link.ebbinghaus.planning.ui.viewholder;

import android.view.View;

/**
 * Created by WINFIELD on 2016/3/13.
 */
public class BaseFragmentViewHolder {
    private View mView;

    public BaseFragmentViewHolder(View view) {
        this.mView = view;
    }

    protected  <T extends View> T find(int viewId) {
        return (T) mView.findViewById(viewId);
    }
}
