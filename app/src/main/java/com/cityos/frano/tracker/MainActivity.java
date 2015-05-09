package com.cityos.frano.tracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import java.text.DateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.cityos.frano.tracker.MESSAGE";
    public final static String STATUS_MESSAGE = "com.cityos.frano.tracker.STATUS";

    public final static int REQUEST_PRIKAZ      = 1;
    public final static int REQUEST_UNOS        = 2;
    public final static int REQUEST_IZMJENA     = 3;
    public final static int REQUEST_BRISANJE    = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void prikazPoruka(String Poruka){

        Toast.makeText(getApplicationContext(),
                Poruka,
                Toast.LENGTH_SHORT).show();
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

    public void btnPregledObilazakaClick(View view) {
        prikazPoruka("Prikaz Obilazaka");
    }

    public void btnIzmjeniObilazakClick(View view) {
        prikazPoruka("Izmjena Obilazaka");
    }

    public void btnUnesiObilazakClick(View view) {

        Intent intentUnos = new Intent(this, UnosObilaskaActivity.class);
        intentUnos.putExtra(EXTRA_MESSAGE, "Novi unos");
        startActivityForResult(intentUnos, REQUEST_UNOS);
    }

    public void btnIzbrisiObilazakClick(View view) {
        prikazPoruka("Brisanje Obilazaka");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case REQUEST_UNOS:
                if (resultCode == Activity.RESULT_OK) {
                    prikazPoruka("Novi unos : " + data.getStringExtra(STATUS_MESSAGE));
                }

                break;
            case REQUEST_PRIKAZ:

                break;
            case REQUEST_IZMJENA:

                break;
            case REQUEST_BRISANJE:

                break;
        }
    }
}
