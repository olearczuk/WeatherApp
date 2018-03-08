package sample;

public class WarunkiPogodowe {
    private String temp = "N/A", ciśn = "N/A", zachm = "N/A", siłaWiatru = "N/A";
    private String kierWiatru = "N/A", wilgotność = "N/A", PM25 = "N/A", PM10 = "N/A";
    public void ustaw(String t,String c,String z,String  s,String k,String w){
        temp = t;
        ciśn = c;
        zachm = z;
        siłaWiatru = s;
        kierWiatru = k;
        wilgotność = w;
    }
    //setter
    public void ustawPM(String p1, String p2){
        PM10 = p1;
        PM25 = p2;
    }
    //gettery
    public String temp(){
        return temp;
    }
    public String ciśn(){
        return ciśn;
    }
    public String zachm(){return zachm;}
    public String siłaWiatru(){
        return siłaWiatru;
    }
    public String kierWiatru(){
        return kierWiatru;
    }
    public String PM25(){
        return PM25;
    }
    public String PM10(){
        return PM10;
    }
    public String wilgotność(){
        return wilgotność;
    }

}
