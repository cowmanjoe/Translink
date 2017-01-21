package com.cowan.translink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cowan on 2017-01-20.
 */

public class TranslinkDataParser {

    /**
     * Parse the text that the API returns on a departure time request
     * @param data the string received from Translink API
     * @return mapping of routes to Strings representations of departure times
     */
    public static Map<Integer, List<String>> parseDepartureTimes(String data) {
        Map<Integer, List<String>> ans = new HashMap<>();
        String dataSoFar = data;
        while (dataSoFar.contains("<RouteNo>")) {
            dataSoFar = dataSoFar.substring(dataSoFar.indexOf("<RouteNo>") + "<RouteNo>".length());
            String routeString = dataSoFar.substring(0, dataSoFar.indexOf('<'));
            int route = Integer.parseInt(routeString);
            List<String> times = new ArrayList<>();
            while(dataSoFar.contains("<ExpectedLeaveTime>") &&
                    (dataSoFar.indexOf("<ExpectedLeaveTime>") < dataSoFar.indexOf("<RouteNo>") ||
                            !dataSoFar.contains("<RouteNo>"))){
                int index = dataSoFar.indexOf("<ExpectedLeaveTime>") + "<ExpectedLeaveTime>".length();
                dataSoFar = dataSoFar.substring(index);
                String time = dataSoFar.substring(0, dataSoFar.indexOf('<'));
                times.add(time);
            }
            ans.put(route, times);
        }
        return ans;
    }

}
