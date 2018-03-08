package sample;


public abstract class ZbieraczDanych {
    protected String temp = "N/A", ciśn = "N/A", zachm = "N/A", siłaWiatru = "N/A", kierWiatru  = "N/A", wilg = "N/A";
    public abstract void ustawParametry(WarunkiPogodowe war);
}
