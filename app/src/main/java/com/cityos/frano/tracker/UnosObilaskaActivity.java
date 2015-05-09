package com.cityos.frano.tracker;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import com.cityos.frano.tracker.ObilazakPoint;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class UnosObilaskaActivity
        extends ActionBarActivity
        implements GoogleApiClient.ConnectionCallbacks,
                    GoogleApiClient.OnConnectionFailedListener{

    private ObilazakPoint m_op;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_obilaska);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        m_op = new ObilazakPoint(dateFormat.format(date), "0.000000", "0.000000", message, "");
        buildGoogleApiClient();
        mGoogleApiClient.connect();

        TextView tv = (TextView)findViewById(R.id.textUnosVrijeme);
        tv.setText(m_op.getVrijeme());

        napuniKoordinate();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_unos_obilaska, menu);
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

    public void btnSpremiPodatke(View view){

        TextView tv = (TextView)findViewById(R.id.textUnosOpis);
        m_op.setOpis(tv.getText().toString());
        String imeDatoteke = imeDatoteke();
        m_op.Spremi("CityOs.ser", getApplicationContext());
        m_op.Spremi(imeDatoteke, getApplicationContext());

        Intent resultIntent = new Intent();
        resultIntent.putExtra(MainActivity.STATUS_MESSAGE, getString(R.string.uspjeh));  // put data that you want returned to activity A
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            m_op.setLatitude(String.valueOf(mLastLocation.getLatitude()));
            m_op.setLongitude(String.valueOf(mLastLocation.getLongitude()));
        }

        napuniKoordinate();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void napuniKoordinate(){
        TextView tv = (TextView)findViewById(R.id.textUnosLongitude);
        tv.setText(getString(R.string.longitude) + ": " + m_op.getLongitude());
        tv = (TextView)findViewById(R.id.textUnosLatitude);
        tv.setText(getString(R.string.latitude) + ": " + m_op.getLatitude());
    }

    public String imeDatoteke(){
        String datoteka = m_op.getVrijeme();
        datoteka = datoteka.replaceAll(" ", "");
        datoteka = datoteka.replaceAll(":", "");
        return datoteka + ".koo";
    }
}
