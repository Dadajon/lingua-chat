package com.example.linguachat;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button nextBtn;
    private int mCurrentPage, btnPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        nextBtn = findViewById(R.id.nextBtn);
        mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick mCurrentPage : " + btnPressed);
                btnPressed++;
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);

                if (btnPressed > 2){
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    SplashActivity.this.startActivity(intent);
                }
            }
        });
    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.md_text));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);

            mCurrentPage = i;

            if (i == 0) {
                nextBtn.setEnabled(true);
                nextBtn.setText("Next");
                nextBtn.setWidth(200);
                nextBtn.setTextColor(getApplication().getResources().getColor(R.color.md_text));
                nextBtn.setBackgroundResource(R.drawable.btn_radius);
            } else if (i == mDots.length - 1) {
                nextBtn.setEnabled(true);
                nextBtn.setText("Get Started");
                nextBtn.setWidth(350);
                nextBtn.setTextColor(getApplication().getResources().getColor(R.color.white));
                nextBtn.setBackgroundResource(R.drawable.btn_get_started);
            } else {
                nextBtn.setEnabled(true);
                nextBtn.setText("Next");
                nextBtn.setWidth(200);
                nextBtn.setTextColor(getApplication().getResources().getColor(R.color.md_text));
                nextBtn.setBackgroundResource(R.drawable.btn_radius);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
