package Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AsientoController implements Initializable {

    @FXML
    private ImageView ImagenAsiento = new ImageView();

    @FXML
    private Button ButtonVer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileAsiento = new File("Imagenes/2 - copia.jpg");
        Image brandingImageAsiento = new Image(fileAsiento.toURI().toString());
        ImagenAsiento.setImage(brandingImageAsiento);
    }

}
