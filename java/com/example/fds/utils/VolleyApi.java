package com.example.fds.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class VolleyApi {


    //    public static final String BASE_URL = "https://cd0799a4.ngrok.io/index.php/";
    public static final String BASE_URL = "https://www.shopzone247.com/btech/fraud_detection/ws/index.php/";
    public static final String IMG_URL = "https://www.shopzone247.com/btech/fraud_detection/ws/application/photo/";
    public static final String SONG_URL = "https://www.shopzone247.com/btech/fraud_detection/ws/application/songs/";
    private static VolleyApi volleyApi = null;
    private ProgressDialog mProgressDialog;
    private ResponseListener mlistener_response;


    public static VolleyApi getInstance() {

        if (volleyApi != null)
            return volleyApi;
        else
            return new VolleyApi();
    }

    public void loginUser(final Activity activity, final ResponseListener _mlistener_response, final String contact, final String password) {
        this.mlistener_response = _mlistener_response;
        showInternetDialog(activity);
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL + "login?mobile=" + contact + "&password=" + password,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString() + "   ****  " + error.getCause() + " ***** " + error.getMessage());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    return params;
                }


            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }

    public void checkCard(final Activity activity, final ResponseListener _mlistener_response, String url, HashMap<String, String> data) {
        this.mlistener_response = _mlistener_response;
        showInternetDialog(activity);
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString() + "   ****  " + error.getCause() + " ***** " + error.getMessage());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    return data;
                }


            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }


    public void getRequestData(final Activity activity, final ResponseListener _mlistener_response,String url) {
        this.mlistener_response = _mlistener_response;
        showInternetDialog(activity);
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL + url ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString() + "   ****  " + error.getCause() + " ***** " + error.getMessage());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    return params;
                }


            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }


    public void postRequest(final Activity activity, final ResponseListener _mlistener_response, String url, HashMap<String, String> data) {
        this.mlistener_response = _mlistener_response;
        showInternetDialog(activity);
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString() + "   ****  " + error.getCause() + " ***** " + error.getMessage());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    return data;
                }


            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            mProgressDialog.dismiss();
            e.printStackTrace();
        }

    }


    public interface ResponseListener {

        void _onResponseError(Throwable e);

        void _onNext(String obj);

    }

    private void showInternetDialog(final Activity activity) {

        if (!Utility.isInternetConnected(activity)) {
            new AlertDialog.Builder(activity).setMessage("Please on internet connection.").setTitle("No Internet Connection !!").setCancelable(true).
                    setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                            if (Utility.isInternetConnected(activity)) {
                                dialog.dismiss();
                            }
                        }
                    }).show();

        }


    }
}


