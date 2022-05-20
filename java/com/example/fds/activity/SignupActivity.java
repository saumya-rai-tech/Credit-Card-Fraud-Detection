package com.example.fds.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fds.R;
import com.example.fds.utils.Utility;
import com.example.fds.utils.VolleyApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity implements VolleyApi.ResponseListener {
    EditText fname, lname, email, password, mobile;
    Spinner spinner_mode;
    TextView tv_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        initUi();
    }

    private boolean checkValidation() {
        boolean res = true;
        if (!Utility.hasText(fname)) res = false;
        if (!Utility.hasText(lname)) res = false;
        if (!Utility.hasText(mobile)) res = false;
        if (!Utility.hasText(password)) res = false;
        if (!Utility.isEmailAddress(email, true)) res = false;
        return res;

    }

    private void initUi() {
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        spinner_mode = findViewById(R.id.spinner_mode);
        tv_submit = findViewById(R.id.tv_submit);

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    sigup();
                }
            }
        });

    }

    private void sigup() {
        HashMap<String, String> data = new HashMap<>();
        data.put("first_name", "" + fname.getText().toString().trim());
        data.put("last_name", "" + lname.getText().toString().trim());
        data.put("password", "" + password.getText().toString().trim());
        data.put("email", "" + email.getText().toString().trim());
        data.put("mobile", "" + mobile.getText().toString().trim());
        data.put("gender", "" + spinner_mode.getSelectedItem().toString());
        data.put("profile_pic", "");
        VolleyApi.getInstance().postRequest(this, this, "usr-insert", data);
    }

    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {
        try {
            JSONObject obj1 = new JSONObject(obj);
            if (obj1.getInt("code") == 200) {
                JSONObject json_data = obj1.getJSONObject("data");
                //int len = jArray.length();

                Utility.addPreferences(this, "first_name", json_data.getString("first_name"));
                Utility.addPreferences(this, "last_name", json_data.getString("last_name"));
                Utility.addPreferences(this, "password", json_data.getString("password"));
                Utility.addPreferences(this, "email", json_data.getString("email"));
                Utility.addPreferences(this, "mobile", json_data.getString("mobile"));
                Utility.addPreferences(this, "profile_pic", json_data.getString("profile_pic"));
                Utility.addPreferences(this, "gender", json_data.getString("gender"));

                Utility.addPreferences(SignupActivity.this, "isLogin", "true");

                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                finish();
            }
            if (obj1.getInt("code") == 203) {
                Toast.makeText(this, " Mobile No. Already Exist !!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong Mobile No. or Password", Toast.LENGTH_SHORT).show();
            }

        } catch (
                JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Wrong Mobile No. or Password", Toast.LENGTH_SHORT).show();

        }
    }
}