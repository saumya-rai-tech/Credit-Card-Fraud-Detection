package com.example.fds.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fds.R;
import com.example.fds.databinding.ActivityCardBinding;
import com.example.fds.utils.Utility;
import com.example.fds.utils.VolleyApi;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class CardActivity extends AppCompatActivity implements VolleyApi.ResponseListener {
    protected ActivityCardBinding acb;
    EditText card_number,name_on_card,expiry,cvv,otp;
    TextView proceed;
    TextView text;
    public static View v;
    int flg=0;
    int keycodecount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        acb=ActivityCardBinding.inflate(getLayoutInflater());
        View v=acb.getRoot();
        setContentView(v);
        initView();
        acb.cardno.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                    keycodecount=keycodecount+1;
                    //Toast.makeText(CardActivity.this, String.valueOf(keycodecount), Toast.LENGTH_SHORT).show();
                    if(keycodecount==20)
                    {
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                        String strDate = simpleDateFormat.format(date);

                        Utility.addPreferences(getApplicationContext(),"lasttime",strDate);
                        Utility.addPreferences(getApplicationContext(),"cardno",acb.cardno.getText().toString().trim());

                        Toast.makeText(CardActivity.this, "Transaction Blocked for 2 Min.", Toast.LENGTH_SHORT).show();
                        Intent obj = new Intent(CardActivity.this, FinalActivity.class);
                        obj.putExtra("msg", "Transaction Blocked for 2 Min.");
                        obj.putExtra("flg", "2");
                        finish();
                        startActivity(obj);
                    }
                }
                return false;
            }
        });
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidation()) {
                    ParsePosition pos = new ParsePosition(0);
                    ParsePosition pos1 = new ParsePosition(0);
                    Date date = Calendar.getInstance().getTime();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                    String strDateCurrent = simpleDateFormat.format(date);
                    Date current=simpleDateFormat.parse(strDateCurrent,pos);

                    String res=Utility.getPreferences(getApplicationContext(),"lasttime");
                    Date last=simpleDateFormat1.parse(res,pos1);

                    long min=CardActivity.printDifference1(last,current);
                    Log.e("nitin",String.valueOf(min));
                    String cardno=Utility.getPreferences(getApplicationContext(),"cardno");
                    Log.e("nitin",cardno+"-"+acb.cardno.getText().toString().trim());

                        if (min > 6) {
                            CardActivity.v = view;
                            String[] arr = acb.expiry.getText().toString().trim().split("/");
                            HashMap<String, String> data = new HashMap<>();
                            data.put("name_on_card", "" + acb.nameoncard.getText().toString().trim());
                            data.put("card_number", "" + acb.cardno.getText().toString().trim());
                            data.put("expiry_month", "" + arr[0]);
                            data.put("expiry_year", "" + arr[1]);
                            data.put("cvv", "" + acb.cvv.getText().toString().trim());

                            VolleyApi.getInstance().checkCard(CardActivity.this, CardActivity.this, "getCardDetails", data);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Card is blocked.Please try another.",Toast.LENGTH_LONG).show();
                        }



                }
                }
        });
    }
    public static long printDifference1(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        return elapsedMinutes;

    }
    private void initView()
    {
        card_number=(EditText) acb.cardno;
        name_on_card=(EditText) acb.nameoncard;
        expiry=(EditText) acb.expiry;
        cvv=(EditText) acb.cvv;
        proceed=(TextView) acb.proceed;
    }

    private boolean checkValidation() {
        boolean res = true;
        if (!Utility.hasText(card_number)) res = false;
        if (!Utility.hasText(name_on_card)) res = false;
        if (!Utility.hasText(expiry)) res = false;
        if (!Utility.hasText(cvv)) res = false;
        return res;

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
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = layoutInflater.inflate(R.layout.layout_otp, null);
                final PopupWindow popupWindow = new PopupWindow(
                        layout,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                popupWindow.showAtLocation(CardActivity.v, Gravity.CENTER, 0, 0);
                popupWindow.setFocusable(true);
                popupWindow.update();
                otp=(EditText) layout.findViewById(R.id.otp);
                text=(android.widget.TextView) layout.findViewById(R.id.textView);
                layout.findViewById(R.id.verifyotp).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if(flg==0) {
                                if (obj1.getString("otp").equalsIgnoreCase(otp.getText().toString().trim())) {
                                    Toast.makeText(CardActivity.this, "Mobile Otp correct.Now Enter Email Otp.", Toast.LENGTH_SHORT).show();
                                    text.setText("Enter Email OTP");
                                    otp.setText("");
                                    flg=1;
                                } else {
                                    Toast.makeText(CardActivity.this, "Incorrect Email otp.", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else if(flg==1)
                            {
                                if (obj1.getString("otpmail").equalsIgnoreCase(otp.getText().toString().trim())) {
                                    Toast.makeText(CardActivity.this, "Email Otp correct.", Toast.LENGTH_SHORT).show();
                                    Intent obj = new Intent(CardActivity.this, FinalActivity.class);
                                    obj.putExtra("msg", "Transaction done successfully");
                                    obj.putExtra("flg", "1");
                                    finish();
                                    startActivity(obj);
                                } else {
                                    Toast.makeText(CardActivity.this, "Incorrect Email otp.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                //Toast.makeText(this, "Card Approved Successfully.", Toast.LENGTH_SHORT).show();
            }
            if (obj1.getInt("code") == 203) {
                Toast.makeText(this, "Card details not matched.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Somethng went wrong.", Toast.LENGTH_SHORT).show();
            }

        } catch (
                JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wen wrong.", Toast.LENGTH_SHORT).show();

        }
    }
}