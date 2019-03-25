package com.example.linguachat;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends AppCompatActivity {
    private SpaceTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CoordinatorLayout coordinatorLayout = findViewById(R.id.activity_main);

        //add the fragments you want to display in a List
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentB());
        fragmentList.add(new FragmentC());
        fragmentList.add(new FragmentD());
//        fragmentList.add(new FragmentE());

        final ViewPager viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.spaceTabLayout);

        tabLayout.initialize(viewPager, getSupportFragmentManager(), fragmentList, savedInstanceState);

        //Set Tab Icons
//        tabLayout.setTabOneIcon(R.drawable.ic_account_circle_white_24dp);
        tabLayout.setTabOneIcon(R.drawable.ic_mic_white_24dp);
        tabLayout.setTabTwoIcon(R.drawable.ic_widgets_white_24dp);
        tabLayout.setTabThreeIcon(R.drawable.ic_verified_user_white_24dp);
        tabLayout.setTabFourIcon(R.drawable.ic_account_circle_white_24dp);
    }

    //we need the outState to save the position
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        tabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }
}
