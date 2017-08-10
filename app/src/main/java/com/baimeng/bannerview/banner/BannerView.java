package com.baimeng.bannerview.banner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baimeng.bannerview.R;

/**
 * Created by Administrator on 2017/8/9.
 */

public class BannerView extends RelativeLayout{

    //轮播图ViewPager
    private BannerViewPager mBannerVP;
    //图片描述
    private TextView mBannerDesc;
    //点的容器
    private LinearLayout mDotContainer;
    //自定义的BannerAdapter
    private BannerAdapter mAdapter ;

    private Context mContext ;
    //选中点的drawable
    private Drawable mIndicatorFocuDrawable ;
    //未选中点的drawable
    private Drawable mIndicatorNormalDrawable ;
    //当前指示点
    private int mCurrIndicator = 0 ;

    public BannerView(Context context) {
        this(context,null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context ;
        View view = inflate(context, R.layout.ui_banner_layout, this);
        initView();
        mIndicatorFocuDrawable = new ColorDrawable(Color.RED);
        mIndicatorNormalDrawable = new ColorDrawable(Color.WHITE);
    }

    private void initView() {
        mBannerVP = (BannerViewPager)findViewById(R.id.banner_vp);
        mBannerDesc = (TextView) findViewById(R.id.banner_desc);
        mDotContainer = (LinearLayout)findViewById(R.id.dot_container);
    }

    /**
     * 设置适配器
     * @param adapter
     */
    public void setAdapter(BannerAdapter adapter){
        mAdapter = adapter ;
        mBannerVP.setAdapter(adapter) ;
        initDotIndicator();
        mBannerVP.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                //前一个选中点恢复
                DotIndicatorView last = (DotIndicatorView)mDotContainer.getChildAt(mCurrIndicator);
                last.setDrawable(mIndicatorNormalDrawable);
                //当前指示点选中
                mCurrIndicator = position % mAdapter.getCount() ;
                DotIndicatorView curr = (DotIndicatorView)mDotContainer.getChildAt(mCurrIndicator);
                curr.setDrawable(mIndicatorFocuDrawable);

                String desc = mAdapter.getItemDesc(mCurrIndicator);
                mBannerDesc.setText(desc);
            }
        });

        String desc = mAdapter.getItemDesc(mCurrIndicator);
        mBannerDesc.setText(desc);
    }

    private void initDotIndicator() {
        int count = mAdapter.getCount();
        mDotContainer.setGravity(Gravity.RIGHT);
        for (int i = 0 ; i < count ; i++){
            DotIndicatorView dot = new DotIndicatorView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(8),dip2px(8));
            dot.setLayoutParams(params);
            params.leftMargin = dip2px(8) ;
            if(i == 0){
                dot.setDrawable(mIndicatorFocuDrawable);
            }else {
                dot.setDrawable(mIndicatorNormalDrawable);
            }
            mDotContainer.addView(dot);
        }
    }

    /**
     * dip转成像素
     * @param dip
     * @return
     */
    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,
                getResources().getDisplayMetrics());
    }

    /**
     * 开始滚动
     */
    public void startRoll() {
        mBannerVP.startRoll();
    }
}
