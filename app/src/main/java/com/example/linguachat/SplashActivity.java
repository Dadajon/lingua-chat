package com.example.linguachat;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";

    private FirebaseAuth mAuth;

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button nextBtn, getStartedBtn;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            //user has already signed in
            Log.d("SPLASH_ACTIVITY AUTH", mAuth.getCurrentUser().getEmail());

            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(intent);
        } else {
            //If there is no user
            nextBtn = findViewById(R.id.nextBtn);
            getStartedBtn = findViewById(R.id.getStartedBtn);
            mSlideViewPager = findViewById(R.id.slideViewPager);
            mDotLayout = findViewById(R.id.dotsLayout);
            sliderAdapter = new SliderAdapter(this);
            mSlideViewPager.setAdapter(sliderAdapter);

            addDotsIndicator(0);
            mSlideViewPager.addOnPageChangeListener(viewListener);

            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick mCurrentPage : " + mCurrentPage);
                    mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                }
            });

            getStartedBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    SplashActivity.this.startActivity(intent);
                }
            });
        }
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
                nextBtn.setVisibility(View.VISIBLE);

                getStartedBtn.setEnabled(false);
                getStartedBtn.setVisibility(View.INVISIBLE);
            } else if (i == mDots.length - 1) {
                nextBtn.setEnabled(false);
                nextBtn.setVisibility(View.INVISIBLE);

                getStartedBtn.setEnabled(true);
                getStartedBtn.setText("Get Started");
                getStartedBtn.setWidth(350);
                getStartedBtn.setTextColor(getApplication().getResources().getColor(R.color.white));
                getStartedBtn.setBackgroundResource(R.drawable.btn_get_started);
                getStartedBtn.setVisibility(View.VISIBLE);
            } else {
                nextBtn.setEnabled(true);
                nextBtn.setText("Next");
                nextBtn.setWidth(200);
                nextBtn.setTextColor(getApplication().getResources().getColor(R.color.md_text));
                nextBtn.setBackgroundResource(R.drawable.btn_radius);
                nextBtn.setVisibility(View.VISIBLE);

                getStartedBtn.setEnabled(false);
                getStartedBtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
