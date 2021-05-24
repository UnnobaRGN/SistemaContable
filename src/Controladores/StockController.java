package Controladores;

import Modelo.Asiento;
import Modelo.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import java.util.ResourceBundle;

public class StockController implements Initializable {

    @FXML
    private Button botonFacturacion;

    @FXML
    private Button botonClientes;

    @FXML
    private Button botonCosteo;

    @FXML
    private Button botonVentas;

    @FXML
    private TextField textoCodigo;

    @FXML
    private TextField textoNombre;

    @FXML
    private TextField textoProveedor;

    @FXML
    private TextField textoPrecio;

    @FXML
    private TextField textoCantidad;

    @FXML
    private Button botonNuevoProducto;

    @FXML
    private Button botonBuscar;

    @FXML
    private Button botonQuitarFiltro;

    @FXML
    private Button botonDescarga;

    @FXML
    private TableView tablaStock;

    @FXML
    private TableColumn<Producto, Integer> columnaCodigo;

    @FXML
    private TableColumn<Producto, String> columnaNombre;

    @FXML
    private TableColumn<Producto, String> columnaProveedor;

    @FXML
    private TableColumn<Producto, Double> columnaPrecio;

    @FXML
    private TableColumn<Producto, Integer> columnaStock;

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    @FXML
    private ImageView imagenDerecha = new ImageView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingDerecha = new File("Imagenes/pep.png");
        Image brandingDer = new Image(brandingDerecha.toURI().toString());
        imagenIzquierda.setImage(brandingDer);

        File brandingIzquierda = new File("Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
        imagenDerecha.setImage(brandingIzq);

    }

    public void accerderFacturacion(ActionEvent e) throws IOException {
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
        stage.setTitle("Clientes");
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

    public void accederVentas(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Ventas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ventas");
        stage.show();
    }

    public void crearProducto(String codigo, String nombre, String proveedor, String precio, String cantidad){


    }

    public boolean noVacios(){

        if (textoCodigo.getText().isEmpty() && textoProveedor.getText().isEmpty() && textoNombre.getText().isEmpty() && textoCantidad.getText().isEmpty() && textoPrecio.getText().isEmpty()){
            return false;
        }
        return true;
    }

    public void accederNuevoProducto(ActionEvent e) throws IOException{
        //Crea nuevo Producto
        if(!noVacios()) {
            crearProducto(textoCodigo.getText(), textoNombre.getText(), textoProveedor.getText(), textoPrecio.getText(), textoCantidad.getText());

        }
        else{
            //Mostrar error
        }

    }

    public void accederBuscar(ActionEvent e) throws IOException{
        //Busca por lo que este filtrado
    }

    public void accederDescarga(ActionEvent e) throws IOException{
        //Descarga PDF
    }

    public void accederQuitarFiltro(ActionEvent e) throws IOException{
        //Quita los filtros
        textoCodigo.setText(null);
        textoNombre.setText(null);
        textoProveedor.setText(null);
        textoPrecio.setText(null);
        textoCantidad.setText(null);
        mostrarDatos(); //Muestra los datos predefinidos
    }

    public void mostrarDatos(){

    }

}
