package link.ebbinghaus.planning.ui.adapter.common.select;

import android.content.Context;

import link.ebbinghaus.planning.ui.viewholder.common.select.DeleteToolbarViewHolder;
import link.ebbinghaus.planning.core.model.po.EventGroup;

/**
 * Created by WINFIELD on 2016/3/20.
 */
public class SelectEventGroupRVAdapter extends SelectRecycleViewAdapter<EventGroup> {

    public SelectEventGroupRVAdapter(Context context, ISelectDaoAdapter<EventGroup> daoAdapter, DeleteToolbarViewHolder deleteToolbar) {
        super(context, daoAdapter, deleteToolbar);
    }

    @Override
    protected void deleteFromDatabase(EventGroup eventGroup) {
        mDaoAdapter.deleteByPrimaryKey(eventGroup.getPkEventGroupId());
    }


    @Override
    public void onBindViewHolder(SelectRecycleViewAdapter.ViewHolder holder, int position) {
        holder.configure(position, mData.get(position).getDescription());
    }

    @Override
    public void onDialogConfirm(String content) {
        EventGroup eventGroup = new EventGroup();
        eventGroup.setDescription(content);
        eventGroup.setCreateTime(System.currentTimeMillis());
        mDaoAdapter.insert(eventGroup);
        mData.add(0, eventGroup);
        mListitemsSelectedStatus.add(0,false);
        this.notifyDataSetChanged();
    }
}
