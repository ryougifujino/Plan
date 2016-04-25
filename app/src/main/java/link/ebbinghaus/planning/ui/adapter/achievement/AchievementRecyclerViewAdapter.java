package link.ebbinghaus.planning.ui.adapter.achievement;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yurikami.lib.util.IOUtils;
import com.yurikami.lib.util.Utils;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.common.constant.Assets;
import link.ebbinghaus.planning.core.model.local.po.Achievement;
import link.ebbinghaus.planning.R;

/**
 * Created by WINFIELD on 2016/4/2.
 */
public class AchievementRecyclerViewAdapter extends RecyclerView.Adapter<AchievementRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Achievement> mAchievements;

    public AchievementRecyclerViewAdapter(Context context, List<Achievement> achievements) {
        this.mContext = context;
        this.mAchievements = achievements;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.listitem_achievement, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mAchievements.get(position));
    }

    @Override
    public int getItemCount() {
        if (mAchievements != null){
            return mAchievements.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_achievement_badge) ImageView badgeIv;
        @Bind(R.id.tv_achievement_name) TextView nameTv;
        @Bind(R.id.tv_achievement_description) TextView descriptionTv;
        @Bind(R.id.tv_achievement_status) TextView statusTv;

        private int[] colorRes = {R.color.rarity_blue,R.color.legend_purple,R.color.immortality_orange};

        LruCache<String,Bitmap> badgeCache = new LruCache<String,Bitmap>(Utils.cacheMemory()){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Achievement achievement) {
            Bitmap badgeBitmap = badgeCache.get(achievement.getImageUrl());
            if ( badgeBitmap == null){
                badgeBitmap = IOUtils.getBitmapFromAssets(mContext, Assets.ACHIEVEMENT_IMAGES + File.separator + achievement.getImageUrl());
                badgeCache.put(achievement.getImageUrl(),badgeBitmap);
            }
            badgeIv.setImageBitmap(badgeBitmap);
            nameTv.setText(achievement.getName());
            nameTv.setTextColor(mContext.getResources().getColor(colorRes[achievement.getRarity() - 1]));
            descriptionTv.setText(achievement.getDescription());
            statusTv.setText(achievement.getIsAchieved() ? R.string.achievement_status_achieved : R.string.achievement_status_not_achieved);
        }
    }
}
