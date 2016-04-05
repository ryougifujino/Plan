package link.ebbinghaus.planning.ui.adapter.extension;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.common.util.CommonUtils;
import link.ebbinghaus.planning.core.model.vo.extension.douban.book.Book;
import link.ebbinghaus.planning.core.model.vo.extension.douban.book.Result;

/**
 * Created by WINFIELD on 2016/4/4.
 */
public class ReadRecyclerViewAdapter extends RecyclerView.Adapter<ReadRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private Result mBooks;
    private int mLastPosition;
    private Callback mCallback;

    public ReadRecyclerViewAdapter(Context context, Result books) {
        this.mContext = context;
        this.mBooks = books;
        //自动注册
        if (context instanceof Callback){
            mCallback = (Callback) context;
        }
    }

    /**
     * 全刷新
     * @param books list显示的数据
     */
    public void refresh(Result books){
        mBooks = books;
        setLastPosition(mBooks.getCount());
        this.notifyDataSetChanged();
    }

    /**
     * 添加式刷新,将会保留原有数据
     * @param newBooks 新增的数据
     */
    public void appendRefresh(Result newBooks){
        mBooks.setCount(mBooks.getCount() + newBooks.getCount());
        mBooks.getBooks().addAll(newBooks.getBooks());
        setLastPosition(mBooks.getCount());
        //prompt
        if (newBooks.getCount() == 0){
            CommonUtils.showLongToast(R.string.extension_read_load_prompt);
        }
        this.notifyDataSetChanged();
    }

    private void setLastPosition(int count){
        mLastPosition = count > 0 ? count - 1 : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.listitem_extension_read, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mBooks.getBooks().get(position));
        if (position == mLastPosition && mCallback!= null){
            mCallback.requestNewData(mLastPosition + 1);
        }
    }


    @Override
    public int getItemCount() {
        if (mBooks != null) {
            return mBooks.getCount();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_extension_read_cover) ImageView coverIv;
        @Bind(R.id.tv_extension_read_title) TextView titleTv;
        @Bind(R.id.rb_extension_read_ranting) RatingBar ratingRb;
        @Bind(R.id.tv_extension_read_ranting) TextView ratingTv;
        @Bind(R.id.tv_extension_read_publish_info) TextView publishInfoTv;
        @Bind(R.id.rl_extension_read_listitem) RelativeLayout listitemRl;
        
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(Book book) {
            Glide.with(mContext)
                    .load(book.getImage())
                    .crossFade()
                    .into(coverIv);
            titleTv.setText(book.getTitle());
            ratingRb.setRating( (Float.parseFloat(book.getRating().getAverage()) / 10.0F * 5.0F) );
            ratingTv.setText(book.getRating().getAverage()); // TODO: 2016/4/4 可能没有评分
            publishInfoTv.setText((book.getAuthor().size() != 0 ? book.getAuthor().get(0) : "没有作者") + "/" + book.getPublisher() + "/" + book.getPubdate());

        }
    }

    /**
     * 可自动注册,也可使用这个方法手动注册
     * @param c 回调
     */
    public void setCallback(Callback c){
        mCallback = c;
    }

    public interface Callback{
        /**
         * 请求新数据的回调函数
         * @param start 新数据的起始offset
         */
        void requestNewData(int start);
    }

}
