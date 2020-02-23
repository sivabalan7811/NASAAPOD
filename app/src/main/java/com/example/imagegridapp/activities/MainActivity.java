package com.example.imagegridapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.example.imagegridapp.R;
import com.example.imagegridapp.Utils.AnimationItem;
import com.example.imagegridapp.Utils.Measure;
import com.example.imagegridapp.adapter.GridRecyclerViewAdapter;
import com.example.imagegridapp.customViews.GridSpacingItemDecoration;
import com.example.imagegridapp.model.JsonModelPojo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.imagegridapp.Utils.CommonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.imagegridapp.Utils.CommonUtils.runLayoutAnimation;

public class MainActivity extends AppCompatActivity  implements GridRecyclerViewAdapter.CallBackRequest {

    public static List<JsonModelPojo> imagesList;
    private GridRecyclerViewAdapter gridRecyclerViewAdapter;
    private  GridSpacingItemDecoration gridSpacingItemDecoration;
    private    RecyclerView recycleViewGrid;
    private AnimationItem mSelectedItem;
    private TextView no_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVIews();
        initRecyclerView();
        getJsonDataFromJsonFile();
    }


    public void initVIews() {
        recycleViewGrid = findViewById(R.id.recycleViewGrid);
        no_information = findViewById(R.id.no_information);
    }

    public void initRecyclerView() {

        gridSpacingItemDecoration = new GridSpacingItemDecoration(2, Measure.pxToDp(3,this), true);
        recycleViewGrid.addItemDecoration(gridSpacingItemDecoration);
        AnimationItem[] mAnimationItems = getAnimationItems();
        mSelectedItem = mAnimationItems[0];

        recycleViewGrid.setLayoutManager(new GridLayoutManager(this, 2));
        recycleViewGrid.setHasFixedSize(true);

    }

    private void getJsonDataFromJsonFileUsingGson() {
        String jsonFileString = CommonUtils.getJsonFromAssets(getApplicationContext(), "data.json");
        Log.d("stringjson", "" + jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<JsonModelPojo>>() {
        }.getType();

        imagesList = gson.fromJson(jsonFileString, listUserType);


    }

    private void getJsonDataFromJsonFile() {

        String jsonFileString = CommonUtils.getJsonFromAssets(getApplicationContext(), "data.json");
        imagesList = new ArrayList<>();
        if (jsonFileString != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonFileString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    JsonModelPojo jsonModelPojo = new JsonModelPojo();

                    if (jsonObject.has("copyright")) {
                        jsonModelPojo.setCopyright(jsonObject.getString("copyright"));
                    } else {
                        jsonModelPojo.setCopyright("-");
                    }

                    if (jsonObject.has("date")) {
                        jsonModelPojo.setDate(jsonObject.getString("date"));
                    }else {
                        jsonModelPojo.setDate("-");
                    }

                    if (jsonObject.has("explanation")) {
                        jsonModelPojo.setExplanation(jsonObject.getString("explanation"));
                    }else {
                        jsonModelPojo.setExplanation("-");
                    }
                    if (jsonObject.has("hdurl")) {
                        jsonModelPojo.setHdurl(jsonObject.getString("hdurl"));
                    }else {
                        jsonModelPojo.setHdurl("-");
                    }

                    if (jsonObject.has("media_type")) {
                        jsonModelPojo.setMedia_type(jsonObject.getString("media_type"));
                    }else {
                        jsonModelPojo.setMedia_type("-");
                    }

                    if (jsonObject.has("service_version")) {
                        jsonModelPojo.setService_version(jsonObject.getString("service_version"));
                    }else {
                        jsonModelPojo.setService_version("-");
                    }

                    if (jsonObject.has("title")) {
                        jsonModelPojo.setTitle(jsonObject.getString("title"));
                    }else {
                        jsonModelPojo.setTitle("-");
                    }
                    if (jsonObject.has("url")) {
                        jsonModelPojo.setUrl(jsonObject.getString("url"));
                    }else {
                        jsonModelPojo.setUrl("-");
                    }
                    imagesList.add(jsonModelPojo);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        setAdapter();
    /*  Log.d("size",""+imagesList.size());
        for (int i = 0; i < imagesList.size(); i++) {
            Log.d("Title ",""+imagesList.get(i).getTitle());
        }*/

    }

    private void setAdapter() {

        if (imagesList!=null&&imagesList.size()>0){
            recycleViewGrid.setVisibility(View.VISIBLE);
            no_information.setVisibility(View.GONE);
            gridRecyclerViewAdapter = new GridRecyclerViewAdapter(this,imagesList,this);
            recycleViewGrid.setAdapter(gridRecyclerViewAdapter);
            runLayoutAnimation(recycleViewGrid, mSelectedItem);
        } else {
            recycleViewGrid.setVisibility(View.GONE);
            no_information.setVisibility(View.VISIBLE);
        }


    }
    @Override
    public void callbacktoActivity(int position) {

        Intent intent = new Intent(MainActivity.this,FullScreenActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public static AnimationItem[] getAnimationItems() {
        return new AnimationItem[]{
                new AnimationItem("Slide from right", R.anim.layout_animation_fall_down),
                new AnimationItem("Slide from right", R.anim.layout_animation_from_right),
                new AnimationItem("Slide from bottom", R.anim.layout_animation_from_bottom)
        };
    }
}
