package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatagoryFragment extends Fragment {


    public CatagoryFragment() {
        // Required empty public constructor
    }

    private GridView gridviewSubCatagory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_catagory, container, false);
        TextView gridLayoutSubCatagoryTittle = view.findViewById(R.id.grid_sub_catagory_layout_tittle);
        Button gridLayoutSubCatagoryViewAll = view.findViewById(R.id.grid_sub_catagory_layout_viewall_btn);
        gridviewSubCatagory = view.findViewById(R.id.grid_sub_catagory_layout_gridview);
        SUBCATAGORY();


        return view;
    }

    private void SUBCATAGORY() {
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

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("Category.json");
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
