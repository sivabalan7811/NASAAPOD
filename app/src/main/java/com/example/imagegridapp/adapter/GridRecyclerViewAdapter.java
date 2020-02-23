package com.example.imagegridapp.adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.imagegridapp.R;
import com.example.imagegridapp.model.JsonModelPojo;

import java.util.List;


public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.ViewHolder> {

    private Context context;
    List<JsonModelPojo> imagesList;
    private CallBackRequest mCallBack;


    public GridRecyclerViewAdapter(Context context, List<JsonModelPojo> list, CallBackRequest mCallBack) {

        this.context = context;
        this.imagesList = list;
        this.mCallBack = mCallBack;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_grid, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Log.d("url",""+imagesList.get(i).getUrl());
        Glide.with(context)
                .load(imagesList.get(i).getUrl())
                .apply(requestOptions)
                .into(viewHolder.album_preview);
    }


    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView album_preview;

        ViewHolder(View itemView) {
            super(itemView);
            album_preview =itemView.findViewById(R.id.album_preview);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            mCallBack.callbacktoActivity(getAdapterPosition());
        }
    }


    public interface CallBackRequest {
        void callbacktoActivity(int position);

    }

}
