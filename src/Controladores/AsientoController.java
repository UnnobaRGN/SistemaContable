package Controladores;

import Modelo.Asiento;
import Modelo.Cuenta_Asiento;
import Modelo.Cuentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.ConexionBD;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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

    @FXML
    private Button ButtonFiltrar;

    @FXML
    private Button ButtonLimpiarFiltro;

    @FXML
    private DatePicker fechaDesde;

    @FXML
    private DatePicker fechaHasta;

    @FXML
    private TableView<Asiento> tablaAsientos;

    @FXML
    private TableColumn<Asiento, Date> columnaFecha;

    @FXML
    private TableColumn<Asiento, String> columnaDescripcion;

    ObservableList<Asiento> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileAsiento = new File("Imagenes/2 - copia.jpg");
        Image brandingImageAsiento = new Image(fileAsiento.toURI().toString());
        ImagenAsiento.setImage(brandingImageAsiento);

        File fileFondoAsiento = new File("Imagenes/grisAsiento.png");
        Image brandingFondoAsiento = new Image(fileFondoAsiento.toURI().toString());
        FondoAsiento.setImage(brandingFondoAsiento);


        mostrarAsientos();////////////////ACTIVAR CUANDO HAYA DATOS


    }


    public void registrarNuevoAsiento(ActionEvent event) throws IOException {
        Parent registrarAsiento = FXMLLoader.load(getClass().getResource("/Vista/RegistrarAsiento.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(registrarAsiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void verAsiento(ActionEvent event) throws IOException{
            if(!tablaAsientos.getSelectionModel().isEmpty()) {

                Parent registrarAsiento = FXMLLoader.load(getClass().getResource("/Vista/VerAsiento.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(registrarAsiento);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Ha surgido un problema");
                alert.setContentText("Seleccione el registro a ver");
                alert.showAndWait();
            }

    }

    public void salirAsientos(ActionEvent event){
        Stage stage = (Stage) ButtonSalir.getScene().getWindow();
        stage.close();
    }

    public void mostrarAsientos(){


        columnaFecha.setCellValueFactory(new PropertyValueFactory<Asiento, Date>("fecha"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<Asiento, String>("descripcion"));

        list = getAsientos();
        tablaAsientos.setItems(list);

    }


    public static ObservableList<Asiento> getAsientos(){

        Connection conn = ConexionBD.getConnection();
        ObservableList<Asiento> list = FXCollections.observableArrayList();

        try {


            String SQL = "SELECT * FROM asiento ";

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                list.add(new Asiento(rs.getDate("fecha"), rs.getString("descripcion")));
            }

        } catch (Exception e) {

        }
        return list;

    }

    public void filtrarFechas(ActionEvent event){

        if(fechaDesde.getValue() != null && fechaHasta.getValue() != null && compararFechas(Date.valueOf(fechaDesde.getValue()), Date.valueOf(fechaHasta.getValue()))){

            Date fechaDe = Date.valueOf(fechaDesde.getValue());
            Date fechaHas = Date.valueOf(fechaHasta.getValue());
            list = mostrarAsientosFiltraros(fechaDe, fechaHas);
            tablaAsientos.setItems(list);

        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Ingrese ambas fechas para el filtrado.");
            alert.showAndWait();
        }

    }

    public boolean compararFechas(Date dd, Date dh){

        if (0 == dd.compareTo(dh)){
            return false;
        }
        else{
            return true;
        }

    }

    public static ObservableList<Asiento> mostrarAsientosFiltraros(Date dd, Date dh){

        Connection conn = ConexionBD.getConnection();
        ObservableList<Asiento> list = FXCollections.observableArrayList();

        try {

            String SQL = "SELECT *, a.fecha as fecha, a.descripcion as descripcion FROM asiento as a WHERE fecha BETWEEN '" + dd + "'AND'" + dh + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                list.add(new Asiento(rs.getDate("fecha"), rs.getString("descripcion")));
            }

            } catch (Exception e) {

            }
        return list;


    }

    public void limpiarFiltro(ActionEvent event){

        fechaDesde.setValue(null);
        fechaHasta.setValue(null);
        mostrarAsientos();


    }

    public Asiento seleccionAsiento(){
        Asiento a = tablaAsientos.getSelectionModel().getSelectedItem();
        return a;

   }

}
