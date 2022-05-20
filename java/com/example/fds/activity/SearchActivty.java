package com.example.fds.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fds.R;
import com.example.fds.model.SongsModel;
import com.example.fds.ui.songs.SongsAdapter;
import com.example.fds.utils.VolleyApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivty extends AppCompatActivity implements VolleyApi.ResponseListener {
    EditText etSearch;
    private ArrayList<SongsModel> catModels = new ArrayList<>();
    RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        etSearch = findViewById(R.id.et_search);
        rv = findViewById(R.id.rv_home);
//        TextView tv_search = findViewById(R.id.tv_search);
        etSearch.setVisibility(View.VISIBLE);
//        tv_search.setVisibility(View.GONE);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>2){
                    getSearchData(s.toString());
                }
            }
        });

    }

    private void getSearchData(String q) {
        VolleyApi.getInstance().getRequestData(this,this,"search?q="+q);
    }


    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {
        try {
            JSONObject obj1 = new JSONObject(obj);
            if(obj1.getInt("code")==200){
                JSONArray jArray = obj1.getJSONArray("data");
                //int len = jArray.length();
                catModels.clear();
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    catModels.add(new SongsModel(
                            ""+json_data.optString("thumbnail"),
                            ""+json_data.optString("id"),
                            ""+json_data.optString("title"),
                            ""+json_data.optString("description"),
                            ""+json_data.optString("path"),
                            ""+json_data.optString("movie_album_name")
                    ));

                }

                SongsAdapter catAdapter = new SongsAdapter(this,catModels);
                rv.setAdapter(catAdapter);
            }else{
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
            }

        } catch (
                JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();

        }
    }
}
