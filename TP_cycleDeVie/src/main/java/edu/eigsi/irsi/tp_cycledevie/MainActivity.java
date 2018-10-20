package edu.eigsi.irsi.tp_cycledevie;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Toast.makeText(this, "création de l'activité",Toast.LENGTH_LONG).show();
        Log.i("TP2","création activité");
    }

    @Override
    public void onDestroy(){

        Toast.makeText(this, "destruction de l'activité",Toast.LENGTH_LONG).show();
        Log.i("TP2" , "Destruction de l'activité");
        super.onDestroy();
    }

    @Override
    public void onStart(){

        Toast.makeText(this, "Démarrage de l'activité",Toast.LENGTH_LONG).show();
        super.onStart();
    }

    @Override
    public void onStop(){

        Toast.makeText(this, "Arrêt de l'activité",Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    public void onRestart(){

        Toast.makeText(this, "Redémarrage de l'activité",Toast.LENGTH_LONG).show();
        super.onRestart();
    }

    @Override
    public void onPause(){

        Toast.makeText(this, "pause de l'activité",Toast.LENGTH_LONG).show();
        super.onPause();
    }

    @Override
    public void onResume(){

        Toast.makeText(this, "Reprise de l'activité",Toast.LENGTH_LONG).show();
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        Toast.makeText(this, "On save instance state de l'activité",Toast.LENGTH_LONG).show();
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){

        Toast.makeText(this, "onRestoreInstanceState de l'activité",Toast.LENGTH_LONG).show();
        super.onRestoreInstanceState(savedInstanceState);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
