package Controladores;

import Modelo.Asiento;
import Modelo.Cuenta_Asiento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class LibroDiarioController implements Initializable {

    @FXML
    private ImageView ImagenLibroAsiento = new ImageView();

    @FXML
    private Button ButtonImprimir;

    @FXML
    private Button ButtonSalir;

    @FXML
    private TextField FechaEmision;

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

    @FXML
    private Button LimpiarFiltro;

    @FXML
    private Button Filtrar;

    ObservableList<Cuenta_Asiento> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File fileLibroDiario = new File("Imagenes/2 - copia.jpg");
        Image brandingImageLibroDiarioAsiento = new Image(fileLibroDiario.toURI().toString());
        ImagenLibroAsiento.setImage(brandingImageLibroDiarioAsiento);
        fechaActual();
        nombreEmpresa();
        mostrarLibroDiario();
        ejercicio();
    }

   public void filtrarLibroDiario(ActionEvent e){
       if(FechaDesdeLibroDiario.getValue() != null && FechaHastaLibroDiario.getValue() != null){
           if(compararFechas(Date.valueOf(FechaDesdeLibroDiario.getValue()), Date.valueOf(FechaHastaLibroDiario.getValue()))){

               Date fechaDe = Date.valueOf(FechaDesdeLibroDiario.getValue());
               Date fechaHas = Date.valueOf(FechaHastaLibroDiario.getValue());

               list = mostrarAsientosFiltraros(fechaDe, fechaHas);
               TablaLibroDiario.setItems(list);
           }else{
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Atencion!");
               alert.setHeaderText("Por favor,");
               alert.setContentText("Ingrese un año valido.");
               alert.showAndWait();
           }
       }
       else {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Atencion!");
           alert.setHeaderText("Por favor,");
           alert.setContentText("Ingrese ambas fechas para el filtrado.");
           alert.showAndWait();
       }

   }

   public static ObservableList<Cuenta_Asiento> mostrarAsientosFiltraros(Date dd, Date dh){

        Connection conn = ConexionBD.getConnection();
        ObservableList<Cuenta_Asiento> list = FXCollections.observableArrayList();

       try {

           String SQL = "SELECT a.fecha as fecha, a.descripcion as descripcion, ca.id_cuenta as idcuenta, a.numero_asiento as numero, ca.debe as debe, ca.haber as haber, c.cuenta as cuenta FROM cuenta_asiento as ca INNER JOIN asiento as a ON ca.id_asiento=a.idasiento INNER JOIN cuenta as c ON c.idcuenta = ca.id_cuenta WHERE fecha BETWEEN '" + dd + "'AND'" + dh + "' ORDER BY ca.id_asiento, ca.debe DESC";
           Statement statement = conn.createStatement();
           ResultSet rs = statement.executeQuery(SQL);

           while (rs.next()) {
               Cuenta_Asiento ca= new Cuenta_Asiento();
               ca.setFecha(rs.getDate("fecha"));
               ca.setIdcuenta(rs.getInt("idcuenta"));
               ca.setCuenta(rs.getString("cuenta"));
               ca.setNum_asiento(rs.getInt("numero"));
               ca.setDebe(rs.getFloat("debe"));
               ca.setDescrip(rs.getString("descripcion"));
               ca.setHaber(rs.getFloat("haber"));
               list.add(ca);
           }

        } catch (Exception e) {

        }
        return list;


    }

   public boolean compararFechas(Date dd, Date dh){
        java.util.Date ahora = new java.util.Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy");
        if (formateador.format(dd).equals(formateador.format(dh)) && formateador.format(dh).equals(formateador.format(ahora))){
            return true;
        }
        else{
            return false;
        }

    }



   public void mostrarLibroDiario(){

        TablaFecha.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Date>("fecha"));
        TablaDescripcion.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, String>("descrip"));
        Tablacuenta.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, String>("cuenta"));
        TablaNumeroCuenta.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Integer>("idcuenta"));
        TablaNumeroAsiento.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Integer>("num_asiento"));
        TablaDebe.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Float>("debe"));
        TablaHaber.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Float>("haber"));

        list = getLibroDiario();
        TablaLibroDiario.setItems(list);

    }

   public static ObservableList<Cuenta_Asiento> getLibroDiario(){

        Connection conn = ConexionBD.getConnection();
        ObservableList<Cuenta_Asiento> list = FXCollections.observableArrayList();

        try {


            String SQL = "SELECT a.fecha as fecha, a.descripcion as descripcion, ca.id_cuenta as idcuenta, a.numero_asiento as numero, ca.debe as debe, ca.haber as haber, c.cuenta as cuenta FROM cuenta_asiento as ca INNER JOIN asiento as a ON ca.id_asiento=a.idasiento INNER JOIN cuenta as c ON c.idcuenta = ca.id_cuenta ORDER BY ca.id_asiento, ca.debe DESC";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                Cuenta_Asiento ca= new Cuenta_Asiento();
                ca.setFecha(rs.getDate("fecha"));
                ca.setIdcuenta(rs.getInt("idcuenta"));
                ca.setCuenta(rs.getString("cuenta"));
                ca.setNum_asiento(rs.getInt("numero"));
                ca.setDebe(rs.getFloat("debe"));
                ca.setDescrip(rs.getString("descripcion"));
                ca.setHaber(rs.getFloat("haber"));
                list.add(ca);
            }

        } catch (Exception e) {

        }
        return list;

    }

   public void fechaActual() {
        java.util.Date ahora = new java.util.Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        FechaEmision.setText(formateador.format(ahora));
        FechaEmision.setDisable(true);
    }

   public void ejercicio() {
        java.util.Date ahora = new java.util.Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy");
        EjercicioLibroDiario.setText(formateador.format(ahora));
        EjercicioLibroDiario.setDisable(true);
    }

   public void nombreEmpresa() {
        NombreEmpresa.setText("Urano's contability");
        NombreEmpresa.setDisable(true);

    }

   public void limpiarFiltro(ActionEvent event){

        FechaHastaLibroDiario.setValue(null);
        FechaDesdeLibroDiario.setValue(null);
        TablaLibroDiario.setItems(null);
        mostrarLibroDiario();

   }


   public void salirLibroDiario(ActionEvent e){

        Stage stage = (Stage) ButtonSalir.getScene().getWindow();
        stage.close();

    }

   public void imprimirLibroDiario(ActionEvent e){

        System.out.println("Ger se ocupa de esto");

    }

}
