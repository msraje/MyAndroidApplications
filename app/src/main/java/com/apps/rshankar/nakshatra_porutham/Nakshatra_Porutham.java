package com.apps.rshankar.nakshatra_porutham;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Nakshatra_Porutham extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    public static final String All_NAKSHATRAM = "com.example.myfirstapp.MESSAGE";
    public static final String ID_NAKSHATRAM = "com.example.myfirstapp.IDMESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nakshatra__porutham);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Spinner spinner = findViewById(R.id.spinner);

        String S = spinner.getSelectedItem().toString();

    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Porutham_Results.class);
        Spinner girlSpinner = findViewById(R.id.spinner3);
        String girlNakshatram = girlSpinner.getSelectedItem().toString();

        Spinner boySpinner = findViewById(R.id.spinner);
        String boyNakshatram = boySpinner.getSelectedItem().toString();

        int gIndex = girlSpinner.getSelectedItemPosition();
        int bIndex = boySpinner.getSelectedItemPosition();


      //  System.out.println("Nakshatrams Selected :" + girlNakshatram + ":" + boyNakshatram);

        String[] bothNakshatram = {girlNakshatram, boyNakshatram};

        ArrayList<Integer> idx_Nakshatram = new ArrayList<Integer>();


        idx_Nakshatram.add(Integer.valueOf(gIndex));
        idx_Nakshatram.add(Integer.valueOf(bIndex));

        intent.putExtra(All_NAKSHATRAM, bothNakshatram);
        intent.putExtra(ID_NAKSHATRAM, idx_Nakshatram);
        startActivity(intent);
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nakshatra__porutham, menu);
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
