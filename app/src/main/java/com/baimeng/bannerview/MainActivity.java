package com.baimeng.bannerview;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.baimeng.bannerview.banner.BannerAdapter;
import com.baimeng.bannerview.banner.BannerView;
import com.baimeng.bannerview.banner.BannerViewPager;

public class MainActivity extends AppCompatActivity {

    private int [] items = new int [] {R.mipmap.banner1,R.mipmap.banner2,
                    R.mipmap.banner3,R.mipmap.banner4,R.mipmap.banner5};
    private BannerView mMyVP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyVP = (BannerView)findViewById(R.id.my_viewpager);
        mMyVP.setAdapter(new BannerAdapter() {
            @Override
            public View getView(int positon) {
                ImageView img = new ImageView( MainActivity.this);
                img.setImageResource(items[0]);
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //img.setImageResource(R.mipmap.ic_launcher);
                return img;
            }

            @Override
            public int getCount() {
                return items.length;
            }
        });
        mMyVP.startRoll();
    }
}
