package link.ebbinghaus.planning.common.util;

import link.ebbinghaus.planning.common.App;

/**
 * Created by WINFIELD on 2016/3/23.
 */
public class DensityUtils {
    /**
     * dp转px
     * @param dpVal
     * @return
     */
    public static int dp2px(float dpVal)
    {
        return com.yurikami.lib.util.DensityUtils.dp2px(App.getContext(), dpVal);
    }

    /**
     * sp转px
     * @param spVal
     * @return
     */
    public static int sp2px(float spVal)
    {
        return com.yurikami.lib.util.DensityUtils.sp2px(App.getContext(), spVal);
    }

    /**
     * px转dp
     *
     * @param pxVal
     * @return
     */
    public static float px2dp(float pxVal)
    {
        final float scale = App.getContext().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     * @param pxVal
     * @return
     */
    public static float px2sp(float pxVal)
    {
        return com.yurikami.lib.util.DensityUtils.px2sp(App.getContext(), pxVal);
    }
}
