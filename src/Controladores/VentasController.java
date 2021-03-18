package Controladores;

import Modelo.Cuenta_Asiento;
import Modelo.Producto;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingDerecha = new File("../Imagenes/pep.png");
        Image brandingDer = new Image(brandingDerecha.toURI().toString());
        imagenDerecha.setImage(brandingDer);

        File brandingIzquierda = new File("../Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
        imagenIzquierda.setImage(brandingIzq);


        confirmarVenta();
        cancelarVenta();
        agregarProducto();

    }

    public void confirmarVenta(){}

    public void cancelarVenta(){}

    public void agregarProducto(){}

}