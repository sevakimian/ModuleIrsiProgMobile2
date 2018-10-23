package edu.eigsi.irsi.tp5_2;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class WebServiceCall implements Response.Listener<String>{

    private MainActivity activity;
    private String clientName;
    private RequestQueue queue;
    private String url;

    public WebServiceCall(MainActivity activity, String clientName, String url){
        super();
        this.activity=activity;
        this.clientName=clientName;
        this.url=url;
        this.queue= Volley.newRequestQueue(activity);
    }



    @Override
    public void onResponse(String s) {
        if(activity.infoProgression.isShowing()){
            activity.infoProgression.dismiss();
            Toast.makeText(activity,s, Toast.LENGTH_LONG);
        }
        activity.populate(s);
    }

    public void write(final String s){
        StringRequest myReq = new StringRequest(Request.Method.POST, url, this, null){
            @Override
            protected Map<String,String> getParams() throws com.android.volley.AuthFailureError{
                Map <String, String> params = new HashMap<String,String>();
                params.put("username", WebServiceCall.this.clientName);
                params.put("gps_pos", s);
                return params;
            };
        };

        this.queue.add(myReq);
    }
}
