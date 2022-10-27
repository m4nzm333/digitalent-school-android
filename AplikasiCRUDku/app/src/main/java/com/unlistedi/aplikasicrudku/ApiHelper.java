package com.unlistedi.aplikasicrudku;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ApiHelper {
    Context context;
    RequestQueue requestQueue;

    public ApiHelper(Context context){
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public String getSiswa() throws InterruptedException, ExecutionException, TimeoutException {
        
    }



}
