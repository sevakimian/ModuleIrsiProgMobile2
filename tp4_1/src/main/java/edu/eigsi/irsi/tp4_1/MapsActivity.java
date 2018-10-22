package edu.eigsi.irsi.tp4_1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marqueur;
    private LocationListener Listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        this.mMap.getUiSettings().setZoomControlsEnabled(true);


        LatLng eigsi = new LatLng(46.139717, -1.154308);
        this.mMap.addMarker(new MarkerOptions().position(eigsi).title("Ici c'est l'EIGSI"));

        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eigsi, 17));



        LocationManager locManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);

        LocationListener locListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                MapsActivity.this.majPosition(location);
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
        if(locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 0, locListener);
        }
        else if(locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5,0,locListener);
        }
        else {
            Toast.makeText(this, "Ca marche pas",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.switchTerrain) {
            changeTerrain(mMap);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeTerrain(GoogleMap mMap) {

        Log.v("terrain", Integer.toString(mMap.getMapType()));

        if (mMap.getMapType() == 2) {
            mMap.setMapType(1);
        } else {
            mMap.setMapType(2);
        }

    }







    public void majPosition(Location location){

        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
        if (this.marqueur !=null){
            this.marqueur.remove();
        }

        this.marqueur= this.mMap.addMarker(new MarkerOptions().position(position).title("Vous êtes ici"));
    }

}
