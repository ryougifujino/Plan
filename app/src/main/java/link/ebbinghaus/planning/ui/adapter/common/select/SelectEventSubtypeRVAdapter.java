package link.ebbinghaus.planning.ui.adapter.common.select;

import android.content.Context;

import link.ebbinghaus.planning.ui.viewholder.common.select.DeleteToolbarViewHolder;
import link.ebbinghaus.planning.core.model.local.po.EventSubtype;

/**
 * Created by WINFIELD on 2016/3/20.
 */
public class SelectEventSubtypeRVAdapter extends SelectRecycleViewAdapter<EventSubtype> {

    public SelectEventSubtypeRVAdapter(Context context, ISelectDaoAdapter<EventSubtype> daoAdapter, DeleteToolbarViewHolder deleteToolbar) {
        super(context, daoAdapter, deleteToolbar);
    }


    @Override
    protected void deleteFromDatabase(EventSubtype eventSubtype) {
        mDaoAdapter.deleteByPrimaryKey(eventSubtype.getPkEventSubtypeId());
    }

    @Override
    public void onBindViewHolder(SelectRecycleViewAdapter.ViewHolder holder, int position) {
        holder.configure(position, mData.get(position).getEventSubtype());
    }

    @Override
    public void onDialogConfirm(String content) {
        EventSubtype eventSubtype = new EventSubtype();
        eventSubtype.setEventSubtype(content);
        mDaoAdapter.insert(eventSubtype);
        mData.add(0, eventSubtype);
        mListitemsSelectedStatus.add(0,false);
        this.notifyDataSetChanged();
    }
}
