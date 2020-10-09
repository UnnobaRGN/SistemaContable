package Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class VerAsientoController implements Initializable {

    @FXML
    private ImageView ImagenVerAsiento = new ImageView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileVerAsiento = new File("Imagenes/2 - copia.jpg");
        Image brandingImageVerAsiento = new Image(fileVerAsiento.toURI().toString());
        ImagenVerAsiento.setImage(brandingImageVerAsiento);
    }
}
