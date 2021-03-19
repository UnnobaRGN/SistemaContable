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

public class FacturacionController implements Initializable{

    @FXML
    private ImageView imagenDerecha = new ImageView();

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    @FXML
    private Button botonCosteo;

    @FXML
    private Button botonClientes;

    @FXML
    private Button botonVentas;

    @FXML
    private Button botonStock;

    @FXML
    private MenuButton menuTipo;

    @FXML
    private MenuItem itemA;

    @FXML
    private MenuItem itemB;

    @FXML
    private MenuItem itemC;

    @FXML
    private DatePicker fechaEntrega;

    @FXML
    private TextField codigoCliente;

    @FXML
    private Button botonBuscarCliente;

    @FXML
    private TextField direccionCliente;

    @FXML
    private TextField nombrecliente;

    @FXML
    private TextField cuitCliente;

    @FXML
    private MenuButton menuCategoria;

    @FXML
    private MenuItem itemAA;

    @FXML
    private MenuItem itemBB;

    @FXML
    private MenuItem itemCC;

    @FXML
    private TextField codigoVendedor;

    @FXML
    private MenuButton menuVendedor;

    @FXML
    private MenuItem itemAAA;

    @FXML
    private MenuItem itemBBB;

    @FXML
    private MenuItem itemCCC;

    @FXML
    private TextField codigoArticulo;

    @FXML
    private TextField descripcionVenta;

    @FXML
    private TextField cantiadArticulo;

    @FXML
    private Button botonBuscarArticulo;

    @FXML
    private TextField precioArticulo;

    @FXML
    private TextField itemFacturacion;

    @FXML
    private TextField ivaTotal;

    @FXML
    private TextField precioFinal;

    @FXML
    private Button botonLimpiarTodo;

    @FXML
    private Button botonCancelarFactura;

    @FXML
    private Button botonConfirmarFactura;

    @FXML
    private TableView<Producto> tablaFacturacion;

    @FXML
    private TableColumn<Producto, Integer> columnaCodigo;

    @FXML
    private TableColumn<Producto, String> columnaDescripcion;

    @FXML
    private TableColumn<Producto, Float> columnaPrecio;

    @FXML
    private TableColumn<Producto, Float> columnaSubtotal;

    @FXML
    private TableColumn<Producto, Integer> columnaCantiad;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingDerecha = new File("Imagenes/pep.png");
        Image brandingDer = new Image(brandingDerecha.toURI().toString());
        imagenIzquierda.setImage(brandingDer);

        File brandingIzquierda = new File("Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
        imagenDerecha.setImage(brandingIzq);

    }

    public void accederVentas(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Ventas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ventas");
        stage.show();
    }

    public void accederCliente(ActionEvent e) throws IOException{
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

    public void buscarCliente(){}

    public void limpiarCliente(){}

    public void buscarArticulo(){}

    public void limpiarTodo(){}

    public void cancelarFactura(){}

    public void confirmarFactura(){}

}
