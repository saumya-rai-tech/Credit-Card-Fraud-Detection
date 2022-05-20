package com.example.fds.activity;

import android.os.Bundle;

import com.example.fds.databinding.ActivityFinalBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



import com.example.fds.R;

public class FinalActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityFinalBinding binding;
    ImageView imcheck,imcross;
    TextView msg;
    String message;
    String flg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        message=getIntent().getExtras().get("msg").toString();
        flg=getIntent().getExtras().get("flg").toString();
        initView();
        if(flg.equalsIgnoreCase("1"))
        {
            imcheck.setVisibility(View.VISIBLE);
        }
        if(flg.equalsIgnoreCase("2"))
        {
            imcross.setVisibility(View.VISIBLE);
        }
        msg.setText(message);
    }

    void initView()
    {
      imcheck =(ImageView) binding.imview;
      imcross =(ImageView) binding.imviewcross;
      msg=(TextView) binding.text;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_final);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}