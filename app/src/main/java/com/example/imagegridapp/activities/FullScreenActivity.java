package com.example.imagegridapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.imagegridapp.R;
import com.example.imagegridapp.adapter.ImageScreenAdapter;

public class FullScreenActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ViewPager viewPager;
    int currentItemPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        viewPager = findViewById(R.id.viewPager);
        getIntentData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewPager();
    }

    public void getIntentData(){
      Bundle bundle =  getIntent().getExtras();
      if (bundle!=null){
          currentItemPos = bundle.getInt("position");
      }

    }

    public void setViewPager(){
        ImageScreenAdapter adapterViewPager = new ImageScreenAdapter(getSupportFragmentManager(),currentItemPos);
        viewPager.setAdapter(adapterViewPager);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(currentItemPos);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
