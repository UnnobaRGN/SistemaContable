package Controladores;

import Modelo.Cuenta_Asiento;
import Modelo.Producto;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class VentasController implements Initializable {

    @FXML
    private ImageView imagenDerecha = new ImageView();

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    @FXML
    private Button accederCosteo;

    @FXML
    private Button accederClientes;

    @FXML
    private Button accederFacturacion;

    @FXML
    private Button accederStock;

    @FXML
    private TextField cantidad;

    @FXML
    private TextField stock;

    @FXML
    private TextField codigo;

    @FXML
    private TextField precio;

    @FXML
    private ComboBox producto;

    @FXML
    private ComboBox cliente;

    @FXML
    private DatePicker fecha;

    @FXML
    private TableView<Producto> tabla;

    @FXML
    private TableColumn<Producto, Integer> columnaCodigo;

    @FXML
    private TableColumn<Producto, Integer> columnaCantidad;

    @FXML
    private TableColumn<Producto, String> columnaDescripcion;

    @FXML
    private TableColumn<Producto, Float> columnaPrecio;

    @FXML
    private TableColumn<Producto, Float> columnaTotal;

    @FXML
    private Button agregarProducto;

    @FXML
    private TextField totalPagar;

    @FXML
    private Button confirmarVenta;

    @FXML
    private Button cancelarVenta;

    @FXML
    private Button agregarP;

    @FXML
    private ComboBox seleccionProductos;

    @FXML
    private ComboBox seleccionClientes;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingDerecha = new File("Imagenes/pep.png");
        Image brandingDer = new Image(brandingDerecha.toURI().toString());
        imagenIzquierda.setImage(brandingDer);

        File brandingIzquierda = new File("Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
        imagenDerecha.setImage(brandingIzq);

        seleccionProductos.setItems(tomarProductos());
        seleccionClientes.setItems(tomarClientes());

        fecha.setValue(LocalDate.now());
        condicionFecha();

        confirmarVenta();
        cancelarVenta();
        agregarProducto();

    }

    public void accederFacturacion(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Facturas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Facturas");
        stage.show();
    }

    public void accederClientes(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Clientes.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Clienes");
        stage.show();
    }

    public void accederStock(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Stock.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Stock");
        stage.show();
    }

    public void accederCosteo(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/MetodoCosteo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Metodo de Costeo");
        stage.show();
    }


    public void condicionFecha(){
        if(fecha.getValue().isAfter(LocalDate.now())){
            fecha.setValue(LocalDate.now());
        }
    }

    public void confirmarVenta(){}

    public void cancelarVenta(){}

    public void agregarProducto(){}

    @FXML
    void seleccionarProducto(ActionEvent event){
        String s = seleccionProductos.getSelectionModel().getSelectedItem().toString();

        Connection conn = ConexionBD.getConnection();

        try {

            String SQL = "SELECT p.codigo as codigo, p.precio as precio, p.stock as stock FROM producto AS p WHERE p.nombre LIKE "+ "'" + s + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                codigo.setText(rs.getString("codigo"));
                precio.setText(rs.getString("precio"));
                stock.setText(rs.getString("stock"));
            }

        } catch (Exception e) {

        }
    }

    public ObservableList<String> tomarProductos(){
        Connection conn = ConexionBD.getConnection();
        ObservableList<String> list = FXCollections.observableArrayList();

        try {

            String SQL = "SELECT p.codigo as codigo, p.nombre as nombre, p.precio as precio, p.stock as stock FROM producto AS p WHERE p.activo = true ";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                list.add(rs.getString("nombre"));
            }

        } catch (Exception e) {

        }
        return list;
    }

    @FXML
    void seleccionarClientes(){
        String s = seleccionClientes.getSelectionModel().getSelectedItem().toString();

        Connection conn = ConexionBD.getConnection();

        try {

            String SQL = "SELECT c.nombre FROM cliente AS c WHERE c.nombre LIKE "+ "'" + s + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                codigo.setText(rs.getString("codigo"));
                precio.setText(rs.getString("precio"));
                stock.setText(rs.getString("stock"));
            }

        } catch (Exception e) {

        }
    }

    public ObservableList<String> tomarClientes(){
        Connection conn = ConexionBD.getConnection();
        ObservableList<String> list = FXCollections.observableArrayList();

        try {

            String SQL = "SELECT c.nombre FROM cliente AS c ";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                list.add(rs.getString("nombre"));
            }

        } catch (Exception e) {

        }
        return list;
    }

}
