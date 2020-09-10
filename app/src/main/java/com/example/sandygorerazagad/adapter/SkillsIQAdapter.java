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
import com.example.sandygorerazagad.model.RetroSkilliq;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class SkillsIQAdapter extends RecyclerView.Adapter
{
    private List<RetroSkilliq> RetroSkilliqs;
    private Context context;
    public SkillsIQAdapter(Context context, List<RetroSkilliq> RetroSkilliqs){
        this.context=context;
        this.RetroSkilliqs=RetroSkilliqs;
    }

    private class ItemHolder extends RecyclerView.ViewHolder{
        private TextView name, score, country;
        private ImageView badgeurl;

        private ItemHolder(View view){
            super(view);
            name=(TextView)view.findViewById(R.id.title2);
            score =(TextView)view.findViewById(R.id.description2);
            country = (TextView)view.findViewById(R.id.country2) ;
            badgeurl=view.findViewById(R.id.badgeurl2);
            //badgeurl=view.findViewById(R.id.badgeurl);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View items= LayoutInflater.from(parent.getContext()).inflate(R.layout.skillsio_row,parent,false);
        return new ItemHolder(items);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //now creating model for adapter
        RetroSkilliq RetroSkilliq=RetroSkilliqs.get(position);
        final ItemHolder itemHolder=(ItemHolder)holder;
        itemHolder.name.setText(RetroSkilliq.getName());
        itemHolder.score.setText(RetroSkilliq.getScore());
        itemHolder.country.setText(RetroSkilliq.getCountry());

        //itemHolder.badgeurl.setText(itemModel.getBadgeUrl());
        //now loading image we need to add external library which is glide i used here
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.placeholder(R.drawable.bg_grey);
        requestOptions.error(R.drawable.bg_grey);


        Glide.with(context)
                .load(RetroSkilliq.getBadgeUrl())
                .into(itemHolder.badgeurl);
    }


    @Override
    public int getItemCount() {
        return RetroSkilliqs.size();
    }


}

