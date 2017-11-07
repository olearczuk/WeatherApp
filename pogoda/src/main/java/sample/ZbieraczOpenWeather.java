package sample;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ZbieraczOpenWeather extends ZbieraczDanych{
    private String stopnieNaKierunek(Double s){
        if(s >= 360 - 22.5 || s <= 22.5)
            return "N";
        if(s >= 22.5 && s <= 45 + 22.5)
            return "NE";
        if(s >= 90 - 22.5 && s <= 90 + 22.5)
            return "E";
        if(s >= 135 - 22.5 && s <= 135 + 22.5)
            return "SE";
        if(s >= 180 - 22.5 && s <= 180 + 22.5)
            return "S";
        if(s >= 225 - 22.5 && s <= 225 + 22.5)
            return "SW";
        if(s >= 270 - 22.5 && s <= 270 + 22.5)
            return "W";
        return "NW";
    }
    public void ustawParametry(WarunkiPogodowe war) {
        StringBuilder a = new StringBuilder();
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Warsaw&APPID=c906e661199b53f21d4b5ed849a6c1df");
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
        JsonObject tab = new JsonParser().parse(zawartość).getAsJsonObject();


        JsonObject main = tab.get("main").getAsJsonObject();
        Double tempDouble = main.get("temp").getAsDouble() - 272.15;
        temp = tempDouble.toString();
        ciśn = main.get("pressure").getAsString();
        wilg = main.get("humidity").getAsString();

        JsonObject wind = tab.get("wind").getAsJsonObject();
        siłaWiatru = wind.get("speed").getAsString();
        kierWiatru = stopnieNaKierunek(wind.get("deg").getAsDouble());

        JsonObject clouds = tab.get("clouds").getAsJsonObject();
        zachm = clouds.get("all").getAsString();
        war.ustaw(temp, ciśn, zachm, siłaWiatru, kierWiatru, wilg);
    }
}
