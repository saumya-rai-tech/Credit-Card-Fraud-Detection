package com.example.fds.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.fds.activity.CardActivity;
import com.example.fds.activity.SearchActivty;
import com.example.fds.adapter.HomeMainAdapter;
import com.example.fds.databinding.FragmentHomeBinding;
import com.example.fds.model.HomeHeader;
import com.example.fds.model.HomeHeaderMapping;
import com.example.fds.ui.cat.CatFragment;
import com.example.fds.ui.songs.SongFragment;
import com.example.fds.utils.VolleyApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements VolleyApi.ResponseListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    ArrayList<HomeHeader> homeHeaders = new ArrayList<>();
    ArrayList<HomeHeaderMapping> homeHeadersMapping = new ArrayList<>();
    ArrayList<SlideModel> imageList = new ArrayList<>();
    int flagApi = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity().getApplicationContext(),"hello m working",Toast.LENGTH_LONG).show();
               Intent intent = new Intent(getActivity(), CardActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {

    }
}