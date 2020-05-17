package com.example.baking;

import android.app.DownloadManager;
import android.content.Context;
import android.telephony.mbms.DownloadRequest;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.baking.MainActivity.VolleyCallBack;
public class getJson {



    public static void getData(Context context,final VolleyCallBack successMethod){
        ///Log.i(this.getClass().getName(),"requesting");
        //String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        String url ="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        RequestQueue mRequestQueue= Volley.newRequestQueue(context);




        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {


                    @Override
                    public void onResponse(final JSONArray response) {
                        //textView.setText("Response: " + response.toString());
                        //Log.i(this.getClass().getName(),response.toString());
                        successMethod.succesVolley(response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i("Error","");
                        error.fillInStackTrace();
                    }
                });

        // Access the RequestQueue through your singleton class.
        //requestQueue.getInstance(this).addToRequestQueue(jsonObjectRequest);
        mRequestQueue.add(jsonObjectRequest);




    }


}