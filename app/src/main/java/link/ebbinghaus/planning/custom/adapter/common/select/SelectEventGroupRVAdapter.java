package link.ebbinghaus.planning.custom.adapter.common.select;

import android.content.Context;

import link.ebbinghaus.planning.custom.viewholder.common.select.DeleteToolbarViewHolder;
import link.ebbinghaus.planning.model.entity.po.EventGroup;

/**
 * Created by WINFIELD on 2016/3/20.
 */
public class SelectEventGroupRVAdapter extends SelectRecycleViewAdapter<EventGroup> {

    public SelectEventGroupRVAdapter(Context context, ISelectDaoAdapter dao, DeleteToolbarViewHolder deleteToolbar) {
        super(context, dao, deleteToolbar);
    }

    @Override
    protected void deleteFromDatabase(EventGroup eventGroup) {
        mDao.deleteByPrimaryKey(eventGroup.getPkEventGroupId());
    }


    @Override
    public void onBindViewHolder(SelectRecycleViewAdapter.ViewHolder holder, int position) {
        holder.descriptionTv.setText(mData.get(position).getDescription());
    }
}
