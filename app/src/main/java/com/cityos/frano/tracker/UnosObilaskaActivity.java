package com.cityos.frano.tracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import com.cityos.frano.tracker.ObilazakPoint;

public class UnosObilaskaActivity extends ActionBarActivity {

    private ObilazakPoint m_op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_obilaska);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Date date = new Date();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        m_op = new ObilazakPoint(dateFormat.format(date), "0.000000", "0.000000", message, "");

        TextView tv = (TextView)findViewById(R.id.textVrijeme);
        tv.setText(m_op.getVrijeme());
        tv = (TextView)findViewById(R.id.textLongitude);
        tv.setText("Longitude : " + m_op.getLongitude());
        tv = (TextView)findViewById(R.id.textLatitude);
        tv.setText("Latitude : " + m_op.getLatitude());

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

        Intent resultIntent = new Intent();
        resultIntent.putExtra(MainActivity.STATUS_MESSAGE, "Gotovo");  // put data that you want returned to activity A
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
