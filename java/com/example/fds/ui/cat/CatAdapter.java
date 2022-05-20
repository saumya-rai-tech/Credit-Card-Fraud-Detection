package com.example.fds.ui.cat;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fds.R;
import com.example.fds.model.CatModel;
import com.example.fds.ui.songs.SongFragment;

import java.util.ArrayList;


public class CatAdapter extends RecyclerView.Adapter<CatAdapter.myViewHolder> {


    private ArrayList<CatModel> data;
    private Activity mContext;


    public CatAdapter(Activity mContext, ArrayList<CatModel> data) {
        this.mContext = mContext;
        this.data = data;
    }


    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.cat.setText("" + data.get(position).getTitle());
        holder.des.setText("" + data.get(position).getDescription());



    }


    @Override
    public int getItemCount() {

        return data.size();


    }


    @Override
    public myViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cat, parent, false);
        return new myViewHolder(v);
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        TextView cat,des;

        myViewHolder(View view) {
            super(view);
            des = view.findViewById(R.id.des);
            cat= view.findViewById(R.id.cat);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CatFragment.ARG_CAT_ID = data.get(getAdapterPosition()).getId();
                    v.getContext().startActivity(new Intent(v.getContext(), SongFragment.class));
                }
            });

        }


    }

}



