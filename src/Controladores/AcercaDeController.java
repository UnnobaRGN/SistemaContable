package Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AcercaDeController implements Initializable {

    @FXML
    private ImageView ImagenFondo = new ImageView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("Imagenes/2.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        ImagenFondo.setImage(brandingImage);
    }
}
