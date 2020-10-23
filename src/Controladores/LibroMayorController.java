package Controladores;

import Modelo.Cuenta_Asiento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LibroMayorController implements Initializable {

    @FXML
    private ImageView ImagenFondo;

    @FXML
    private Button Filtrar;

    @FXML
    private Button LimpiarFiltro;

    @FXML
    private Button Salir;

    @FXML
    private DatePicker fechaDesde;

    @FXML
    private DatePicker fechaHasta;

    @FXML
    private ComboBox menuCuenta;

    @FXML
    private TextField muestraCuenta;

    @FXML
    private TableView<Cuenta_Asiento> TablaLibroMayor;

    @FXML
    private TableColumn<Cuenta_Asiento, String> columnaDescripcion;

    @FXML
    private TableColumn<Cuenta_Asiento,Float> columnaDebe;

    @FXML
    private TableColumn<Cuenta_Asiento, Float> columnaHaber;

    @FXML
    private TableColumn<Cuenta_Asiento, Float> columnaSaldo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File fileLibroMayor = new File("Imagenes/2 - copia.jpg");
        Image brandingImageLibroDiarioMayor = new Image(fileLibroMayor.toURI().toString());
        ImagenFondo.setImage(brandingImageLibroDiarioMayor);

    }

    public void filtrarFechas(){};
    public void limpiarFiltro(){};


    public void salirLibroMayor(ActionEvent e){

        Stage stage = (Stage) Salir.getScene().getWindow();
        stage.close();

    }





}
