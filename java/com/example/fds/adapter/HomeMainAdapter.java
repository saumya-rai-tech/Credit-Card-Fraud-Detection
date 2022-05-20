package com.example.fds.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fds.R;
import com.example.fds.model.HomeHeader;

import java.util.ArrayList;


public class HomeMainAdapter extends RecyclerView.Adapter<HomeMainAdapter.myViewHolder> {


    private ArrayList<HomeHeader> data;
    private Activity mContext;


    public HomeMainAdapter(Activity mContext, ArrayList<HomeHeader> data) {
        this.mContext = mContext;
        this.data = data;
    }


    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.title.setText("" + data.get(position).getTitle());
        HomeMappingAdapter homeMappingAdapter
                = new HomeMappingAdapter(mContext,data.get(position).getHomeHeaderMappings());
        holder.recyclerView.setAdapter(homeMappingAdapter);


    }


    @Override
    public int getItemCount() {

        return data.size();


    }


    @Override
    public myViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_header, parent, false);
        return new myViewHolder(v);
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        RecyclerView recyclerView;

        myViewHolder(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.rv);
            title= view.findViewById(R.id.title);

        }


    }

}



