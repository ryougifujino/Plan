package link.ebbinghaus.planning.custom.adapter.common.select;

import android.content.Context;
import android.view.View;

import link.ebbinghaus.planning.custom.db.daoadapter.impl.EventGroupDaoAdapter;
import link.ebbinghaus.planning.custom.viewholder.common.select.DeleteToolbarViewHolder;
import link.ebbinghaus.planning.model.entity.po.EventGroup;

/**
 * Created by WINFIELD on 2016/3/20.
 */
public class SelectEventGroupRVAdapter extends SelectRecycleViewAdapter<EventGroup> {
    private EventGroupDaoAdapter mEventGroupDao = new EventGroupDaoAdapter();

    public SelectEventGroupRVAdapter(Context context, DeleteToolbarViewHolder deleteToolbar) {
        super(context, deleteToolbar);
        mData = mEventGroupDao.findAll();
    }



    @Override
    public void onDestroy() {
        mEventGroupDao.closeDB();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void deleteFromDatabase(EventGroup eventGroup) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }



    @Override
    public void onBindViewHolder(SelectRecycleViewAdapter.ViewHolder holder, int position) {
        String description = mData.get(position).getDescription();
        holder.descriptionTv.setText(description);
    }
}
