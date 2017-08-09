package com.baimeng.bannerview.banner;

import android.view.View;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2017/8/9.
 */

public interface BannerAdapter {
    public abstract View getView(int positon) ;

    public abstract int getCount();
}
