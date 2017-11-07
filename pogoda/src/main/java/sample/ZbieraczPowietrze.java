package sample;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import sample.WarunkiPogodowe;

public class ZbieraczPowietrze extends ZbieraczDanych{
    public void ustawParametry(WarunkiPogodowe war){
        StringBuilder a = new StringBuilder();
        try {
            URL url = new URL("http://powietrze.gios.gov.pl/pjp/current/getAQIDetailsList?param=AQI");
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(), "UTF-8"));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                a.append(inputLine);
            in.close();
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }

        String zawartość = a.toString();
        JsonArray tab = new JsonParser().parse(zawartość).getAsJsonArray();

        JsonObject el;
        int ilePM10 = 0;
        int ilePM25 = 0;
        float sumaPM10 = 0;
        float sumaPM25 = 0;
        for(JsonElement aux : tab)
        {
            el = aux.getAsJsonObject();
            String s = el.get("stationName").getAsString();
            if(s.contains("Warszawa")) {
                el = el.get("values").getAsJsonObject();
                if(el.get("PM10") != null) {
                    ilePM10++;
                    sumaPM10 += el.get("PM10").getAsFloat();
                }
                if(el.get("PM2.5") != null) {
                    ilePM25++;
                    sumaPM25 += el.get("PM2.5").getAsFloat();
                }
            }
        }
        war.ustawPM(Float.toString(sumaPM10/ilePM10), Float.toString(sumaPM25/ilePM25));
    }
}
