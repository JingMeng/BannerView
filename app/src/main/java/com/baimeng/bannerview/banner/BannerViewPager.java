package com.baimeng.bannerview.banner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/8/9.
 */

public class BannerViewPager extends ViewPager {

    private BannerAdapter mAdapter ;

    private final int SCROLL_MSG = 0x0011 ;
    private int mCutDownTime = 3500 ;
    private BannerScroller mScroller;

    public BannerViewPager(Context context) {
        this(context,null);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置切换速率
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            //第一个参数，要设置的对象，第二个参数表示要设置的值
            mScroller = new BannerScroller(context);
            field.set(this,mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //每隔几秒切换图片
            setCurrentItem(getCurrentItem()+1);

            startRoll();
        }
    };

    public void setAdapter (BannerAdapter adapter){
        this.mAdapter = adapter ;
        setAdapter(new BannerViewAdapter());
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
    }

    public class BannerViewAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mAdapter.getView(position % mAdapter.getCount());
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
            object = null ;
        }
    }

    /**
     * 自动轮播
     */
    public void startRoll(){
        mHandler.removeMessages(SCROLL_MSG);
        mHandler.sendEmptyMessageDelayed(SCROLL_MSG,mCutDownTime);
    }

    /**
     * activity销毁时回调，清除消息
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeMessages(SCROLL_MSG);
        mHandler = null ;
    }

    /**
     * 设置页面切换动画时间
     * @param duration
     */
    public void setScrollerDuration(int duration){
        mScroller.setScrollerDuration(duration);
    }

}
