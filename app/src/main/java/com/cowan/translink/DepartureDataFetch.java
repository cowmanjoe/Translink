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
    private static final String API_KEY = "gmSNagac7UUqdirk3bFj";

    private static WeakReference<MainActivity> activityRef;

    public static void updateActivity(MainActivity activity) {
        activityRef = new WeakReference<>(activity);
    }

    private static List<Departure> getDepartures(int stop) {
        List<Departure> ans = new ArrayList<Departure>();
        Map<Integer, List<String>> lts = getLastTimes(stop);

        for (Integer r : lts.keySet()) {
            for (String lt : lts.get(r)) {
                Departure a = new Departure(lt, stop, r);
                ans.add(a);
            }
        }

        return ans;
    }

    private static List<Departure> getDepartures(int stop, int route) {
        Map<Integer, List<String>> lts = getLastTimes(stop, route);
        List<Departure> ans = new ArrayList<Departure>();

        for (Integer r : lts.keySet()) {
            for (String lt : lts.get(r)) {
                Departure a = new Departure(lt, stop, r);
                ans.add(a);
            }
        }

        return ans;

    }

    private static Map<Integer, List<String>> getLastTimes(int stop) {
        URL url = getURLForLastTimes(stop);
        String data = retrieveDataFromURL(url);

        return TranslinkDataParser.parseDepartureTimes(data);
    }


    private static Map<Integer, List<String>> getLastTimes(int stop, int route) {
        URL url = getURLForLastTimes(stop, route);

        String data = retrieveDataFromURL(url);

        return TranslinkDataParser.parseDepartureTimes(data);
    }

    /**
     * Retrieves raw data from API request at the URL
     * @param url
     * @return String containing at the specified url or the empty string if an IOException occurs
     */
    private static String retrieveDataFromURL(URL url) {


        String line = "";
        String temp;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            //String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
            return stringBuilder.toString();

            /*
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            while((temp = in.readLine()) != null) {
                line += temp + "\n";
            }
            */
        } catch (IOException e) {
            System.out.println("Attempting to read from " + url.toString() + " caused an IOException:" + e.toString());

        } catch (Exception e) {
            System.out.println("Attempting to read from " + url.toString() + " caused an Exception: " + e.toString());
        }
        return line;

    }

    /**
     * Generates URL for departure times for all routes from particular stop
     * @param stop the stop number
     * @return URL or null if a MalformedURLException is thrown
     */
    private static URL getURLForLastTimes(int stop) {
        String url = "";
        try {
            url = "http://api.translink.ca/rttiapi/v1/stops/" + stop + "/estimates?apikey=" + API_KEY;
            return new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException thrown: " + e.toString() + "\nThis was the URL: " + url);
            return null;
        }
    }

    /**
     * Generates URL for departure times for a particular stop and route
     * @param stop the stop number
     * @param route the route number
     * @return URL or null if a MalformedURLException is thrown
     */
    private static URL getURLForLastTimes(int stop, int route) {
        String url = "";
        try {
            url = "http://api.translink.ca/rttiapi/v1/stops/" + stop + "/estimates?apikey=" + API_KEY + "&routeNo=" + route;
            return new URL(url);
        } catch (MalformedURLException e ) {
            System.out.println("MalformedURLException thrown: " + e.toString() + "\nThis was the URL: " + url);
            return null;
        }
    }



    @Override
    protected List<Departure> doInBackground(Integer... stops) {
        List<Departure> departures = new ArrayList<>();
        for (Integer stop : stops) {
            departures.addAll(DepartureDataFetch.getDepartures(stop));
        }
        return departures;
    }

    @Override
    protected void onPostExecute(List<Departure> deps) {
        activityRef.get().addDepartures(deps);
    }
}
