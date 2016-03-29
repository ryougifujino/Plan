package link.ebbinghaus.planning.custom.adapter.common.select;

import android.content.Context;

import link.ebbinghaus.planning.custom.viewholder.common.select.DeleteToolbarViewHolder;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;

/**
 * Created by WINFIELD on 2016/3/20.
 */
public class SelectFastTemplateRVAdapter extends SelectRecycleViewAdapter<FastTemplate> {

    public SelectFastTemplateRVAdapter(Context context, ISelectDaoAdapter<FastTemplate> daoAdapter,DeleteToolbarViewHolder deleteToolbar) {
        super(context, daoAdapter, deleteToolbar);
    }


    @Override
    protected void deleteFromDatabase(FastTemplate fastTemplate) {
        mDaoAdapter.deleteByPrimaryKey(fastTemplate.getPkFastTemplateId());
    }

    @Override
    public void onBindViewHolder(SelectRecycleViewAdapter.ViewHolder holder, int position) {
        holder.configure(position,mData.get(position).getTemplate());
    }

    @Override
    public void onDialogConfirm(String content) {
        FastTemplate fastTemplate = new FastTemplate();
        fastTemplate.setTemplate(content);
        fastTemplate.setEventType(((FastTemplateSelectDaoAdapter) mDaoAdapter).getEventType());
        mDaoAdapter.insert(fastTemplate);
        mData.add(0,fastTemplate);
        mListitemsSelectedStatus.add(0,false);
        this.notifyDataSetChanged();
    }
}
