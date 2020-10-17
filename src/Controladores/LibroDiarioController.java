package Controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LibroDiarioController implements Initializable {

    @FXML
    private ImageView ImagenLibroAsiento;

    @FXML
    private Button ButtonImprimir;

    @FXML
    private Button ButtonSalir;

    @FXML
    private DatePicker FechaEmision;

    @FXML
    private DatePicker FechaDesdeLibroDiario;

    @FXML
    private DatePicker FechaHastaLibroDiario;

    @FXML
    private TextField NombreEmpresa;

    @FXML
    private TextField EjercicioLibroDiario;

    @FXML
    private TableView TableLibroDiario;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File fileLibroDiario = new File("Imagenes/2 - copia.jpg");
        Image brandingImageLibroDiarioAsiento = new Image(fileLibroDiario.toURI().toString());
        ImagenLibroAsiento.setImage(brandingImageLibroDiarioAsiento);

    }
}
