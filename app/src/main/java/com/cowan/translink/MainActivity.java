package com.cowan.translink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<Departure> departListPopulator;

    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        departListPopulator = new ArrayAdapter<>(this, R.layout.activity_departure_list);
        ListView departureList = (ListView) findViewById(R.id.departure_list);
        departureList.setAdapter(departListPopulator);

    }


    public void requestDepartures(View view) {
        EditText stopField = (EditText)findViewById(R.id.stop_num_field);
        new DepartureDataFetch().execute(Integer.parseInt(stopField.getText().toString()));
    }

    public void addDepartures(List<Departure> deps) {
        departListPopulator.addAll(deps);
    }


    public void clearDepartures(View view) {
        departListPopulator.clear();
    }
}
