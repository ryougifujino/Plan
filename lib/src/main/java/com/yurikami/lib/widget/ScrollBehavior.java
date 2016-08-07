package com.yurikami.lib.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * 修复用NestedScrollView时，CollapsingToolbars的bug
 */
public final class ScrollBehavior extends AppBarLayout.Behavior {

    private float mStartY;

    public ScrollBehavior() { }

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target) {
        //上滑
        if (mStartY - abl.getY() > 0) abl.setExpanded(false);
        super.onStopNestedScroll(coordinatorLayout, abl, target);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        mStartY = child.getY();
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
    }

}