package com.example.sandygorerazagad.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sandygorerazagad.R;
import com.example.sandygorerazagad.model.ItemModel;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter
{
    private List<ItemModel> itemModels;
    private Context context;
    public ItemAdapter(Context context, List<ItemModel> itemModels){
        this.context=context;
        this.itemModels=itemModels;
    }

    private class ItemHolder extends RecyclerView.ViewHolder{
        private TextView name, hours, country;
        private ImageView badgeurl;

        private ItemHolder(View view){
            super(view);
            name=(TextView)view.findViewById(R.id.title);
            hours =(TextView)view.findViewById(R.id.description);
            country = (TextView)view.findViewById(R.id.country) ;
           badgeurl=view.findViewById(R.id.badgeurl);
            //badgeurl=view.findViewById(R.id.badgeurl);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View items= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new ItemHolder(items);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //now creating model for adapter
        ItemModel itemModel=itemModels.get(position);
        final ItemHolder itemHolder=(ItemHolder)holder;
        itemHolder.name.setText(itemModel.getName());
        itemHolder.hours.setText(itemModel.getHours());
        itemHolder.country.setText(itemModel.getCountry());

       //itemHolder.badgeurl.setText(itemModel.getBadgeUrl());
        //now loading image we need to add external library which is glide i used here
        RequestOptions requestOptions=new RequestOptions();
       requestOptions.placeholder(R.drawable.bg_grey);
       requestOptions.error(R.drawable.bg_grey);


       Glide.with(context)
               .load(itemModel.getBadgeUrl())
               .into(itemHolder.badgeurl);
    }


    @Override
    public int getItemCount() {
        return itemModels.size();
    }


}
