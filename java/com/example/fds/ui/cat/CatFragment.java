package com.example.fds.ui.cat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fds.databinding.FragmentDashboardBinding;
import com.example.fds.model.CatModel;
import com.example.fds.utils.VolleyApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CatFragment extends Fragment implements VolleyApi.ResponseListener {

    private FragmentDashboardBinding binding;
    private ArrayList<CatModel> catModels = new ArrayList<>();
    public static String ARG_CAT_ID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        VolleyApi.getInstance().getRequestData(getActivity(),this,"getCats");
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
        try {
            JSONObject obj1 = new JSONObject(obj);
            if(obj1.getInt("code")==200){
                JSONArray jArray = obj1.getJSONArray("data");
                //int len = jArray.length();
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    catModels.add(new CatModel(
                            ""+json_data.optString("thumbnail"),
                            ""+json_data.optString("id"),
                            ""+json_data.optString("title"),
                            ""+json_data.optString("description")
                    ));

                }

               CatAdapter catAdapter = new CatAdapter(getActivity(),catModels);
                binding.rv.setAdapter(catAdapter);
            }else{
                Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_SHORT).show();
            }

        } catch (
                JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_SHORT).show();

        }
    }
}