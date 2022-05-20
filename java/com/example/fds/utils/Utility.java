package com.example.fds.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import com.example.fds.R;

public class Utility {
    static String[] permissionsRequired = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    static String[] cameraPermissionsRequired = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final String REQUIRED_MSG = "required";
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "^[1-9][0-9]{9}$";
    // Error Messages
    private static final String EMAIL_MSG = "invalid email";
    private static final String PHONE_MSG = "invalid phone";
    public static String formatDateForDisplay(String inputDate, String inputFormat, String outputFormat) {

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            Log.e(TAG, "ParseException - dateFormat");
        }

        return outputDate;

    }


    public static boolean isInternetConnected(Context mContext) {

        /*
         * final ConnectivityManager connec = (ConnectivityManager)
         * mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
         *
         * if (connec != null&& (connec.getNetworkInfo(1).getState() ==
         * NetworkInfo.State.CONNECTED)|| (connec.getNetworkInfo(0).getState()
         * == NetworkInfo.State.CONNECTED)) { return true; } return false;
         */
//        hideKeyboard((Activity) mContext);
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            // Toast.makeText(mContext, ""+Constants.errorMsdOffline, Toast.LENGTH_SHORT).show();
            //showSnackBar(mContext, Constants.errorMsdOffline);
        }

        return false;
    }
    public static class MyBounceInterpolator implements android.view.animation.Interpolator {
        private double mAmplitude = 1;
        private double mFrequency = 10;

        public MyBounceInterpolator(double amplitude, double frequency) {
            mAmplitude = amplitude;
            mFrequency = frequency;
        }

        public float getInterpolation(float time) {
            return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) *
                    Math.cos(mFrequency * time) + 1);
        }
    }


    public static void addPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static void ToastShow(Context context){
        Toast.makeText(context,"Something went wrong !!",Toast.LENGTH_SHORT).show();
    }
    public static void datePickerChekOutDialog(Activity context, DatePickerDialog.OnDateSetListener listner,int i) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(context, listner,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        if(i!=0) {
            dialog.getDatePicker().setMinDate(System.currentTimeMillis() + 86400000);
        }
        dialog.show();
    }
    public static void timePickerDialog(Activity context, TimePickerDialog.OnTimeSetListener listner) {

        Calendar mcurrentTime = Calendar.getInstance(TimeZone.getDefault());
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        TimePickerDialog mTimePicker = new TimePickerDialog(context, listner, hour, minute, false);

        mTimePicker.show();
    }

    public static String getCurrentDate(String format) {


        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat(format);

        return df.format(c);
    }

    public static List<Address> getAddress(Context context, double latitude, double longitude) {
        List<Address> addresses = null;
        String address1 = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            address1 = addresses.get(0).getAddressLine(0);
//            address1 =addresses.get(0).getAdminArea();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return addresses;

    }
    public static String getPreferences(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", MODE_PRIVATE);
        String text = prefs.getString(key, "");
        return text;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissionsRequired[0]) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissionsRequired[1])) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Access Location permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if (required && !hasText(editText)) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }
        ;

        return true;
    }

    public static boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }
//    public static void addPreferencesUserData(Context context, String key, checkUserModel responseEmpAttendence) {
//        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", MODE_PRIVATE).edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(responseEmpAttendence);
//        editor.putString(key, json);
//        editor.commit();
//    }
//
//    public static checkUserModel getResponseUser(Context context, String key) {
//        SharedPreferences prefs = context.getSharedPreferences("Preferences_", MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = prefs.getString(key, "");
//        checkUserModel responseEmpAttendence = gson.fromJson(json, checkUserModel.class);
//        return responseEmpAttendence;
//
//    }
}
