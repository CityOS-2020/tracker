package com.cityos.frano.tracker;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class BrisiPointe extends ListActivity {

    List<String> popisDatoteka = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_brisi_pointe);

        ListView lstView = getListView();
        lstView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lstView.setTextFilterEnabled(true);

        File intStorage = getFilesDir();
        String[] popis = fileList();
        for(int i=0; i< popis.length; i++)
        {
            if (popis[i].endsWith(".koo")) // Condition to check .jpg file extension
                popisDatoteka.add(popis[i]);
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, popisDatoteka));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_brisi_pointe, menu);
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

    public void onListItemClick(ListView parent, View v,int position, long id){
        Toast.makeText(this, "Brisanje: " + popisDatoteka.get(position), Toast.LENGTH_SHORT).show();

        File dir = getFilesDir();
        deleteFile(popisDatoteka.get(position));

        osvjeziListu();

    }

    private void osvjeziListu() {
        File intStorage = getFilesDir();
        String[] popis = fileList();

        popisDatoteka.clear();

        for(int i=0; i< popis.length; i++)
        {
            if (popis[i].endsWith(".koo")) // Condition to check .jpg file extension
                popisDatoteka.add(popis[i]);
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, popisDatoteka));
    }
}
