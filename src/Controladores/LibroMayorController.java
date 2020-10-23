package Controladores;

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
        traerCuentasAcomboBox();

    }

    public void mostrarCuenta(ActionEvent a){
        muestraCuenta.setDisable(true);
        muestraCuenta.setText(menuCuenta.getSelectionModel().getSelectedItem().toString());
        mostrarDatos();
    }
    public void limpiarFiltro(){
        TablaLibroMayor.setItems(null);
        fechaDesde.setValue(null);
        fechaHasta.setValue(null);
        muestraCuenta.setText(null);



    }


    public void salirLibroMayor(ActionEvent e){

        Stage stage = (Stage) Salir.getScene().getWindow();
        stage.close();

    }

    public void traerCuentasAcomboBox() {
        ObservableList<String> list = FXCollections.observableArrayList();
        this.menuCuenta.getItems().clear();

        try {
            Connection conn = ConexionBD.getConnection();
            Statement s = conn.createStatement();
            String SQL = "SELECT * FROM cuenta WHERE recibe_saldo = 1 AND habilitada_no='" + "Si" + "'";
            ResultSet rs = s.executeQuery(SQL);

            while (rs.next()) {
                list.add(rs.getString("cuenta"));
            }

            menuCuenta.setItems(list);
        } catch (Exception e) {

        }

    }

    public ObservableList<Cuenta_Asiento> mostrarDatosLibro(){
        Connection conn = ConexionBD.getConnection();
        ObservableList<Cuenta_Asiento> list = FXCollections.observableArrayList();
        String s = menuCuenta.getSelectionModel().getSelectedItem().toString();
        try {
            String SQL = "SELECT c.cuenta as cuenta, a.descripcion as descripcion, ca.debe as debe, ca.haber as haber,ca.saldo as saldo FROM cuenta c INNER JOIN cuenta_asiento ca ON c.idcuenta=ca.id_cuenta INNER JOIN asiento a ON a.idasiento=ca.id_asiento WHERE c.cuenta='" + s +"' ORDER BY a.fecha";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                Cuenta_Asiento c = new Cuenta_Asiento();
                c.setDescrip(rs.getString("descripcion"));
                c.setDebe(rs.getFloat("debe"));
                c.setHaber(rs.getFloat("haber"));
                c.setSaldo(rs.getFloat("saldo"));
                list.add(c);
            }

        }catch(Exception e){

        }
        return list;
    }

    public void mostrarDatos(){
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, String>("descrip"));
        columnaDebe.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Float>("debe"));
        columnaHaber.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Float>("haber"));
        columnaSaldo.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Float>("saldo"));

        TablaLibroMayor.setItems(mostrarDatosLibro());


    }






}
