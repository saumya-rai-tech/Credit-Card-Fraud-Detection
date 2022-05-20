package com.example.fds.ui.songs;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fds.R;
import com.example.fds.databinding.FragmentDashboardBinding;
import com.example.fds.model.SongsModel;
import com.example.fds.ui.cat.CatFragment;
import com.example.fds.utils.VolleyApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SongFragment extends AppCompatActivity implements VolleyApi.ResponseListener {

    private ArrayList<SongsModel> catModels = new ArrayList<>();
    RecyclerView rv;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);
        rv = findViewById(R.id.rv);
       // VolleyApi.getInstance().getRequestData(this,this,"getSongs?cat_id="+ CatFragment.ARG_CAT_ID);
    }

    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {

    }
}