package Controladores;

import Modelo.Producto;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MetodoCosteoController implements Initializable {

    @FXML
    private Button botonFacturacion;

    @FXML
    private Button botonClientes;

    @FXML
    private Button botonStock;

    @FXML
    private Button botonVentas;

    @FXML
    private Button botonMetodoAbsorbente;

    @FXML
    private Button botonMetodoDirecto;

    @FXML
    private Button botonLimpiar;

    @FXML
    private TableView tablaMetodoCosteo;

    @FXML
    private TableColumn<Producto, Integer> columnaCuenta;

    @FXML
    private TableColumn<Producto, Double> columnaDebe;

    @FXML
    private TableColumn<Producto, Double> columnaHaber;

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

    public void accederFacturacion(ActionEvent event) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Facturas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Facturas");
        stage.show();
    }

    public void accederClientes(ActionEvent event) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Clientes.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Facturacion");
        stage.show();
    }

    public void accederStock(ActionEvent event) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Stock.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Facturacion");
        stage.show();
    }

    public void accederVentas(ActionEvent event) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Ventas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Facturacion");
        stage.show();
    }

    public void accederMetodoAbsorbente(ActionEvent event) {
    }

    public void accederMetodoDirecto(ActionEvent event) {
    }

    public void accederLimpiar(ActionEvent event) {
        mostrarDatos();
    }

    public void mostrarDatos(){}
}
