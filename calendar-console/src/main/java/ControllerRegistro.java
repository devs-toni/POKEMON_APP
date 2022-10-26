import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRegistro implements Initializable {

        @FXML
        private TextField mail;
        @FXML
        private TextField name;
        @FXML
        private PasswordField pass;
        @FXML
        private ComboBox<String> tipo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
