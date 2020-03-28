package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class CatagoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);
        getSupportActionBar().setTitle("Catagories");

        SUBCATAGORY();

    }


    public void SUBCATAGORY() {
        TextView gridLayoutSubCatagoryTittle = findViewById(R.id.grid_sub_catagory_layout_tittle);
        Button gridLayoutSubCatagoryViewAll = findViewById(R.id.grid_sub_catagory_layout_viewall_btn);
        GridView gridviewSubCatagory = findViewById(R.id.grid_sub_catagory_layout_gridview);
        List<HorizontalProductScrollModel> grid_sub_catagory_list = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("allCatagories");


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String tiitle = String.valueOf(jo_inside.getString("categoryName"));
                String image = String.valueOf(jo_inside.getString("imageUrl"));
                grid_sub_catagory_list.add(new HorizontalProductScrollModel(image,tiitle, "", ""));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        gridviewSubCatagory.setAdapter(new GridCatagoryLayoutAdapter(grid_sub_catagory_list));
        ViewGroup.LayoutParams params = gridviewSubCatagory.getLayoutParams();
        double rowCount = grid_sub_catagory_list.size();
        rowCount = rowCount / 4;
        int rowCount_result = (int) Math.ceil(rowCount);
        params.height = (432 * rowCount_result) + 40;

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("Category.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}