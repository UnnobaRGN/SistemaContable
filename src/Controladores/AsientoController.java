package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AsientoController implements Initializable {

    @FXML
    private ImageView ImagenAsiento = new ImageView();

    @FXML
    private ImageView FondoAsiento = new ImageView();

    @FXML
    private Button ButtonRegistrar;

    @FXML
    private Button ButtonVer;

    @FXML
    private Button ButtonSalir;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileAsiento = new File("Imagenes/2 - copia.jpg");
        Image brandingImageAsiento = new Image(fileAsiento.toURI().toString());
        ImagenAsiento.setImage(brandingImageAsiento);

        File fileFondoAsiento = new File("Imagenes/grisAsiento.png");
        Image brandingFondoAsiento = new Image(fileFondoAsiento.toURI().toString());
        FondoAsiento.setImage(brandingFondoAsiento);
    }

    public void registrarNuevoAsiento(ActionEvent event) throws IOException {
        Parent registrarAsiento = FXMLLoader.load(getClass().getResource("/Vista/RegistrarAsiento.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(registrarAsiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void verAsiento(ActionEvent event) throws IOException{
        Parent registrarAsiento = FXMLLoader.load(getClass().getResource("/Vista/VerAsiento.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(registrarAsiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void salirAsientos(ActionEvent event) {
        Stage stage = (Stage) ButtonSalir.getScene().getWindow();
        stage.close();
    }

}
