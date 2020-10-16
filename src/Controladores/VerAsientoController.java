package Controladores;

import Modelo.Asiento;
import Modelo.Cuenta_Asiento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class VerAsientoController implements Initializable {

    @FXML
    private ImageView ImagenVerAsiento = new ImageView();

    @FXML
    private Button botonImprimir;

    @FXML
    private Button botonSalir;


    @FXML
    private TextField numeroAsiento;

    @FXML
    private TableView<Asiento> tablaAsiento;

    @FXML
    private TableColumn<Asiento, Date> columnaFecha;

    @FXML
    private TableColumn<Asiento, String> columnaCuenta;

    @FXML
    private TableColumn<Asiento, Float> columnaDebe;

    @FXML
    private TableColumn<Asiento, Float> columnaHaber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileVerAsiento = new File("Imagenes/2 - copia.jpg");
        Image brandingImageVerAsiento = new Image(fileVerAsiento.toURI().toString());
        ImagenVerAsiento.setImage(brandingImageVerAsiento);
    }

    public void salirVerAsiento(ActionEvent event) throws IOException {
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();
    }

    public void imprimirVerAsiento(ActionEvent event){
        System.out.println("Jaja pensaste que iba a imprimir");
    }

}
