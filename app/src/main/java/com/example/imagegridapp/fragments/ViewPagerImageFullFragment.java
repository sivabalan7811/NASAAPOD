package com.example.imagegridapp.fragments;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.imagegridapp.R;
import com.example.imagegridapp.customViews.JustifiedTextView;

import java.util.Objects;

import static com.example.imagegridapp.activities.MainActivity.imagesList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerImageFullFragment extends Fragment {


    private static String POSTION="position";
    Toolbar toolbar;
   private ImageView image_view;
   private int position;

    public ViewPagerImageFullFragment() {
        // Required empty public constructor
    }
    public static ViewPagerImageFullFragment newInstance(int pos) {
        ViewPagerImageFullFragment fragment = new ViewPagerImageFullFragment();
        Bundle args = new Bundle();
        args.putInt(POSTION, pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position =  getArguments().getInt(POSTION);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_pager_full_image, container, false);

         initToolbarAndViews(view);

         return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    private void initToolbarAndViews(View view){
        toolbar = view. findViewById(R.id.toolbar);
        image_view=view.findViewById(R.id.backdrop);
        loadImage();

        TextView copyright =view.findViewById(R.id.copyright);
        TextView date =view.findViewById(R.id.date);
        TextView media_type =view.findViewById(R.id.media_type);
        TextView service_version=view.findViewById(R.id.service_version);
        JustifiedTextView explanation =view.findViewById(R.id.explanation);


        copyright.setText(imagesList.get(position).getCopyright());
        date.setText(imagesList.get(position).getDate());
        media_type.setText(imagesList.get(position).getMedia_type());
        service_version.setText(imagesList.get(position).getService_version());
        explanation.setText(imagesList.get(position).getExplanation());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle(imagesList.get(position).getTitle());

        }
    }

    private void loadImage(){
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Glide.with(Objects.requireNonNull(getActivity()))
                    .load(imagesList.get(position).getUrl())
                    .apply(requestOptions)
                    .into(image_view);
        }
    }
}
