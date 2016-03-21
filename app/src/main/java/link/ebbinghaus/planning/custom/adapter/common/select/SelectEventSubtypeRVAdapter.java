package link.ebbinghaus.planning.custom.adapter.common.select;

import android.content.Context;

import link.ebbinghaus.planning.custom.db.daoadapter.impl.EventSubtypeDaoAdapter;
import link.ebbinghaus.planning.custom.viewholder.common.select.DeleteToolbarViewHolder;
import link.ebbinghaus.planning.model.entity.po.EventSubtype;

/**
 * Created by WINFIELD on 2016/3/20.
 */
public class SelectEventSubtypeRVAdapter extends SelectRecycleViewAdapter<EventSubtype> {
    private EventSubtypeDaoAdapter mEventSubtypeDao = new EventSubtypeDaoAdapter();

    public SelectEventSubtypeRVAdapter(Context context, DeleteToolbarViewHolder deleteToolbar) {
        super(context, deleteToolbar);
        mData = mEventSubtypeDao.findAll();
        initListitemsSelectedStatus();
    }

    @Override
    public void onDestroy() {
        mEventSubtypeDao.closeDB();
    }


    @Override
    protected void deleteFromDatabase(EventSubtype eventSubtype) {
        mEventSubtypeDao.removeByPrimaryKey(eventSubtype.getPkEventSubtypeId());
    }

    @Override
    public void onBindViewHolder(SelectRecycleViewAdapter.ViewHolder holder, int position) {
        holder.configure(position, mData.get(position).getEventSubtype());
    }
}
