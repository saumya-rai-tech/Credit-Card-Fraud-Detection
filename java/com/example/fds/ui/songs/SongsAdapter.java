package com.example.fds.ui.songs;

import static com.example.fds.utils.VolleyApi.IMG_URL;

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
import com.example.fds.model.SongsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.myViewHolder> {


    private ArrayList<SongsModel> data;
    private Activity mContext;


    public SongsAdapter(Activity mContext, ArrayList<SongsModel> data) {
        this.mContext = mContext;
        this.data = data;
    }


    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.cat.setText("" + data.get(position).getTitle());
        holder.des.setText("" + data.get(position).getDescription());
        Picasso.get().load(IMG_URL+data.get(position).getThumbnail()).into(holder.img);



    }


    @Override
    public int getItemCount() {

        return data.size();


    }


    @Override
    public myViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_songs, parent, false);
        return new myViewHolder(v);
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        TextView cat,des;
        ImageView img;

        myViewHolder(View view) {
            super(view);
            des = view.findViewById(R.id.des);
            img = view.findViewById(R.id.img);
            cat= view.findViewById(R.id.cat);
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



