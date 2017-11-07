package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sample.ZbieraczDanych;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZbieraczMeteo extends ZbieraczDanych {

    private String wy(String text, String pat)
    {
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(text);
        matcher.find();
        return matcher.group(1);
    }
    public void ustawParametry(WarunkiPogodowe war) {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.meteo.waw.pl").get();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String text = doc.body().text();
        zachm = "N/A";
        temp = wy(text, "temperatura ([^ ]+)");
        ciśn = wy(text, "ciśnienie ([^ ]+)");
        siłaWiatru = wy(text, "wiatr ([^ ]+)");
        kierWiatru = wy(text, "kierunek: [^ ]+ [^ ]+ ([^ ]+)");
        wilg = wy(text, "wilgotność ([^ ]+)");
        war.ustaw(temp, ciśn, zachm, siłaWiatru, kierWiatru, wilg);
    }
}
