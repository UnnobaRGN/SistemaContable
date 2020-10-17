package Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LibroDiarioController implements Initializable {

    @FXML
    private ImageView ImagenLibroAsiento;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File fileLibroDiario = new File("Imagenes/2 - copia.jpg");
        Image brandingImageLibroDiarioAsiento = new Image(fileLibroDiario.toURI().toString());
        ImagenLibroAsiento.setImage(brandingImageLibroDiarioAsiento);

    }
}
