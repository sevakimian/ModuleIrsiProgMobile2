package edu.eigsi.irsi.tp3_convertisseur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private double taux = 1.17;
    private EditText edtEuros;
    private EditText edtDollars;
    private EditText edtTaux;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.edtEuros = (EditText) findViewById(R.id.editEuro);
        this.edtDollars = (EditText) findViewById(R.id.editDollar);
        this.edtTaux = (EditText) findViewById(R.id.editTaux);
        this.edtTaux.setText(Double.toString(this.taux));


        edtEuros.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                 //MainActivity.this.convertirEnDollars();

                String strEuro=MainActivity.this.edtEuros.getText().toString();
                String strTaux=MainActivity.this.edtTaux.getText().toString();
                double dTaux;


                if (strTaux != null){
                    dTaux = Double.valueOf(strTaux);
                    if (strEuro.length() !=0 && dTaux !=0){
                        if(strEuro.contains(",")){
                            strEuro.replace(',' , '.');
                        }

                        double dEuro = Double.valueOf(strEuro);
                        double dDollars = dEuro* dTaux;
                        //MainActivity.this.edtDollars.setText(String.valueOf(dDollars));
                        MainActivity.this.edtDollars.setText(String.format("%.2f",dDollars));

                    }

                }
                return false;
            }
        });

        edtDollars.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                //MainActivity.this.convertirEnEuros();

                String strDollar=MainActivity.this.edtDollars.getText().toString();
                String strTaux=MainActivity.this.edtTaux.getText().toString();
                double dTaux;

                if(strTaux != null){
                    dTaux=Double.valueOf(strTaux);
                    if(strDollar.length() !=0){
                        if(strDollar.contains(",")){
                            strDollar.replace(',' , '.');
                        }
                        double dDollars = Double.valueOf(strDollar);
                        double dEuros =  dDollars /dTaux;
                        //MainActivity.this.edtEuros.setText(String.valueOf(dEuros));
                        MainActivity.this.edtEuros.setText(String.format("%.2f", dEuros));
                }

                }

                return false;
            }

        });

        edtEuros.setOnFocusChangeListener(this);
        edtDollars.setOnFocusChangeListener(this);

    }


    public void convertirEnEuros() {

        String StrDollar =this.edtDollars.getText().toString();
        StrDollar.replace(',' , '.');

        double dollar = Double.valueOf(StrDollar);
        double euros = dollar / this.taux;
        this.edtEuros.setText((char) euros);
    }

    public void convertirEnDollars() {

        String StrEuro = this.edtEuros.getText().toString();
        StrEuro.replace(',','.');

        double euros = Double.valueOf(StrEuro);
        double dollars = euros * this.taux;
        this.edtDollars.setText((char) dollars);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        this.edtEuros.setText("");
        this.edtDollars.setText("");
    }
}