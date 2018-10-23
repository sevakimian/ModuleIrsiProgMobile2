package edu.eigsi.irsi.tp5_1;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {

    public ProgressDialog infoProgression;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.lblResult);

        this.infoProgression = new ProgressDialog(this);
        this.infoProgression.setTitle("Veuillez Patienter");
        this.infoProgression.setMessage("Récupération des messages en cours");
        this.infoProgression.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.infoProgression.show();

        String url ="http://qcmjava.eigsi.fr/pos/write_gpspos.php";
        new WebServiceCall(this, "Sevak", url);


    }


    public void populate(String s) {
        text.setText(s);
    }
}
