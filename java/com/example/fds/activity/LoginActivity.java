package com.example.fds.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fds.BottomNavigation;
import com.example.fds.R;
import com.example.fds.utils.Utility;
import com.example.fds.utils.VolleyApi;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, VolleyApi.ResponseListener {

    private TextInputEditText mIdEdtEmail;
    private TextInputEditText mEdtPsw;

    private TextView signup,mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }


    private void initView() {
        mIdEdtEmail = (TextInputEditText) findViewById(R.id.login_etEmail);
        mEdtPsw = (TextInputEditText) findViewById(R.id.login_etPassword);
        signup = (TextView) findViewById(R.id.signup);
        mBtnLogin = (TextView) findViewById(R.id.login_tvLogin);
        mBtnLogin.setOnClickListener(this);
        signup.setOnClickListener(this);
        if(Utility.getPreferences(this,"isLogin").equalsIgnoreCase("true")){
            startActivity(new Intent(LoginActivity.this, BottomNavigation.class));
            finish();
        }
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_tvLogin:
                if (checkValidation()) {
                    userLogin();
                }
                break;

            case R.id.signup:
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                break;
            default:
                break;
        }
    }

    private void userLogin() {
        VolleyApi.getInstance().loginUser(this, this, mIdEdtEmail.getText().toString().trim(), mEdtPsw.getText().toString().trim());
    }

    private boolean checkValidation() {
        boolean res = true;
        if (!Utility.hasText(mIdEdtEmail)) res = false;
        if (!Utility.hasText(mEdtPsw)) res = false;
        return res;

    }


    @Override
    public void _onResponseError(Throwable e) {
        e.printStackTrace();
        Utility.ToastShow(this);
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

                    System.out.println("sdfsdfd :::::::: " + json_data.toString());


                    Utility.addPreferences(this, "first_name", json_data.getString("first_name"));
                    Utility.addPreferences(this, "last_name", json_data.getString("last_name"));
                    Utility.addPreferences(this, "password", json_data.getString("password"));
                    Utility.addPreferences(this, "email", json_data.getString("email"));
                    Utility.addPreferences(this, "mobile", json_data.getString("mobile"));
                    Utility.addPreferences(this, "profile_pic", json_data.getString("profile_pic"));
                    Utility.addPreferences(this, "gender", json_data.getString("gender"));

                    Utility.addPreferences(LoginActivity.this, "isLogin", "true");

                }
                startActivity(new Intent(LoginActivity.this, BottomNavigation.class));
                finish();
            }else{
                Toast.makeText(this, "Wrong Mobile No. or Password", Toast.LENGTH_SHORT).show();
            }

        } catch (
                JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Wrong Mobile No. or Password", Toast.LENGTH_SHORT).show();

        }

    }

}
