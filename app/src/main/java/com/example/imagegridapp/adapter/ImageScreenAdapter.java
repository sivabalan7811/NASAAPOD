package com.example.imagegridapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.imagegridapp.fragments.ViewPagerImageFullFragment;

import static com.example.imagegridapp.activities.MainActivity.imagesList;

public class ImageScreenAdapter extends FragmentStatePagerAdapter {

    private int position;

    public ImageScreenAdapter(FragmentManager fragmentManager,int position) {
        super(fragmentManager);
        this.position=position;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return imagesList.size();
    }

    // Returns the fragment to display for that page
    @NonNull
    @Override
    public Fragment getItem(int position) {
        //Log.e("position", "position>>" + position);
        return ViewPagerImageFullFragment.newInstance(position);
    }
}
