package Controladores;

import Modelo.Asiento;
import Modelo.Cuenta_Asiento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
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
    private TableView<Cuenta_Asiento> TablaLibroDiario;

    @FXML
    private TableColumn<Cuenta_Asiento, Date> TablaFecha;

    @FXML
    private TableColumn<Cuenta_Asiento, Integer> TablaNumeroAsiento;

    @FXML
    private TableColumn<Cuenta_Asiento, Integer> TablaNumeroCuenta;

    @FXML
    private TableColumn<Cuenta_Asiento, String> TablaDescripcion;

    @FXML
    private TableColumn<Cuenta_Asiento, String> Tablacuenta;

    @FXML
    private TableColumn<Cuenta_Asiento,Float> TablaDebe;

    @FXML
    private TableColumn<Cuenta_Asiento, Float> TablaHaber;

    ObservableList<Cuenta_Asiento> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File fileLibroDiario = new File("Imagenes/2 - copia.jpg");
        Image brandingImageLibroDiarioAsiento = new Image(fileLibroDiario.toURI().toString());
        ImagenLibroAsiento.setImage(brandingImageLibroDiarioAsiento);

    }

    /*public void fechaDesde(ActionEvent e){

        if(FechaDesdeLibroDiario.getValue() != null && FechaHastaLibroDiario.getValue() != null){ //&& compararFechas(Date.valueOf(FechaDesdeLibroDiario.getValue()), Date.valueOf(FechaHastaLibroDiario.getValue()))){

            Date fechaDe = Date.valueOf(FechaDesdeLibroDiario.getValue());
            Date fechaHas = Date.valueOf(FechaHastaLibroDiario.getValue());
            list = mostrarFiltrados(fechaDe, fechaHas);
            TablaLibroDiario.setItems(list);

        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Ingrese ambas fechas para el filtrado.");
            alert.showAndWait();
        }

    }

    public static ObservableList<Cuenta_Asiento> mostrarFiltrados(Date dd, Date dh){

        Connection conn = ConexionBD.getConnection();
        ObservableList<Cuenta_Asiento> list = FXCollections.observableArrayList();

        try {

            String SQL = "SELECT *, ca.fecha as fecha, a.descripcion as descripcion, ca.idcuenta as idcuenta, ca.idasiento as idasiento, ca.debe as debe, ca.haber as haber  FROM cuenta_asiento as ca INNER JOIN asiento as a ON ca.idasiento=a.idasiento WHERE a.fecha BETWEEN '" + dd + "'AND'" + dh + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                list.add(new Cuenta_Asiento(rs.getDate("fecha"),rs.getInt("idcuenta"), rs.getInt("idasiento"), rs.getString("cuenta"), rs.getString("TablaDescripcion"), rs.getFloat("debe"), rs.getFloat("haber")));
            }

        } catch (Exception e) {

        }
        return list;


    }

     */

    public void salirLibroDiario(ActionEvent e){

        Stage stage = (Stage) ButtonSalir.getScene().getWindow();
        stage.close();

    }

    public void imprimirLibroDiario(ActionEvent e){

        System.out.println("Ger se ocupa de esto");

    }

}
