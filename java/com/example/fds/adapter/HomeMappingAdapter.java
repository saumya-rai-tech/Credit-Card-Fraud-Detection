package com.example.fds.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fds.R;
import com.example.fds.activity.FullScreenAudioPlayer;
import com.example.fds.model.HomeHeaderMapping;
import com.example.fds.utils.VolleyApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HomeMappingAdapter extends RecyclerView.Adapter<HomeMappingAdapter.myViewHolder> {


    private ArrayList<HomeHeaderMapping> data;
    private Activity mContext;


    public HomeMappingAdapter(Activity mContext, ArrayList<HomeHeaderMapping> data) {
        this.mContext = mContext;
        this.data = data;
    }


    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.title.setText(data.get(position).getTitle());
        Picasso.get().load(VolleyApi.IMG_URL+data.get(position).getThumbnail()).into(holder.img);
    }


    @Override
    public int getItemCount() {

        return data.size();


    }


    @Override
    public myViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_header_mapping, parent, false);
        return new myViewHolder(v);
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;

        myViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.icon);
            title = view.findViewById(R.id.title);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), FullScreenAudioPlayer.class)
                            .putExtra("song",data.get(getAdapterPosition()).getPath())
                            .putExtra("singer",data.get(getAdapterPosition()).getTitle())
                            .putExtra("audioName",data.get(getAdapterPosition()).getMovie_album_name()));
                }
            });


        }


    }

}



