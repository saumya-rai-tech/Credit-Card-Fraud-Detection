package com.example.fds.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.fds.R;
import com.example.fds.adapter.HomeMainAdapter;
import com.example.fds.model.HomeHeader;
import com.example.fds.model.HomeHeaderMapping;
import com.example.fds.utils.VolleyApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements VolleyApi.ResponseListener {
    RecyclerView recyclerView;
    ArrayList<HomeHeader> homeHeaders= new ArrayList<>();
    ArrayList<HomeHeaderMapping> homeHeadersMapping= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.rv_home);
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
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    HomeHeader homeHeader = new HomeHeader();
                    homeHeader.setTitle(json_data.optString("title"));
                    JSONArray headData = json_data.getJSONArray("data");
                    for (int j=0;j<headData.length();j++){
                        JSONObject songData = headData.getJSONObject(j);
                        HomeHeaderMapping homeHeaderMapping = new HomeHeaderMapping();
                        homeHeaderMapping.setCat_id(songData.optString("cat_id"));
                        homeHeaderMapping.setTitle(songData.optString("title"));
                        homeHeaderMapping.setDescription(songData.optString("description"));
                        homeHeaderMapping.setMovie_album_name(songData.optString("movie_album_name"));
                        homeHeaderMapping.setPath(songData.optString("path"));
                        homeHeaderMapping.setThumbnail(songData.optString("thumbnail"));

                        homeHeadersMapping.add(homeHeaderMapping);

                    }
                    homeHeader.setHomeHeaderMappings(homeHeadersMapping);

                    homeHeaders.add(homeHeader);

                }

                HomeMainAdapter homeMainAdapter=new HomeMainAdapter(this,homeHeaders);
                recyclerView.setAdapter(homeMainAdapter);
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