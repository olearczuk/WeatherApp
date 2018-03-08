package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import com.google.common.io.Resources;
import org.kordamp.ikonli.javafx.FontIcon;

public class AppMain extends Application {

    private int czasOdświeżania = 30;
    class Pom extends TimerTask {
        private Controller controller;
        public Pom(Controller controller){
            this.controller = controller;
        }
        public void run() {
            controller.odświeżDane();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Controller controller = new Controller();
        URL mainFxmlURL = Resources.getResource("fxml/app.fxml");
        FXMLLoader loader = new FXMLLoader(mainFxmlURL);
        loader.setController(controller);
        AnchorPane pane = loader.load();

        Scene scene = new Scene(pane);
        scene.setFill(null);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Timer timer = new Timer();
        timer.schedule(new Pom(controller), 0, czasOdświeżania * 1000);
    }
    public static void main(String[] args) {launch(args);}
}
