package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView ImagenPrincipal;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("Imagenes/Captura.PNG");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        ImagenPrincipal.setImage(brandingImage);

    }

    public void loginButtonAction(ActionEvent event){
        loginMessageLabel.setText("Trataste de loguearte");

    }

}
