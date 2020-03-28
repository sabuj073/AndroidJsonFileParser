package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GridCatagoryLayoutAdapter extends BaseAdapter {

    List<HorizontalProductScrollModel> horizontalCatagoryScrollModelList;

    public GridCatagoryLayoutAdapter(List<HorizontalProductScrollModel> horizontalCatagoryScrollModelList) {
        this.horizontalCatagoryScrollModelList = horizontalCatagoryScrollModelList;
    }

    @Override
    public int getCount() {
        return horizontalCatagoryScrollModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name =  horizontalCatagoryScrollModelList.get(position).getProductTittle();
                    Toast.makeText(parent.getContext(),"You Have Selected"+name,Toast.LENGTH_SHORT).show();


                }
            });


            ImageView productImage = view.findViewById(R.id.h_s_product_image);
            TextView productTittle = view.findViewById(R.id.h_s_product_tittle);
            TextView productDescription = view.findViewById(R.id.h_s_product_price);
            TextView productPrice = view.findViewById(R.id.h_s_product_price);


            Picasso.get().load(horizontalCatagoryScrollModelList.get(position).getProductImage()).into(productImage);
            productTittle.setText(horizontalCatagoryScrollModelList.get(position).getProductTittle());
            productDescription.setText(horizontalCatagoryScrollModelList.get(position).getProductDescription());
            productPrice.setText(horizontalCatagoryScrollModelList.get(position).getProductPrice());
        }else{
            view = convertView;

        }
        return view;
    }


}
