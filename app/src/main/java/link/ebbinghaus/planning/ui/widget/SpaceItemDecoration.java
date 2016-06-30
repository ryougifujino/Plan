package link.ebbinghaus.planning.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by WINFIELD on 2016/6/30.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public SpaceItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (parent.getPaddingLeft() != mSpace) {
            parent.setPadding(mSpace, mSpace, mSpace, mSpace);
            parent.setClipToPadding(false);
        }

        outRect.top = mSpace;
        outRect.bottom = mSpace;
        outRect.left = mSpace;
        outRect.right = mSpace;
    }

}
