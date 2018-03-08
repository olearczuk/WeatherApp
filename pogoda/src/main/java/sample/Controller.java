package sample;


import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Controller {
    private ZbieraczDanych zmienialny = new ZbieraczMeteo();
    private ZbieraczDanych stały = new ZbieraczPowietrze();
    private WarunkiPogodowe w;
    private int aktZbier = 0, liczbaZbier = 2;
    @FXML
    RadioButton meteo, openWeather;
    @FXML
    Button button1;
    @FXML
    Text ciśn, zachm, siłaWiatru, kierWiatru, wilgotność, PM10, PM25, czas, temp;

    public void odświeżDane(){
        w = new WarunkiPogodowe();
        Task<Void> task = new Task1(zmienialny, w, true);
        Thread thread = new Thread(task);
        thread.setDaemon(true);

//        Task<Void> task1 = new Task1(stały, w, false);
//        Thread thread1 = new Thread(task1);
//        thread1.setDaemon(true);

        thread.start();
//        thread1.start();
    }
    @FXML
    public void initialize(){
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {odświeżDane();
            }
        };
        EventHandler<ActionEvent> radioMeteo = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                zmienialny = new ZbieraczMeteo();
                openWeather.setSelected(false);
                aktZbier = 0;
            }
        };
        EventHandler<ActionEvent> radioOpenWeather = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                zmienialny = new ZbieraczOpenWeather();
                meteo.setSelected(false);
                aktZbier = 1;
            }
        };
        button1.setOnAction(buttonHandler);
        meteo.setOnAction(radioMeteo);
        openWeather.setOnAction(radioOpenWeather);
    }


    private class Task1 extends Task<Void> {
        private ZbieraczDanych zbier;
        private WarunkiPogodowe war;
        private boolean czyZmieniaćCzas;
        public Task1(ZbieraczDanych zbier, WarunkiPogodowe war, boolean czyZmieniaćCzas){
            this.zbier = zbier;
            this.war = war;
            this.czyZmieniaćCzas = czyZmieniaćCzas;
        }
        protected Void call() throws Exception {
            zbier.ustawParametry(war);
            zmienialny.ustawParametry(w);
            temp.setText(w.temp());
            ciśn.setText(w.ciśn());
            zachm.setText(w.zachm());
            siłaWiatru.setText(w.siłaWiatru());
            wilgotność.setText(w.wilgotność());
            PM10.setText(w.PM10());
            PM25.setText(w.PM25());
            kierWiatru.setText(w.kierWiatru());
            if(czyZmieniaćCzas) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date d = new Date();
                czas.setText(sdf.format(d));
            }
            return null;
        }
    }


}
