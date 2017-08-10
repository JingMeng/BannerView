package com.baimeng.bannerview.banner;

import android.view.View;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2017/8/9.
 */

public abstract class BannerAdapter {
    public abstract View getView(int positon , View convertview) ;

    public abstract int getCount();

    public String getItemDesc(int postion){
        return "" ;
    };
}
