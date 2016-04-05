package link.ebbinghaus.planning.ui.viewholder.common.select;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import link.ebbinghaus.planning.ui.viewholder.BaseActivityViewHolder;
import link.ebbinghaus.planning.R;

/**
 * Created by WINFIELD on 2016/3/21.
 */
public class DeleteToolbarViewHolder extends BaseActivityViewHolder {
    public RelativeLayout toolbarRl;
    public ImageView arrowBackIv;
    public TextView selectInfolTv;
    public TextView selectAllToggleTv;
    public TextView deleteTv;
    
    public DeleteToolbarViewHolder(Activity activity) {
        super(activity);
        toolbarRl = find(R.id.rl_common_select_delete_toolbar);
        arrowBackIv = find(R.id.iv_common_select_delete_toolbar_arrow_back);
        selectInfolTv = find(R.id.tv_common_select_delete_toolbar_select_info);
        selectAllToggleTv = find(R.id.tv_common_select_delete_toolbar_select_all_toggle);
        deleteTv = find(R.id.tv_common_select_delete_toolbar_delete);
    }
}
