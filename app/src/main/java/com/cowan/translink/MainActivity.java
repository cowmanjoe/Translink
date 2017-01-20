package com.cowan.translink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<Departure> departListPopulator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Departure> d = new ArrayList<>();
        d.add(new Departure("1:30", 69806, 12));

        departListPopulator = new ArrayAdapter<>(this, R.layout.activity_departure_list, d);
        ListView departureList = (ListView) findViewById(R.id.departure_list);
        departureList.setAdapter(departListPopulator);

    }


    public void addDepartures(View view) {
        //TODO: implement method
        departListPopulator.add(new Departure("5:55", 50585, 84));
    }
}
