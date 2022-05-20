package com.example.fds.ui.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fds.databinding.FragmentNotificationsBinding;
import com.example.fds.utils.Utility;

public class NotificationsFragment extends Fragment {


    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setData();
        return root;
    }

    private void setData() {


        binding.fname.setText(Utility.getPreferences(getActivity(),"first_name"));
        binding.lname.setText(Utility.getPreferences(getActivity(),"last_name"));
        binding.email.setText(Utility.getPreferences(getActivity(),"email"));
        binding.mobile.setText(Utility.getPreferences(getActivity(),"mobile"));
        binding.gender.setText(Utility.getPreferences(getActivity(),"gender"));
        

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}