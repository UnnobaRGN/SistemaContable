package Controladores;

import Modelo.Asiento;
import Modelo.Cuenta_Asiento;
import Modelo.Cuentas;
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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;



public class VerAsientoController implements Initializable {

    @FXML
    private ImageView ImagenVerAsiento = new ImageView();

    @FXML
    private Button botonImprimir;

    @FXML
    private Button botonSalir;

    private AsientoController a;

    @FXML
    private TextField numeroAsiento;

    @FXML
    private TableView<Cuenta_Asiento> tablaAsiento;

    @FXML
    private TableColumn<Cuenta_Asiento, Date> columnaFecha;

    @FXML
    private TableColumn<Cuenta_Asiento, String> columnaCuenta;

    @FXML
    private TableColumn<Cuenta_Asiento, Float> columnaDebe;

    @FXML
    private TableColumn<Cuenta_Asiento, Float> columnaHaber;

    ObservableList<Cuenta_Asiento> lista;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileVerAsiento = new File("Imagenes/2 - copia.jpg");
        Image brandingImageVerAsiento = new Image(fileVerAsiento.toURI().toString());
        ImagenVerAsiento.setImage(brandingImageVerAsiento);
        //mostrarDatos(); NO ANDA
    }

    public void salirVerAsiento(ActionEvent event) throws IOException {
        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();
    }

    public ObservableList<Cuenta_Asiento> obtenerDatos(Asiento a){
        Connection conn = ConexionBD.getConnection();
        ObservableList<Cuenta_Asiento> list = FXCollections.observableArrayList();
        try {

            String datos ="SELECT a.fecha,cu.cuenta,c.haber,c.debe FROM asiento a INNER JOIN cuenta_asiento c ON a.idasiento = c.id_asiento INNER JOIN cuenta as cu ON cu.idcuenta = c.id_cuenta WHERE a.idasiento="+ a ;


            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(datos);

            while (rs.next()) {
                Cuenta_Asiento c = new Cuenta_Asiento();
                c.setFecha(rs.getDate("fecha"));
                c.setCuenta(rs.getString("cuenta"));
                c.setDebe(rs.getFloat("debe"));
                c.setHaber(rs.getFloat("haber"));
                list.add(c);

            }
        } catch (Exception e) {


        }
        return list;
    }

    public void mostrarDatos(){
        columnaFecha.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Date>("fecha"));
        columnaCuenta.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, String>("cuenta"));
        columnaDebe.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Float>("debe"));
        columnaDebe.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Float>("haber"));

        lista = obtenerDatos(a.seleccionAsiento());
        tablaAsiento.setItems(lista);
    }

    public void imprimirVerAsiento(ActionEvent event){
        System.out.println("Jaja pensaste que iba a imprimir");
    }

}
