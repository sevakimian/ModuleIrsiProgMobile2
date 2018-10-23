package edu.eigsi.irsi.tp5_2;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public ProgressDialog infoProgression;

    private TextView text;
    private LocationManager manager;
    private LocationListener listener;
    private Location coordonnee;
    private WebServiceCall dialogueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.lblResult);

        this.infoProgression = new ProgressDialog(this);
        this.infoProgression.setTitle("Veuillez Patienter");
        this.infoProgression.setMessage("Récupération des messages en cours");
        this.infoProgression.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //this.infoProgression.show();

        String url = "http://qcmjava.eigsi.fr/pos/write_gpspos.php";
        this.dialogueur = new WebServiceCall(this, "Sevak", url);
        geoLoc();
    }


    public void geoLoc() {
        this.manager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);

        this.listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                MainActivity.this.coordonnee = location;
                String lat = String.valueOf(MainActivity.this.coordonnee.getLatitude());
                String lng = String.valueOf(MainActivity.this.coordonnee.getLongitude());
                String alt = String.valueOf(MainActivity.this.coordonnee.getAltitude());
                String strCoordonnee = new String(lng + ";" + lat +";" + alt);
                MainActivity.this.dialogueur.write(strCoordonnee);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (this.manager.isProviderEnabled(this.manager.GPS_PROVIDER)){

            this.manager.requestLocationUpdates(this.manager.GPS_PROVIDER, 2000, 0, this.listener);
        }
        else if(this.manager.isProviderEnabled(this.manager.NETWORK_PROVIDER)){
            this.manager.requestLocationUpdates(this.manager.NETWORK_PROVIDER,2000,0,this.listener);
        }
        else{
            Toast.makeText(this, "pas de bol", Toast.LENGTH_LONG).show();
        }

    }



    public void populate(String s) {
        text.setText(s);
    }


    /*public String getLocation(){
        String lat = String.valueOf(this.coordonnee.getLatitude());
        String lng = String.valueOf(this.coordonnee.getLongitude());
        String alt = String.valueOf(this.coordonnee.getAltitude());
        String strCoordonnee = new String(lng + ";" + lat +";" + alt);

        return strCoordonnee;

    }*/

}

