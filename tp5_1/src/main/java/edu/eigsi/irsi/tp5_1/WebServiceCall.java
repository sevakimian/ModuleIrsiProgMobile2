package edu.eigsi.irsi.tp5_1;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;


public class WebServiceCall implements Response.Listener<String>{

    private MainActivity activity;
    private String clientName;
    private RequestQueue queue;
    private String url;

    public WebServiceCall (MainActivity activity, String clientName, String url){
        super();
        this.activity = activity;
        this.clientName = clientName;

        queue = Volley.newRequestQueue(activity);
        this.url = url;
    }

    public void write(String gpsPos){
    }


    @Override
    public void onResponse(String response) {

    }
}
