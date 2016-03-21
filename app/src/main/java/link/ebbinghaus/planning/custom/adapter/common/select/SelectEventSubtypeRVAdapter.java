package link.ebbinghaus.planning.custom.adapter.common.select;

import android.content.Context;

import link.ebbinghaus.planning.custom.viewholder.common.select.DeleteToolbarViewHolder;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;

/**
 * Created by WINFIELD on 2016/3/20.
 */
public class SelectEventSubtypeRVAdapter extends SelectRecycleViewAdapter<EventSubtype> {

    public SelectEventSubtypeRVAdapter(Context context, ISelectDaoAdapter dao, DeleteToolbarViewHolder deleteToolbar) {
        super(context, dao, deleteToolbar);
    }


    @Override
    protected void deleteFromDatabase(EventSubtype eventSubtype) {
        mDao.deleteByPrimaryKey(eventSubtype.getPkEventSubtypeId());
    }

    @Override
    public void onBindViewHolder(SelectRecycleViewAdapter.ViewHolder holder, int position) {
        holder.configure(position, mData.get(position).getEventSubtype());
    }
}
