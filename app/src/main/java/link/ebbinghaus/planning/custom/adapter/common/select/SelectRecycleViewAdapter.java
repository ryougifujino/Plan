package link.ebbinghaus.planning.custom.adapter.common.select;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yurikami.lib.base.BaseActivity;
import com.yurikami.lib.util.LogUtils;
import com.yurikami.lib.widget.SingleInputDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.custom.viewholder.common.select.DeleteToolbarViewHolder;
import link.ebbinhaus.planning.R;

/**
 * <pre>
 *
 * 子类需要实现的方法
 * protected abstract void deleteFromDatabase(T t);
 *
 * 注意子类覆盖时要用SelectRecycleViewAdapter.ViewHolder,不然编译无法通过
 * \@Override
 * public  void onBindViewHolder(SelectRecycleViewAdapter.ViewHolder holder, int position) {
 *     holder.configure(position, mData.get(position).获取描述的方法);
 *  }
 *
 * </pre>
 * @param <T>
 */
public abstract class SelectRecycleViewAdapter<T extends Parcelable> extends RecyclerView.Adapter<SelectRecycleViewAdapter.ViewHolder>
        implements BaseActivity.OnActivityStopListener,View.OnClickListener,
        View.OnLongClickListener, SingleInputDialog.OnDialogConfirmListener {

    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected OnItemClickListener mOnItemClickListener;
    protected DeleteToolbarViewHolder mDeleteToolbar;
    protected List<T> mData;
    protected ISelectDaoAdapter<T> mDao;

    /** 用于记录每个listitem的选中情况 */
    protected List<Boolean> mListitemsSelectedStatus = new ArrayList<>();
    /** 被选中的listitem数量 */
    protected int mSelectedCount = 0;
    /** 是否进入删除状态 */
    protected boolean mDeleteStatus = false;
    /** 是否选中了所有 */
    protected boolean mIsSelectedAll = false;

    public SelectRecycleViewAdapter(Context context, ISelectDaoAdapter<T> dao,DeleteToolbarViewHolder deleteToolbar) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(this.mContext);
        this.mDao = dao;
        this.mDeleteToolbar = deleteToolbar;

        this.mData = mDao.selectAll();
        Collections.reverse(mData);
        initListitemsSelectedStatus();

        mDeleteToolbar.arrowBackIv.setOnClickListener(this);
        mDeleteToolbar.selectAllToggleTv.setOnClickListener(this);
        mDeleteToolbar.deleteTv.setOnClickListener(this);
    }


    @Override
    public SelectRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.listitem_common_select, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public int getItemCount() {
        if(mData != null){
            return mData.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.rl_common_select_listitem) RelativeLayout listitemRl;
        @Bind(R.id.tv_common_select_description) TextView descriptionTv;
        @Bind(R.id.iv_common_select_selected_icon) ImageView selectedIconIv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * 给控件添加描述,控制listitem的选中状态的显示,<br>
         * 给listitem设置监听器
         * @param position 位置
         * @param description 描述信息
         */
        public void configure(int position, String description){
            boolean isSelected = mListitemsSelectedStatus.get(position);
            if(isSelected){
                selectedIconIv.setVisibility(View.VISIBLE);
            }else {
                selectedIconIv.setVisibility(View.GONE);
            }
            descriptionTv.setText(description);
            listitemRl.setOnClickListener(SelectRecycleViewAdapter.this);
            listitemRl.setOnLongClickListener(SelectRecycleViewAdapter.this);
            listitemRl.setTag(position);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_common_select_listitem:
                int position = (int) v.getTag();
                if(mDeleteStatus){
                    deleteStatusClickLogic(position);
                }else {
                    mOnItemClickListener.onItemClick(mData.get(position));
                }
                break;
            case R.id.iv_common_select_delete_toolbar_arrow_back:
                onArrowBackClickLogic();
                break;
            case R.id.tv_common_select_delete_toolbar_select_all_toggle:
                onSelectAllToggleClickLogic();
                break;
            case R.id.tv_common_select_delete_toolbar_delete:
                onDeleteClickLogic();
                break;
        }
        refreshDeleteToolbar();
        this.notifyDataSetChanged();    //TODO:增加动画效果,改成this.notifyItemRemoved();
    }

    @Override
    public boolean onLongClick(View v) {
        LogUtils.d("onLongClick","function");
        int position = (int) v.getTag();
        if (!mDeleteStatus){
            mDeleteStatus = true;
            mListitemsSelectedStatus.set(position, true);
            mSelectedCount = 1;
            mIsSelectedAll = mData.size() == 1;
        }else {
            deleteStatusClickLogic(position);
        }

        refreshDeleteToolbar();
        this.notifyDataSetChanged();
        return true;
    }

    ///

    /** 从数据库中删除 */
    protected abstract void deleteFromDatabase(T t);

    /** 关闭数据库 */
    @Override
    public void onStop() {
        mDao.closeDB();
    }

    //以下为辅助方法

    /**
     * 辅助方法,初始化selectListitems
     * !使用此方法前,必须先初始化mData
     */
    protected void initListitemsSelectedStatus(){
        if (mData == null){
            throw new IllegalStateException("mData还没有初始化");
        }
        int size = mData.size();
        for (int i = 0; i < size; i++) {
            mListitemsSelectedStatus.add(false);
        }
    }
    /**
     * 辅助方法,设置selectListitems为给定状态
     * @param status 给所有Listitem设置的状态
     */
    private void setListitemsSelectedStatus(boolean status){
        int size = mListitemsSelectedStatus.size();
        for (int i = 0; i < size; i++) {
            mListitemsSelectedStatus.set(i, status);
        }
    }

    /**
     * 辅助方法,更新数据后调用此方法刷新delete toolbar上的控件显示数据
     */
    private void refreshDeleteToolbar() {
        mDeleteToolbar.selectInfolTv.setText(String.format(mContext.getString(R.string.common_select_delete_toolbar_select_info), mSelectedCount));
        if(mIsSelectedAll){
            mDeleteToolbar.selectAllToggleTv.setText(mContext.getString(R.string.common_select_none));
        }else {
            mDeleteToolbar.selectAllToggleTv.setText(mContext.getString(R.string.common_select_all));
        }
        if(mSelectedCount == 0){
            mDeleteToolbar.toolbarRl.setVisibility(View.GONE);
        }
        if(mSelectedCount == 1){
            mDeleteToolbar.toolbarRl.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 当点击删除工具条上的全选/全不选的toggle时对参数的设置逻辑
     */
    private void onSelectAllToggleClickLogic(){
        if(mIsSelectedAll){
            mSelectedCount = 0;
            mDeleteStatus = false;
        }else {
            mSelectedCount = mData.size();
        }
        setListitemsSelectedStatus(mIsSelectedAll = !mIsSelectedAll);
    }

    /**
     * 当点击删除工具条上的返回箭头时对参数的设置逻辑
     */
    private void onArrowBackClickLogic(){
        setListitemsSelectedStatus(false);
        mSelectedCount = 0;
        mDeleteStatus = false;
        mIsSelectedAll = false;
    }

    /**
     * 当点击删除工具条上的删除按钮时执行的逻辑
     */
    private void onDeleteClickLogic(){
        for (int pos = 0; pos < mListitemsSelectedStatus.size(); pos++) {
            boolean isToBeDeleted = mListitemsSelectedStatus.get(pos);
            if (isToBeDeleted){
                T t = mData.get(pos);
                mData.remove(pos);
                mListitemsSelectedStatus.remove(pos);
                pos--;
                if(--mSelectedCount == 0){
                    mDeleteStatus = false;
                }
                //从数据库删除
                deleteFromDatabase(t);
            }
        }
    }

    //以下为本类的监听器

    /**
     * 处于删除状态时,点击和长按对参数设置的逻辑
     * @param position 当前点击或长按时listitem的位置
     */
    private void deleteStatusClickLogic(int position){
        boolean isSelected = mListitemsSelectedStatus.get(position);
        mListitemsSelectedStatus.set(position, !isSelected);
        if(isSelected){
            if(--mSelectedCount == 0){
                mDeleteStatus = false;
            }
            mIsSelectedAll = false;
        }else {
            if (++mSelectedCount == mData.size()){
                mIsSelectedAll = true;
            }

        }
    }

    /**
     * 设置listitem的监听器
     * @param l 目标监听器
     */
    public void setOnItemClickListener(OnItemClickListener l){
        mOnItemClickListener = l;
    }

    /**
     * RecyclerView里listitem的点击事件监听器
     */
    public interface OnItemClickListener{
        /**
         * 点击后的回调函数,作用是用来向Intent发送者传递数据
         * @param result 传递的数据
         */
        void onItemClick(Parcelable result);
    }
}
