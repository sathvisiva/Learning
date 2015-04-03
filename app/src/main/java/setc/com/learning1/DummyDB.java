package setc.com.learning1;

/**
 * Created by sathvi on 3/31/15.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DummyDB {
    private List<String> cities;

    public DummyDB() {

        String data = "Chennai,Coimbatore,cuddalore,"+
                "Argentina,	Armenia, Australia,	Austria,Azerbaijan,Bahamas,Bahrain,Bangladesh,Barbados,"+

                "United Kingdom,United States,Uruguay,Uzbekistan,Vanuatu,Vatican City,Venezuela,Vietnam,Yemen,Zambia,Zimbabwe";

        cities = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(data, ",");

        //Parse the country CSV list and set as Array
        while(st.hasMoreTokens()) {
            cities.add(st.nextToken().trim());
        }


    }

    public String[] getCityList(String query) {

        String country = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for(int i=0; i < cities.size(); i++) {
            country = cities.get(i).toLowerCase();
            if(country.startsWith(query)) {
                matched.add(cities.get(i));
            }
        }
        String[] matched1 = new String[matched.size()];
       matched1 = matched.toArray(matched1);
        matched1  = (matched1.length == 0) ? matched1 : matched1;
        return matched1;
    }


}
