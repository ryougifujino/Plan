package link.ebbinghaus.planning.custom.adapter.common.select;

import android.content.Context;
import android.view.View;

import link.ebbinghaus.planning.custom.constant.entity.FastTemplateConstant;
import link.ebbinghaus.planning.custom.db.daoadapter.impl.FastTemplateDaoAdapter;
import link.ebbinghaus.planning.custom.viewholder.common.select.DeleteToolbarViewHolder;
import link.ebbinghaus.planning.model.entity.po.FastTemplate;

/**
 * Created by WINFIELD on 2016/3/20.
 */
public class SelectFastTemplateRVAdapter extends SelectRecycleViewAdapter<FastTemplate> {
    private int mFastTemplateType;
    private FastTemplateDaoAdapter mFastTemplateDao = new FastTemplateDaoAdapter();

    public SelectFastTemplateRVAdapter(Context context, DeleteToolbarViewHolder deleteToolbar,int fastTemplateType) {
        super(context, deleteToolbar);
        this.mFastTemplateType = fastTemplateType;
        switch (mFastTemplateType){
            case FastTemplateConstant.SPEC_LEARNING_TYPE:
                mData = mFastTemplateDao.findSpecLearningAll();
                break;
            case FastTemplateConstant.SPEC_NORMAL_TYPE:
                mData = mFastTemplateDao.findSpecNormalAll();
                break;
            case FastTemplateConstant.ABSTRACT_TYPE:
                mData = mFastTemplateDao.findAbstractAll();
                break;
            default:
                throw new IllegalArgumentException("传递的快速模板类型不正确");
        }
    }

    @Override
    public void onDestroy() {
        mFastTemplateDao.closeDB();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void deleteFromDatabase(FastTemplate fastTemplate) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    @Override
    public void onBindViewHolder(SelectRecycleViewAdapter.ViewHolder holder, int position) {
        String description = mData.get(position).getTemplate();
        holder.descriptionTv.setText(description);
    }
}
