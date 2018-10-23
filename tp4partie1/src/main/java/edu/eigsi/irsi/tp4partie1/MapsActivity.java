package edu.eigsi.irsi.tp4partie1;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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
        // Add a marker in Sydney and move the camera
        LatLng eigsi = new LatLng(46.139695, -1.154298);
        mMap.addMarker(new MarkerOptions().position(eigsi).title("Ici c'est l'EIGSI"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(eigsi));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.changeTerrain) {
            changeTerrain(this.mMap);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //METHODES SERVICES
    public void changeTerrain(GoogleMap mMap) {

        Log.v("terrain", Integer.toString(mMap.getMapType()));

        if (mMap.getMapType() == 2) {
            mMap.setMapType(1);
        } else {
            mMap.setMapType(2);
        }

    }

}
