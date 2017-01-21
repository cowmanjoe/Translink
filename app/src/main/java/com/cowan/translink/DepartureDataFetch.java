package com.cowan.translink;

/**
 * Created by Cowan on 1/19/2017.
 */

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




public class DepartureDataFetch extends AsyncTask<Integer, Void, List<Departure>> {





    @Override
    protected List<Departure> doInBackground(Integer... stops) {
        List<Departure> departures = new ArrayList<>();
        for (Integer stop : stops) {
            departures.addAll(DepartureDataFetcher.getDepartures(stop));
        }
        return departures;
    }

    @Override
    protected void onPostExecute(List<Departure> deps) {
        MainActivity.instance.addDepartures(deps);
    }
}
