package lordOfTheRings;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class application extends Application {

    /**
     * Creación y edición (ICONO Y TÍTULO) del STAGE y carga de la interfaz gráfica
     *
     * @param stage
     * @throws IOException
     */

    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load((application.class.getResource("interfaz.fxml")));

        Scene escena = new Scene(fxmlLoader);
        stage.getIcons().add(new Image(application.class.getResourceAsStream("Icono.png")));
        stage.setTitle("El Señor de los Anillos");
        stage.setResizable(false);
        stage.setScene(escena);
        stage.show();
    }


    /**
     * Función Principal
     *
     * @param args
     */

    public static void main(String[] args) {
        launch();
    }
}